package helpers;

import java.util.function.Function;

import static java.lang.System.getProperty;

public class PropertiesHelper {
    public static <T> T getSysPropOrDefault (String property, T defaultProp, Function<String, T> function) {
        String prop = getProperty(property);
        return prop == null ? defaultProp : function.apply(prop);
    }

    public static String getStringPropertyOrDef(String property, String defVal) {
        return getSysPropOrDefault(property, defVal, String::toString);
    }
}
