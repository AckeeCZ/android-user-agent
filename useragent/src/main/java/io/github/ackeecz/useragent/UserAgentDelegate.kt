package io.github.ackeecz.useragent

import io.github.ackeecz.useragent.clientinfo.ClientInfo
import io.github.ackeecz.useragent.normalizer.Normalizer
import java.util.Locale

/**
 * Delegate for [UserAgent]. Contains as much [UserAgent] logic as possible that is abstracted away
 * from Android framework for better testability.
 */
internal class UserAgentDelegate(clientInfo: ClientInfo, private val normalizer: Normalizer?) {

    private val userAgentString = String.format(
        Locale.US,
        "%s/%s (%s; build:%d; Android %s; Model:%s)",
        clientInfo.appName,
        clientInfo.appVersionName,
        clientInfo.packageName,
        clientInfo.appVersionCode,
        clientInfo.androidVersion,
        clientInfo.deviceModel,
    )

    fun getNormalizedUserAgent(networkClientUserAgent: String): String {
        val notNormalizedUserAgent = String.format("%s %s", userAgentString, networkClientUserAgent)
        return normalizer?.normalize(notNormalizedUserAgent) ?: notNormalizedUserAgent
    }
}
