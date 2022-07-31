package data.providers;

import data.models.DriverConfigs;
import exceptions.FrameworkFailedError;
import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

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
