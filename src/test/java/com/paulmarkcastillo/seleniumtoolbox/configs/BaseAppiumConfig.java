package com.paulmarkcastillo.seleniumtoolbox.configs;

public abstract class BaseAppiumConfig extends BaseCommonConfig {

    BaseAppiumConfig(String localConfig, String defaultConfig) {
        super(localConfig, defaultConfig);
    }

    // ==========  Standard Configs

    public String getAutomationName() {
        return getProperty("automationName");
    }

    public String getPlatformName() {
        return getProperty("platformName");
    }

    public String getPlatformVersion() {
        return getProperty("platformVersion");
    }

    public String getDeviceName() {
        return getProperty("deviceName");
    }

    public String getAppDev() {
        return getProperty("appDev");
    }

    public String getAppQA() {
        return getProperty("appQA");
    }

    public String getAppBeta() {
        return getProperty("appBeta");
    }

    public String getAppProd() {
        return getProperty("appProd");
    }

    public String getUdid() {
        return getProperty("udid");
    }

    public String getNewCommandTimeout() {
        return getProperty("newCommandTimeout");
    }

    // ==========  Other Configs

    public String appiumVersion() {
        return getProperty("appiumVersion");
    }

    public String appiumURL() {
        return getProperty("appiumURL");
    }

    public String getDevice() {
        return getProperty("device");
    }

    public boolean getDisableKeypad() {
        String disableKeypad = getProperty("disable_keypad");
        return disableKeypad != null && disableKeypad.equals("true");
    }

    public String commonDeviceName() {
        String systemDeviceName = BaseCommonConfig.getEnvironment("deviceName");
        return systemDeviceName == null || systemDeviceName.isEmpty() ? getDeviceName() : systemDeviceName;
    }

    public String commonUdid() {
        String systemUdid = BaseCommonConfig.getEnvironment("udid");
        return systemUdid == null || systemUdid.isEmpty() ? getUdid() : systemUdid;
    }
}
