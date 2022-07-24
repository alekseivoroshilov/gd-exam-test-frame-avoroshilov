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
}
