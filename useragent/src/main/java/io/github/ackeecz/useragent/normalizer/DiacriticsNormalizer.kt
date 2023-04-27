package io.github.ackeecz.useragent.normalizer

import java.util.regex.Pattern

/**
 * Normalizes the user agent value by removing all diacritics
 */
class DiacriticsNormalizer : Normalizer {

    override fun normalize(userAgentValue: String): String {
        val nfdNormalizedString = java.text.Normalizer.normalize(userAgentValue, java.text.Normalizer.Form.NFD)
        val pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+")
        return pattern.matcher(nfdNormalizedString).replaceAll("")
    }
}
