package io.github.ackeecz.useragent.clientinfo

/**
 * Provides all client app information needed to build a user agent header value
 */
internal interface ClientInfo {

    /**
     * Get name of application that is defined in AndroidManifest by application entity
     */
    val appName: String

    /**
     * Get version name of application eg. 1.2.3
     *
     * If retrieving of version name fails for any reason, string "Unknown" is returned as version name
     */
    val appVersionName: String

    /**
     * Get application version code
     *
     * If retrieving of version code fails for any reason, number 0 is returned as version code
     */
    val appVersionCode: Int

    /**
     * Get package name of application
     */
    val packageName: String

    /**
     * Get version of Android as string eg. 4.1.2, 7.0.1 etc.
     */
    val androidVersion: String

    /**
     * Get device model eg. Nexus 5X, SM-A310F etc.
     */
    val deviceModel: String
}
