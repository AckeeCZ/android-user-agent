package io.github.ackeecz.useragent.normalizer

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

internal class DiacriticsNormalizerTest : FunSpec({

    test("removes all diacritics from the user agent value") {
        val userAgentWithDiacritics = "ÁĚỂĜĤḴĽŘŽ:ABCDE"

        val normalizedAgent = DiacriticsNormalizer().normalize(userAgentWithDiacritics)

        normalizedAgent shouldBe "AEEGHKLRZ:ABCDE"
    }
})
