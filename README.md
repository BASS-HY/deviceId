# deviceId 快速获取Android 唯一识别码
[！[]（https://jitpack.io/v/BASS-HY/deviceId.svg）]（https://jitpack.io/#BASS-HY/deviceId）

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
### 只需调用  ```DeviceIdUtils.getDeviceId(context)``` 即可,该方法将返回一个String值(MD5加密后的32位字符串)，返回的string值即为获得的唯一识别码

# 更多用法：

```
DeviceIdUtils.getDeviceId(this@MainActivity,upperCase = false)
upperCase 代表加密后的字符串中的字母是否使用大写;默认为false(不转化成大写)


DeviceIdUtils.getDeviceId(this@MainActivity,useMd5 = true)
useMd5 代表是否使用MD5加密获取到的唯一识别码;默认为true(使用MD5加密后再返回)


DeviceIdUtils.getDeviceId(this@MainActivity,useSSAID = true)
useSSAID 代表是否在IMEI无法获取时，将SSAID纳入考虑范围，默认为true(纳入考虑范围)
SSAID 提供了一个在由同一开发者签名密钥签名的应用之间通用的标识，如果同一个APP,使用了不同的签名打包,那么您应当将该值设置为false;
```
# 注意：
##### 导入该项目，您将实现快速获取android 设备唯一识别码！！！
##### Android 6.0以下，将直接获取到IMEI进行MD5加密并返回；
##### android 6.0 - android 10.0 您需要在清单写入网络权限：
```
<uses-permission android:name="android.permission.INTERNET" />
```
