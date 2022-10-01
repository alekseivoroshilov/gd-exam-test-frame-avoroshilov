package helpers;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

public class PropertiesReader {
    Properties props = new Properties();

    public PropertiesReader() {
        try {
            load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getTarget() {
        String target = System.getProperty("target");

        if (Objects.equals(target, null)) {
            return props.getProperty("target");
        } else {
            return target;
        }
    }

    public Boolean getAutoStartServer() {
        String prop = System.getProperty("autoStartAppiumServer");

        if (Objects.equals(prop, null)) {
            String property = props.getProperty("autoStartAppiumServer");
            return property.equals("true");
        } else {
            return false;
        }
    }

    public void load() throws IOException {
        String propertyFileName = "env.properties";
        ClassLoader loader = Thread.currentThread().getContextClassLoader();

        try (InputStream stream = loader.getResourceAsStream(propertyFileName)) {
            props.load(stream);
        }
    }
}