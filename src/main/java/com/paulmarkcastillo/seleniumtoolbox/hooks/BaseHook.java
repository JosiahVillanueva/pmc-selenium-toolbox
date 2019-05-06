package com.paulmarkcastillo.seleniumtoolbox.hooks;

import com.paulmarkcastillo.seleniumtoolbox.configs.ConfigAndroid;
import com.paulmarkcastillo.seleniumtoolbox.utils.ScreenRecorderUtils;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class BaseHook {

    @SuppressWarnings("rawtypes")
    public static WebDriver driver;
    public static ConfigAndroid config = new ConfigAndroid();
    private ScreenRecorderUtils screenRecorder;

    protected void startRecording(String filename) {
        String artifactsPath = System.getProperty("user.dir") + "/target/cucumber-reports/android/";

        File androidDir = new File(artifactsPath);
        if (!androidDir.exists()) androidDir.mkdir();

        File recorded = new File(artifactsPath + filename + ".mp4");
        screenRecorder = new ScreenRecorderUtils(recorded);
        screenRecorder.startRecording();
    }

    protected void stopRecording() {
        screenRecorder.stopRecording();
    }

    protected void deleteRecording() {
        screenRecorder.deleteRecording();
    }
}
