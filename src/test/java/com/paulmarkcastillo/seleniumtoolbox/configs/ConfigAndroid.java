package com.paulmarkcastillo.seleniumtoolbox.configs;

public class ConfigAndroid extends BaseAppiumConfig {
    public ConfigAndroid() {
        super(
                "local_android.properties",
                "src/test/resources/project_stayhealthy/configs/ConfigurationAndroid.properties");
    }

    public String getAppActivity() {
        return getProperty("appActivity");
    }

    public String getAppPackageQA() {
        return getProperty("appPackageQA");
    }

    public String getAppPackageDev() {
        return getProperty("appPackageDev");
    }

    public String getAppPackageBeta() {
        return getProperty("appPackageBeta");
    }

    public String getAppPackageProd() {
        return getProperty("appPackageProd");
    }

    public String getAutoGrantPermissions() {
        return getProperty("autoGrantPermissions");
    }
}
