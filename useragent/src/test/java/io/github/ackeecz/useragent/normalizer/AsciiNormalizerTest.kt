package io.github.ackeecz.useragent.normalizer

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

internal class AsciiNormalizerTest : FunSpec({

    val underTest = AsciiNormalizer()

    test("returns all valid ASCII characters as expected") {
        val expectedAsciiChars = """ !"#$%&'()*+,-./0123456789:;<%>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\]^_`abcdefghijklmnopqrstuvwxyz{|}~"""

        val actual = underTest.normalize(expectedAsciiChars)

        actual shouldBe expectedAsciiChars
    }

    test("strips out diacritics from otherwise valid ASCII characters") {
        val actual = underTest.normalize("ÁĚĜĤ")

        actual shouldBe "AEGH"
    }

    test("replaces non-ASCII characters with ${AsciiNormalizer.NON_ASCII_REPLACE_CHAR}") {
        val actual = underTest.normalize("mı 8 lite")

        actual shouldBe "m${AsciiNormalizer.NON_ASCII_REPLACE_CHAR} 8 lite"
    }
})

