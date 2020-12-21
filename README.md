# deviceId 快速获取Android 唯一识别码
[！[]（https://jitpack.io/v/BASS-HY/deviceId.svg）]（https://jitpack.io/#BASS-HY/deviceId）

# CSDN文章地址（更详细的介绍）：https://blog.csdn.net/weixin_45379305/article/details/111477505

# 导入方式：
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
}

dependencies {
	    implementation 'com.github.BASS-HY:deviceId:v1.1'
}
```

# 如何使用：
### 1.前提：在清单文件中加入权限：
```<uses-permission android:name="android.permission.INTERNET" />```

### 2.调用：
```DeviceIdUtils.getDeviceId(context)```
即可,该方法将返回一个String值(MD5加密后的32位字符串)，返回的string值即为获得的唯一识别码

# 更多用法：

```
DeviceIdUtils.getDeviceId(this@MainActivity,upperCase = false)
upperCase 代表加密后的字符串中的字母是否使用大写;默认为false(不转化成大写)


DeviceIdUtils.getDeviceId(this@MainActivity,useMd5 = true)
useMd5 代表是否使用MD5加密获取到的唯一识别码;默认为true(使用MD5加密后再返回)


DeviceIdUtils.getDeviceId(this@MainActivity,useSSAID = true)
useSSAID 代表是否在IMEI无法获取时，将SSAID纳入考虑范围，默认为true(纳入考虑范围)
SSAID 提供了一个在由同一开发者签名密钥签名的应用之间通用的标识，如果同一个APP,使用了不同的签名打包,那么您应当将该值设置为false;

开启日志：
日志默认为开启，在release包中，建议将其关闭
DeviceIdUtils.isDebugger(false)//关闭日志
```
# DeviceIdUtils做了什么？
#####  1.首先，DeviceIdUtils会判断当前的Android 版本，如果低于6.0，则会直接获取IMEI，MD5加密后返回；
#####  2.如果Android 版本大于6.0，则会尝试获取IMEI，供有特殊权限的应用使用，如果获取不到，将会获取网卡的MAC地址，一般到这里，就能获取到MAC地址了，然后将其MD5加密后返回；
#####  3.如果因特殊原因无法获取到网卡的MAC地址(如未添加网络权限)，那么将获取SSAID作为唯一识别码（如果SSAID被纳入考虑范围）
#####  4.SSAID无法获取到，那么将进入最后一步，获取UUID，并将其MD5加密后返回；
#####  5.获取到唯一识别码后，将自动进行本地存储，下次获取时将直接从本地存储中读取(如果本地有此文件)，此文件在APP卸载时，将一并被删除；


# 注意：
##### 1.导入该项目，您将实现快速获取android 设备唯一识别码！！！
##### 2.Android 6.0以下，将直接获取到IMEI进行MD5加密并返回；
##### 3.android 6.0 - android 10.0 您需要在清单写入网络权限：
```
<uses-permission android:name="android.permission.INTERNET" />
```
##### 4.如果您有特殊权限可以获取到IMEI，那么您应当在获取权限后，再调用DeviceIdUtils.getDeviceId(context)
