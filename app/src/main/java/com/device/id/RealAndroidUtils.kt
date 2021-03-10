package com.device.id

//import android.bluetooth.BluetoothAdapter
//import android.content.Context
//import android.content.Context.SENSOR_SERVICE
//import android.hardware.Sensor
//import android.hardware.SensorManager
import android.text.TextUtils
//import java.io.BufferedReader
//import java.io.InputStreamReader
//import java.net.NetworkInterface
import java.util.*

object RealAndroidUtils {
    /**
     * 理论知识来源：
     * 原创：OldJohn86
     * 地址：https://blog.csdn.net/yandaqijian/article/details/41748759
     *
     *  1. i386 适用于intel和AMD所有32位的cpu.以及via采用X86架构的32的cpu.intel平台包括8086,80286,80386,80486,奔腾系列(1.2.3.4)、赛扬系列,Pentium D系列以及centrino P-M,core duo 等.
     *
     *  2. X86_64 适用于intel的Core 2 Duo, Centrino Core 2 Duo, and Xeon 和AMD Athlon64/x2, Sempron64/x2, Duron64等采用X86架构的64位cpu.
     *
     *  3.I686 只是i386的一个子集,支持的cpu从Pentium 2 (686)开始,之前的型号不支持.
     */
//    fun isReal(): Boolean {
//        val arch: String =
//            System.getProperties().getProperty("os.arch")?.toUpperCase(Locale.ROOT) ?: return true
//        return !(TextUtils.equals(arch, "I686") || TextUtils.equals(arch, "I386")
//                || TextUtils.equals(arch, "I486") || TextUtils.equals(arch, "I586"))
//    }
    fun isReal(): Boolean {
        val arch: String =
            System.getProperties().getProperty("os.arch")?.toUpperCase(Locale.ROOT) ?: return true
        return TextUtils.equals(arch, "AARCH64") || TextUtils.equals(arch, "AARCH32")
    }

//    /**
//     * 判断是否有蓝牙
//     * @return true 为有蓝牙
//     */
//    private fun realBlueTooth(): Boolean {
//        val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
//        return if (bluetoothAdapter == null) false
//        else !TextUtils.isEmpty(bluetoothAdapter.name)
//    }
//
//    /**
//     * 判断是否有光传感器
//     */
//    private fun realLightSensor(context: Context): Boolean {
//        val sensorManager = context.getSystemService(SENSOR_SERVICE) as SensorManager
//        return sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT) != null
//    }
//
//    /**
//     * 判断CPU是否为手机型号的
//     */
//    private fun realCpu(): Boolean {
//        try {
//            val command = arrayListOf("/system/bin/cat", "/proc/cpuinfo")
//            val cmd = ProcessBuilder(command)
//
//            val process = cmd.start()
//            val sb = StringBuffer()
//            var readLine: String?
//            val responseReader = BufferedReader(InputStreamReader(process.inputStream, "utf-8"))
//            while (responseReader.readLine().also { readLine = it } != null) {
//                sb.append(readLine)
//            }
//            responseReader.close()
//            val cpuModel = sb.toString().toLowerCase(Locale.ROOT)
//            return !(cpuModel.contains("intel") || cpuModel.contains("amd"))
//        } catch (e: Exception) {
//            Logger.e("判断CPU型号出错")
//            return false
//        }
//    }
//
//    /**
//     * 如果为真实手机，则没有eth0 雷电模拟器则有eth0
//     * 需要开启网络权限
//     * @return false 为没有eth0 判断为真实手机
//     */
//    private fun hasEth0(context: Context): Boolean {
//        /**
//         * 此处需要获取网络权限才能获取到MAC地址
//         */
//        if (!PermissionControl.isHasNetPermission(context)) {
//            Logger.e(
//                "获取网络硬件MAC时,没有网络权限,无法判断是否存在eth0,请先在AndroidManifest.xml中添加 Manifest.permission.INTERNET"
//            )
//            return false
//        }
//
//        return try {
//            val networkInterface = NetworkInterface.getByName("eth0")
//            networkInterface != null && networkInterface.hardwareAddress != null
//        } catch (e: Exception) {
//            Logger.d("获取网络硬件MAC地址失败,无法获取到网络接口")
//            false
//        }
//    }


//    //操作系统的名称
//    val toUpperCase = System.getProperties().getProperty("os.name").toUpperCase(Locale.ROOT)
//    //Java 运行时环境版本
//    val version = System.getProperties().getProperty("java.version").toUpperCase(Locale.ROOT)
//    //Java 运行时环境供应商
//    val vendor = System.getProperties().getProperty("java.vendor").toUpperCase(Locale.ROOT)
//    //Java 供应商的 URL
//    val url = System.getProperties().getProperty("java.vendor.url").toUpperCase(Locale.ROOT)
//    //Java 安装目录
//    val home = System.getProperties().getProperty("java.home").toUpperCase(Locale.ROOT)
//    //Java 虚拟机规范版本
//    val jvmSpecificationVersion =
//        System.getProperties().getProperty("java.vm.specification.version")
//            .toUpperCase(Locale.ROOT)
//    //Java 虚拟机规范供应商
//    val jvmSpecificationVendor = System.getProperties().getProperty("java.vm.specification.vendor")
//        .toUpperCase(Locale.ROOT)
//    //Java 虚拟机规范名称
//    val jvmSpecificationName = System.getProperties().getProperty("java.vm.specification.name")
//        .toUpperCase(Locale.ROOT)
//    //Java 虚拟机实现版本
//    val jvmVersion =
//        System.getProperties().getProperty("java.vm.version").toUpperCase(Locale.ROOT)
//    //Java 虚拟机实现供应商
//    val jvmVendor =
//        System.getProperties().getProperty("java.vm.vendor").toUpperCase(Locale.ROOT)
//    //Java 虚拟机实现名称
//    val jvmName = System.getProperties().getProperty("java.vm.name").toUpperCase(Locale.ROOT)
//    //Java 运行时环境规范版本
//    val specificationVersion = System.getProperties().getProperty("java.specification.version").toUpperCase(Locale.ROOT)
//    //Java 运行时环境规范供应商
//    val specificationVendor = System.getProperties().getProperty("java.specification.vendor").toUpperCase(Locale.ROOT)
//    //Java 运行时环境规范名称
//    val specificationName = System.getProperties().getProperty("java.specification.name").toUpperCase(Locale.ROOT)
//    //Java 类格式版本号
//    val classVersion = System.getProperties().getProperty("java.class.version").toUpperCase(Locale.ROOT)
//    //Java 类路径
//    val classPath = System.getProperties().getProperty("java.class.path").toUpperCase(Locale.ROOT)
//    //加载库时搜索的路径列表
//    val libraryPath = System.getProperties().getProperty("java.library.path").toUpperCase(Locale.ROOT)
//    //默认的临时文件路径
//    val ioTmpdir = System.getProperties().getProperty("java.io.tmpdir").toUpperCase(Locale.ROOT)
//    //要使用的 JIT 编译器的名称
//    val compiler = System.getProperties().getProperty("java.compiler").toUpperCase(Locale.ROOT)
//    //一个或多个扩展目录的路径
//    val dirs = System.getProperties().getProperty("java.ext.dirs").toUpperCase(Locale.ROOT)
//    //文件分隔符（在 UNIX 系统中是“/”）
//    val fileSeparator = System.getProperties().getProperty("file.separator").toUpperCase(Locale.ROOT)
//    //路径分隔符（在 UNIX 系统中是“:”）
//    val pathSeparator = System.getProperties().getProperty("path.separator").toUpperCase(Locale.ROOT)
//    //行分隔符（在 UNIX 系统中是“/n”）
//    val lineSeparator = System.getProperties().getProperty("line.separator").toUpperCase(Locale.ROOT)
//    //操作系统的架构
//    val arch = System.getProperties().getProperty("os.arch").toUpperCase(Locale.ROOT)
//    //用户的当前工作目录
//    val dir = System.getProperties().getProperty("user.dir").toUpperCase(Locale.ROOT)
//    //用户的主目录
//    val hahaha = System.getProperties().getProperty("user.home").toUpperCase(Locale.ROOT)
//    //用户的账户名称
//    val name = System.getProperties().getProperty("user.name").toUpperCase(Locale.ROOT)


}