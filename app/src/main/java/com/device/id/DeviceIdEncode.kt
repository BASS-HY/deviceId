package com.device.id

import java.security.MessageDigest
import java.util.*

object DeviceIdEncode {

    fun encodeMD5(message: String, upperCase: Boolean): String {
        return try {
            val md = MessageDigest.getInstance("MD5")
            val digest = md.digest(message.toByteArray())
            bytesToHex(digest, upperCase)
        } catch (e: Exception) {
            Logger.e("对设备的唯一标识进行md5加密失败")
            ""
        }
    }

    /**
     * 将字节数组转化成16进制，再转化大小写
     */
    private fun bytesToHex(digest: ByteArray, upperCase: Boolean): String {
        val md5str = StringBuffer()
        var digital: Int

        for (i in digest.indices) {
            digital = digest[i].toInt()
            if (digital < 0) digital += 256
            if (digital < 16) md5str.append("0")
            md5str.append(Integer.toHexString(digital))
        }

        return if (upperCase) {
            md5str.toString().toUpperCase(Locale.ROOT)
        } else md5str.toString().toLowerCase(Locale.ROOT)
    }

}