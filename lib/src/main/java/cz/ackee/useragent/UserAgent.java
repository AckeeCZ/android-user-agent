package cz.ackee.useragent;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;

import java.text.Normalizer;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * Singleton class that generates user agent string with app specific data for easier server device distinction
 */
public class UserAgent {
    public static final String TAG = UserAgent.class.getName();

    private static UserAgent instance;
    private final String userAgentString;

    /**
     * Getter for singleton instance
     *
     * @param ctx application Context
     * @return instance of UserAgent class
     */
    public static UserAgent getInstance(Context ctx) {
        if (instance == null) {
            instance = new UserAgent(ctx);
        }
        return instance;
    }

    /**
     * Private constructor that generates and caches User-Agent string
     *
     * @param ctx application Context
     */
    private UserAgent(Context ctx) {
        this.userAgentString = String.format(Locale.US, "%s/%s (%s; build:%d; Android %s; Model:%s)", getApplicationName(ctx), getVersionName(ctx), getPackageName(ctx), getVersionCode(ctx), getAndroidVersion(), getModel());
    }

    /**
     * Returns UserAgent string in format
     * "application_name/application_version (package_name; build:version_code; Android android_version; Model:device_model) networkClientUserAgent"
     *
     * @param networkClientUserAgent user agent of used http client. For okhttp/3.20 (Version.userAgent())
     * @return user agent string
     */
    public String getUserAgentString(String networkClientUserAgent) {
        return deaccent(String.format("%s %s", userAgentString, networkClientUserAgent));
    }

    /**
     * Get device model eg. Nexus 5X, SM-A310F etc.
     *
     * @return String device model
     */
    private String getModel() {
        return Build.MODEL;
    }

    /**
     * Get version of android as string eg. 4.1.2, 7.0.1 etc.
     *
     * @return String as android version
     */
    private String getAndroidVersion() {
        return Build.VERSION.RELEASE;
    }


    /**
     * Get application version code
     * <p>
     * If retrieving of version code fails for any reason, number 0 is returned as version code
     *
     * @param ctx application Context
     * @return integer as version code
     */
    private int getVersionCode(Context ctx) {
        try {
            return ctx.getPackageManager().getPackageInfo(ctx.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            return 0;
        }
    }

    /**
     * Get package name of application
     *
     * @param ctx application Context
     * @return String as package name
     */
    private String getPackageName(Context ctx) {
        return ctx.getPackageName();
    }

    /**
     * Get version name of application eg. 1.2.3
     * <p>
     * If retrieving of version names fails for any reason, string "Unknown" is returned as version name
     *
     * @param ctx application Context
     * @return String as version name
     */
    private String getVersionName(Context ctx) {
        try {
            return ctx.getPackageManager().getPackageInfo(ctx.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return "Unknown";
        }
    }

    /**
     * Get name of application that is defined in AndroidManifest by application entity
     *
     * @param context application Context
     * @return String app name
     */
    private String getApplicationName(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        int stringId = applicationInfo.labelRes;
        return stringId == 0 ? applicationInfo.nonLocalizedLabel.toString() : context.getString(stringId);
    }

    /**
     * Removes all the diacritics from the source string
     *
     * @param source the string you want to remove the diacritics from
     * @return the source string without any diacritics
     */
    private static String deaccent(String source) {
        String nfdNormalizedString = Normalizer.normalize(source, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("");
    }
}
