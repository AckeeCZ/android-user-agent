package io.github.ackeecz.useragent.normalizer

/**
 * Normalizes the user agent value by replacing all non-ASCII characters with "?". If there are
 * characters with diacritics that are otherwise valid ASCII characters, then the diacritics is just
 * stripped away and characters are preserved without it in their "pure" ASCII form.
 */
class AsciiNormalizer : Normalizer {

    private val diacriticsNormalizer = DiacriticsNormalizer()

    override fun normalize(userAgentValue: String): String {
        val strippedOutDiacritics = diacriticsNormalizer.normalize(userAgentValue)
        return strippedOutDiacritics.replaceNonAsciiChars()
    }

    private fun String.replaceNonAsciiChars(): String {
        return map { char -> if (char.code in 0..127) char else NON_ASCII_REPLACE_CHAR }
            .toCharArray()
            .concatToString()
    }

    internal companion object {

        internal const val NON_ASCII_REPLACE_CHAR = '?'
    }
}
