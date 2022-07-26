package helpers;

import core.driver.DriverConfigs;
import exceptions.FrameworkFailedError;
import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class TestDataReader {
    public static DriverConfigs readConfig(String platform)  {
        try {
            String path = "config/" + platform + ".json";
            File file = new File(
                    TestDataReader.class.getClassLoader().getResource(path).getFile()
            );
            return new ObjectMapper().readValue(file, DriverConfigs.class);
        } catch (IOException e) {
            throw new FrameworkFailedError("Something went wrong while reading config: " + e.getMessage(), e);
        }
    }
}
