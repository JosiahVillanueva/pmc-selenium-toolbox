package seleniumtoolbox.hooks;

import seleniumtoolbox.drivers.DriverAndroid;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

public class HooksAndroid extends BaseHook {

    private DriverAndroid mobileDevice = new DriverAndroid();

    @Before(order = 0)
    public void getLoadMobileDevice() throws MalformedURLException {
        mobileDevice.mobileDevice();
        driver
                .manage()
                .timeouts()
                .implicitlyWait(config.getImplicitlyWait(), TimeUnit.SECONDS);
    }

    @Before(order = 1)
    public void startScenario(Scenario scenario) {
        String recordingName = scenario.getName().replaceAll(" ", "_");
        startRecording(recordingName);
    }

    @SuppressWarnings("checkstyle:IllegalCatch")
    @After(order = 0)
    public void closeDriver(Scenario scenario) {
        stopRecording();

        if (scenario.isFailed()) {
            try {
                File fileSource = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

                String path = "target/cucumber-reports/android/";
                String filename = (scenario.getName() + ".png").replace(" ", "_");
                File fileDestination = new File(path + filename);

                FileUtils.copyFile(fileSource, fileDestination);

                System.out.println("Success taking screenshot: " + fileDestination.getAbsolutePath());
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Failed taking screenshot.");
            }
        } else {
            deleteRecording();
        }

        AndroidDriver<AndroidElement> driver = (AndroidDriver<AndroidElement>) super.driver;
        driver.closeApp();
        driver.quit();
    }
}
