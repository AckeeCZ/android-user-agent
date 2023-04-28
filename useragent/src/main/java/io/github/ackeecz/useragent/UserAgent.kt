package io.github.ackeecz.useragent

import android.content.Context
import io.github.ackeecz.useragent.clientinfo.AndroidClientInfo
import io.github.ackeecz.useragent.normalizer.DiacriticsNormalizer
import io.github.ackeecz.useragent.normalizer.Normalizer

/**
 * Class that generates user agent string with app specific data for easier server device distinction.
 *
 * @constructor Creates a user agent with a custom [Normalizer] for normalizing a returned user agent
 * string value. Passing null disables all normalization. Using a default value is equivalent to
 * creating an instance using a deprecated [getInstance] method from a point of view of
 * normalization behaviour.
 */
class UserAgent @JvmOverloads constructor(context: Context, normalizer: Normalizer? = DiacriticsNormalizer()) {

    private val userAgentDelegate: UserAgentDelegate = UserAgentDelegate(AndroidClientInfo(context), normalizer)

    /**
     * By default returns UserAgent string in format:
     * "application_name/application_version (package_name; build:version_code; Android android_version; Model:device_model) networkClientUserAgent".
     *
     * However, with added support for custom normalization, returned user agent string can be modified
     * arbitrarily.
     *
     * @param networkClientUserAgent user agent of used http client. For okhttp/3.20 (Version.userAgent())
     * @return user agent string
     */
    fun getUserAgentString(networkClientUserAgent: String?): String {
        return userAgentDelegate.getNormalizedUserAgent(networkClientUserAgent)
    }

    companion object {

        private var instance: UserAgent? = null

        /**
         * Getter for singleton instance
         *
         * @param ctx application Context
         * @return instance of UserAgent class
         */
        @Deprecated(
            message = """Forcing a singleton instance in this class does not make sense anymore with added
            support for custom normalization. Create instance by using a constructor and pass an
            appropriate normalizer implementation or use an override which preserves the same normalization
            behaviour as before. If you really need a singleton, you should manage it on your own
            manually or using your DI solution.
            """,
            replaceWith = ReplaceWith(expression = "UserAgent(ctx)"),
            level = DeprecationLevel.WARNING,
        )
        @JvmStatic
        fun getInstance(ctx: Context): UserAgent {
            return instance ?: UserAgent(ctx).also { instance = it }
        }
    }
}
