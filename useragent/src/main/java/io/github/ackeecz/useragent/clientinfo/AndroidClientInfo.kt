package io.github.ackeecz.useragent.clientinfo

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build

internal class AndroidClientInfo(context: Context) : ClientInfo {

    private val context = context.applicationContext

    override val appName: String
        get() {
            val applicationInfo = context.applicationInfo
            val stringId = applicationInfo.labelRes
            return if (stringId == 0) applicationInfo.nonLocalizedLabel.toString() else context.getString(stringId)
        }

    override val appVersionName: String
        get() {
            return try {
                context.packageManager.getPackageInfo(context.packageName, 0).versionName
            } catch (e: PackageManager.NameNotFoundException) {
                "Unknown"
            }
        }

    override val packageName: String get() = context.packageName

    override val appVersionCode: Int
        get() {
            return try {
                context.packageManager.getPackageInfo(context.packageName, 0).versionCode
            } catch (e: PackageManager.NameNotFoundException) {
                0
            }
        }

    override val androidVersion: String get() = Build.VERSION.RELEASE

    override val deviceModel: String get() = Build.MODEL
}
