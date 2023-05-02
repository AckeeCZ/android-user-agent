package io.github.ackeecz.useragent.normalizer

import io.github.ackeecz.useragent.UserAgent

/**
 * Enables to normalize the final composed user agent value before it is returned from the
 * [UserAgent] to the client code. Clients can use one of the existing normalizers provided by this
 * library or create their own ones.
 */
interface Normalizer {

    fun normalize(userAgentValue: String): String
}
