package seleniumtoolbox.drivers;

import seleniumtoolbox.configs.BaseCommonConfig;
import seleniumtoolbox.configs.ConfigAndroid;
import seleniumtoolbox.hooks.HooksAndroid;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverAndroid {

    private ConfigAndroid config = new ConfigAndroid();

    @SuppressWarnings("rawtypes")
    public void mobileDevice() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        // ========== Standard Configs

        String automationName = "UiAutomator2";
        System.out.println("[CONFIG] automationName: " + automationName);
        capabilities.setCapability("automationName", automationName);

        String deviceName = config.commonDeviceName();
        System.out.println("[CONFIG] deviceName: " + deviceName);
        capabilities.setCapability("deviceName", deviceName);

        String deviceId = config.commonUdid();
        System.out.println("[CONFIG] deviceId: " + deviceId);
        capabilities.setCapability("udid", deviceId);

        String environment = BaseCommonConfig.getEnvironment("environment");
        if (environment == null || environment.equals("")) {
            environment = "qa";
        }
        System.out.println("[CONFIG] environment: " + environment);

        String app;
        String appPackage;

        switch (environment) {
        case "dev":
            app = config.getAppDev();
            appPackage = config.getAppPackageDev();
            break;
        case "qa":
            app = config.getAppQA();
            appPackage = config.getAppPackageQA();
            break;
        case "beta":
            app = config.getAppBeta();
            appPackage = config.getAppPackageBeta();
            break;
        case "prod":
            app = config.getAppProd();
            appPackage = config.getAppPackageProd();
            break;
        default:
            app = config.getAppQA();
            appPackage = config.getAppPackageQA();
            break;
        }

        boolean installed = false;
        String installedStr = BaseCommonConfig.getEnvironment("installed");
        if (installedStr != null && installedStr.equals("true")) {
            installed = true;
        }
        System.out.println("[CONFIG] installed: " + installed);

        if (!installed) {
            System.out.println("[CONFIG] app: " + app);
            capabilities.setCapability("app", app);

            boolean fullReset = true;
            System.out.println("[CONFIG] fullReset: " + fullReset);
            capabilities.setCapability("fullReset", fullReset);
        }

        // ========== Android Configs

        String appActivity = config.getAppActivity();
        System.out.println("[CONFIG] appActivity: " + appActivity);
        capabilities.setCapability("appActivity", appActivity);

        boolean allowTestPackages = true;
        System.out.println("[CONFIG] allowTestPackages: " + allowTestPackages);
        capabilities.setCapability("allowTestPackages", allowTestPackages);

        System.out.println("[CONFIG] appPackage: " + appPackage);
        capabilities.setCapability("appPackage", appPackage);

        boolean autoGrantPermissions = true;
        System.out.println("[CONFIG] autoGrantPermissions: " + autoGrantPermissions);
        capabilities.setCapability("autoGrantPermissions", autoGrantPermissions);

        // ========== Other Configs

        HooksAndroid.driver = new AndroidDriver<AndroidElement>(new URL(config.appiumURL()), capabilities);
    }
}
