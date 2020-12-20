package com.device.id

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.content.Context
import android.net.wifi.WifiManager
import android.os.Build
import android.provider.Settings
import android.telephony.TelephonyManager
import android.text.TextUtils
import java.lang.NullPointerException
import java.net.NetworkInterface
import java.util.*

object DeviceIdControl {

    /**
     * 1.ID/GUID:应用卸载后重新安装将重置；String uniqueID = UUID.randomUUID().toString();
     * 2.SSAID:提供了一个在由同一开发者签名密钥签名的应用之间通用的标识
     *
     * 3.MAC:获取设备MAC地址
     * 4.获取IMEI
     * 5.对不可重置的标识符（包括 IMEI 和序列号）添加了限制。您的应用必须是设备或个人资料所有者应用，
     * 具有特殊运营商权限或具有 READ_PRIVILEGED_PHONE_STATE 特许权限，才能访问这些标识符。
     */
    const val GUID = 0
    const val SSAID = 1
    const val MAC = 2
    const val IMEI = 3

    fun getGUID(): String = UUID.randomUUID().toString()

    fun getSSAID(context: Context): String =
        Settings.System.getString(context.contentResolver, Settings.Secure.ANDROID_ID)

    @SuppressLint("MissingPermission")
    fun getMAC(context: Context): String {
        return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            Logger.d("小于Android 6.0 正在通过 WIFIManager 获取MAC")
            try {
                val manager: WifiManager =
                    context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
                val macAddress = manager.connectionInfo.macAddress
                if (TextUtils.isEmpty(macAddress)) throw NullPointerException()
                else macAddress
            } catch (e: Exception) {
                Logger.e("小于Android 6.0 通过 WIFIManager 获取MAC失败")
                Logger.e("尝试通过蓝牙适配器获取MAC地址")
                try {
                    val macAddress = BluetoothAdapter.getDefaultAdapter().address
                    if (TextUtils.isEmpty(macAddress)) ""
                    else macAddress
                } catch (e: Exception) {
                    Logger.e("小于Android 6.0 通过 BluetoothAdapter 获取MAC失败")
                    Logger.e("小于Android 6.0 开始获取网络硬件MAC")
                    getNetMac(context)
                }
            }
        } else getNetMac(context)
    }

    private fun getNetMac(context: Context): String {
        /**
         * 此处需要获取网络权限才能获取到MAC地址
         */
        if (!PermissionControl.isHasNetPermission(context)) {
            Logger.e(
                "获取网络硬件MAC时,没有网络权限,请先在AndroidManifest.xml中添加 Manifest.permission.INTERNET",
            )
            return ""
        }

        val buf = StringBuffer()
        var networkInterface: NetworkInterface?
        try {
            networkInterface = NetworkInterface.getByName("eth1")
            if (networkInterface == null) networkInterface = NetworkInterface.getByName("wlan0")
            if (networkInterface == null) networkInterface = NetworkInterface.getByName("wlan1")
            if (networkInterface == null) networkInterface = NetworkInterface.getByName("dummy0")
            if (networkInterface == null) networkInterface = NetworkInterface.getByName("bond0")
            if (networkInterface == null) {
                Logger.d( "获取网络硬件MAC地址失败,无法获取到网络接口")
                return ""
            }

            val address = networkInterface.hardwareAddress
            return if (address != null) {
                for (b in address) buf.append(String.format("%02X:", b))
                if (buf.isNotEmpty()) buf.deleteCharAt(buf.length - 1)
                buf.toString()
            } else {
                Logger.d("获取网络硬件MAC地址失败,获取到的网络地址为空")
                ""
            }
        } catch (e: Exception) {
            Logger.d("获取网络硬件MAC地址失败")
            return ""
        }
    }

    @SuppressLint("MissingPermission")
    fun getIMEI(context: Context): String {
        val tm = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        if (!PermissionControl.isHasPhonePermission(context)) {
            Logger.e( "获取IMEI时,没有phone权限,请先获取到权限")
            return ""
        }
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            try {
                Logger.e( "当前版本大于Android 10,尝试获取IMEI")
                tm.imei
            } catch (e: Exception) {
                try {
                    Logger.e( "获取IMEI失败,没有特殊权限,尝试获取MEID")
                    tm.meid
                } catch (e: Exception) {
                    Logger.e( "获取MEID失败,没有特殊权限,尝试获取DEVICE_ID")
                    tm.deviceId
                }
            }
        } else
            tm.deviceId
    }

}