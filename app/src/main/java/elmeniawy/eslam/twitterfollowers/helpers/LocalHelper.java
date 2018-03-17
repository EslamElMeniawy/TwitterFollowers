package elmeniawy.eslam.twitterfollowers.helpers;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;

import java.util.Locale;

import elmeniawy.eslam.twitterfollowers.utils.ConstantUtils;
import timber.log.Timber;

/**
 * LocalHelper
 * <p>
 * Created by Eslam El-Meniawy on 17-Mar-18.
 */

public class LocalHelper {
    public static void setLocale(Context context, String lang) {
        final Resources resources = context.getResources();
        final Configuration configuration = resources.getConfiguration();
        final Locale locale = getLocale(lang);

        if (!configuration.locale.equals(locale)) {
            Timber.i("Changing language.");
            configuration.setLocale(locale);
            resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        }
    }

    private static Locale getLocale(String lang) {
        return new Locale(lang.isEmpty() ? ConstantUtils.LANG_EN : lang);
    }
}
