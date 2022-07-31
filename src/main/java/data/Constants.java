package data;

import static data.Constants.Devices.ANDROID;
import static data.Constants.Devices.IOS;
import static data.Constants.SystemProperties.PLATFORM;
import static data.helpers.Helper.getStringPropertyOrDef;

public class Constants {

    public static final class SystemProperties {
        public static final String PLATFORM = "platform";
    }

    public static final class Devices {
        public final static String IOS = "ios";
        public final static String ANDROID = "android";
    }

    public static final class Configuration {
        public final static String TEST_PLATFORM = getStringPropertyOrDef(PLATFORM, IOS).toLowerCase();
        public final static boolean IS_IOS = TEST_PLATFORM.equals(IOS);
        public final static boolean IS_ANDROID = TEST_PLATFORM.equals(ANDROID);
    }

    public static final class Locators {
        public final static String ID = "com.movinapp.dict.english.american:id/";
        public final static String ANDROID_ID = "android:id/";
    }

    public static final class Texts {
        public final static String STARTUP_TITLE_POPUP = "English Dictionary";
        public final static String STARTUP_EULA_TITLE = "EULA";
        public final static String WELCOME_MSG = "Welcome to The Advanced English Dictionary!";
        public final static String MENU_FAVORITES = "Favorites";
        public final static String MENU_CLR_HISTORY = "Clear history";
        public final static String MENU_SHARE_APP = "Share application";
        public final static String MENU_MORE_APPS = "More apps";
        public final static String MENU_ABOUT = "About";
        public final static String MENU_EXPORT_FAVS = "Export favorites";
        public final static String MENU_CLEAR_FAVS = "Clear favorites";
        public final static String MENU_BACKUP_FAVS = "Backup favorites";
        public final static String MENU_RESTORE_FAVS = "Restore favorites";
        public final static String POPUP_DELETE_FAVS_MSG = "This action will DELETE your current favorites list. Are you sure?";
        public final static String POPUP_OVERWRITE_BACKUP = "This action will overwrite your previous favorites backup. Are you sure?";
        public final static String ABOUT_MSG = "WordNet 3.0 Copyright 2006 by Princeton University. All rights reserved.";
    }

    public static final class Words {
        public final static String WORD1 = "perpetual";
        public final static String WORD2 = "ubiquitous";
        public final static String WORD3 = "marvelous";
        public final static String WORD4 = "grid";
        public final static String WORD5 = "dynamics";
        public final static String WORD6 = "please";
        public final static String WORD7 = "accept";
        public final static String WORD8 = "my";
        public final static String WORD9 = "frame";
        public final static String WRONG_WORD = "heyyosupmydawg";
        public final static String ADVERB = "[adv.]";
        public final static String NOUN = "[n.]";
    }
}
