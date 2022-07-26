package core.driver;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonInclude;
import io.qameta.allure.internal.shadowed.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DriverConfigs {

    @JsonProperty("type")
    private String type;

    @JsonProperty("device.name")
    private String deviceName;

    @JsonProperty("platform.name")
    private String platformName;

    @JsonProperty("app")
    private String app;

    @JsonProperty("udid")
    private String udid;            // not really needed, since I will use only simulators

    @JsonProperty("url")
    private String url;

    @JsonProperty("command.timeout")
    private int timeout;

    @JsonProperty("bundleId")
    private String bundleId;

    @JsonProperty("automationName")
    private String automationName;

    /**
     * that's why we use lombok
     */
    public String getType() {
        return type;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getPlatformName() {
        return platformName;
    }

    public String getApp() {
        return app;
    }

    public String getUdid() {
        return udid;
    }

    public String getUrl() {
        return url;
    }

    public int getTimeout() {
        return timeout;
    }

    public String getBundleId() { return bundleId; }

    public String getAutomationName() { return automationName; }
}
