package io.github.ackeecz.useragent;

import android.content.Context;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import io.github.ackeecz.useragent.clientinfo.AndroidClientInfo;
import io.github.ackeecz.useragent.normalizer.DiacriticsNormalizer;
import io.github.ackeecz.useragent.normalizer.Normalizer;

/**
 * Class that generates user agent string with app specific data for easier server device distinction.
 */
public class UserAgent {

    private static UserAgent instance;

    private final UserAgentDelegate userAgentDelegate;

    /**
     * Getter for singleton instance
     *
     * @param ctx application Context
     * @return instance of UserAgent class
     * @deprecated Forcing a singleton instance in this class does not make sense anymore with added
     * support for custom normalization. Create instance by using a constructor and pass an
     * appropriate normalizer implementation or use an override which preserves the same normalization
     * behaviour as before. If you really need a singleton, you should manage it on your own
     * manually or using your DI solution.
     */
    @Deprecated
    public static UserAgent getInstance(Context ctx) {
        if (instance == null) {
            instance = new UserAgent(ctx);
        }
        return instance;
    }

    /**
     * Equivalent instance creation as by creating an instance using {@code getInstance}. It uses
     * a {@link DiacriticsNormalizer} which provides exactly the same normalization behaviour as a
     * deprecated singleton instance.
     */
    public UserAgent(@NotNull Context context) {
        this(context, new DiacriticsNormalizer());
    }

    /**
     * Creates a user agent with a custom {@link Normalizer} for normalizing a returned user agent
     * string value.
     *
     * @param normalizer Normalizer used for normalization of the user agent string. Passing null
     *                   disables all normalization.
     */
    public UserAgent(@NotNull Context context, @Nullable Normalizer normalizer) {
        userAgentDelegate = new UserAgentDelegate(new AndroidClientInfo(context), normalizer);
    }

    /**
     * By default returns UserAgent string in format:
     * "application_name/application_version (package_name; build:version_code; Android android_version; Model:device_model) networkClientUserAgent".
     * <p>
     * However, with added support for custom normalization, returned user agent string can be modified
     * arbitrarily.
     * </p>
     *
     * @param networkClientUserAgent user agent of used http client. For okhttp/3.20 (Version.userAgent())
     * @return user agent string
     */
    @NotNull
    public String getUserAgentString(String networkClientUserAgent) {
        return userAgentDelegate.getNormalizedUserAgent(networkClientUserAgent);
    }
}
