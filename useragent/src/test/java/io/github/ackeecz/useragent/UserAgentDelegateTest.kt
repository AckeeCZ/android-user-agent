package io.github.ackeecz.useragent

import io.github.ackeecz.useragent.clientinfo.ClientInfo
import io.github.ackeecz.useragent.clientinfo.FakeClientInfo
import io.github.ackeecz.useragent.normalizer.DiacriticsNormalizer
import io.github.ackeecz.useragent.normalizer.Normalizer
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

internal class UserAgentDelegateTest : FunSpec({

    val clientInfo = FakeClientInfo()

    fun underTest(normalizer: Normalizer?) = UserAgentDelegate(clientInfo, normalizer)

    fun ClientInfo.getExpectedUserAgent(
        networkClientUserAgent: String,
        deviceModel: String = this.deviceModel,
    ): String {
        val buildPart = "build:$appVersionCode"
        val androidPart = "Android $androidVersion"
        val modelPart = "Model:$deviceModel"
        return "$appName/$appVersionName ($packageName; $buildPart; $androidPart; $modelPart) $networkClientUserAgent"
    }

    test("get user agent value without normalization") {
        clientInfo.deviceModel = "ÁĚĜĤ"
        val networkClientUserAgent = "okhttp/4.9.3"

        val userAgentValue = underTest(normalizer = null).getNormalizedUserAgent(networkClientUserAgent)

        userAgentValue shouldBe clientInfo.getExpectedUserAgent(networkClientUserAgent = networkClientUserAgent)
    }

    test("get user agent value with normalized diacritics") {
        clientInfo.deviceModel = "ÁĚĜĤ"

        val userAgentValue = underTest(DiacriticsNormalizer()).getNormalizedUserAgent("")

        userAgentValue shouldBe clientInfo.getExpectedUserAgent(networkClientUserAgent = "", deviceModel = "AEGH")
    }
})
