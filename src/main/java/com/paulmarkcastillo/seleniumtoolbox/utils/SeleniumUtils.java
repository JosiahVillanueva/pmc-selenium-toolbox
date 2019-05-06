package com.paulmarkcastillo.seleniumtoolbox.utils;

import com.paulmarkcastillo.seleniumtoolbox.hooks.HooksAndroid;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertNotNull;

public class SeleniumUtils {

    private static final int SWIPE_SPEED_MILLIS = 500;
    private static final int MAX_TRIES = 5;

    @Contract(pure = true)
    private static double getPercentage(double num) {
        return num / 100;
    }

    public static void singleTap(
            @NotNull AppiumDriver driver,
            @NotNull WebElement element) {
        int x1 = element.getLocation().getX();
        int y1 = element.getLocation().getY();

        Dimension size = element.getSize();
        int x2 = x1 + size.width;
        int y2 = y1 + size.height;

        int targetX = (x1 + x2) / 2;
        int targetY = (y1 + y2) / 2;

        tap(driver, targetX, targetY);
    }

    public static void doubleTap(
            @NotNull AppiumDriver driver,
            @NotNull WebElement element) {
        int x1 = element.getLocation().getX();
        int y1 = element.getLocation().getY();

        Dimension size = element.getSize();
        int x2 = x1 + size.width;
        int y2 = y1 + size.height;

        int targetX = (x1 + x2) / 2;
        int targetY = (y1 + y2) / 2;

        tap(driver, targetX, targetY);
        tap(driver, targetX, targetY);
    }

    private static void tap(
            @NotNull AppiumDriver driver,
            int targetX,
            int targetY) {
        TouchAction action = new TouchAction(driver);
        System.out.println("[TEST] Tapping: X=" + targetX + ";Y=" + targetY);
        action.tap(PointOption.point(targetX, targetY))
                .tap(PointOption.point(targetX, targetY))
                .perform();
    }

    public static void swipeRightToLeft(
            @NotNull AppiumDriver driver,
            @NotNull WebElement element) {
        int x1 = element.getLocation().getX();
        int y1 = element.getLocation().getY();

        Dimension size = element.getSize();
        int x2 = x1 + size.width;
        int y2 = y1 + size.height;

        int startX = x1 + (int) (size.width * getPercentage(60));
        int endX = x1 + (int) (size.width * getPercentage(30));

        int startY = (y1 + y2) / 2;
        int endY = startY;

        swipe(driver, startX, startY, endX, endY);
    }

    public static void swipeRightToLeftCompletely(
            @NotNull AppiumDriver driver,
            @NotNull WebElement element) {
        int x1 = element.getLocation().getX();
        int y1 = element.getLocation().getY();

        Dimension size = element.getSize();
        int x2 = x1 + size.width;
        int y2 = y1 + size.height;

        int startX = x1 + (int) (size.width * getPercentage(99));
        int endX = x1 + (int) (size.width * getPercentage(1));

        int startY = (y1 + y2) / 2;
        int endY = startY;

        swipe(driver, startX, startY, endX, endY);
    }

    public static void swipeLeftToRight(
            @NotNull AppiumDriver driver,
            @NotNull WebElement element) {
        int x1 = element.getLocation().getX();
        int y1 = element.getLocation().getY();

        Dimension size = element.getSize();
        int x2 = x1 + size.width;
        int y2 = y1 + size.height;

        int startX = x1 + (int) (size.width * getPercentage(30));
        int endX = x1 + (int) (size.width * getPercentage(60));

        int startY = (y1 + y2) / 2;
        int endY = startY;

        swipe(driver, startX, startY, endX, endY);
    }

    public static void swipeLeftToRightCompletely(
            @NotNull AppiumDriver driver,
            @NotNull WebElement element) {
        int x1 = element.getLocation().getX();
        int y1 = element.getLocation().getY();

        Dimension size = element.getSize();
        int x2 = x1 + size.width;
        int y2 = y1 + size.height;

        int startX = x1 + (int) (size.width * getPercentage(1));
        int endX = x1 + (int) (size.width * getPercentage(99));

        int startY = (y1 + y2) / 2;
        int endY = startY;

        swipe(driver, startX, startY, endX, endY);
    }

    public static void swipeBottomToTop(
            @NotNull AppiumDriver driver,
            @NotNull WebElement element) {
        int x1 = element.getLocation().getX();
        int y1 = element.getLocation().getY();

        Dimension size = element.getSize();
        int x2 = x1 + size.width;
        int y2 = y1 + size.height;

        int startX = (x1 + x2) / 2;
        int endX = startX;

        int startY = y1 + (int) (size.height * getPercentage(60));
        int endY = y1 + (int) (size.height * getPercentage(30));

        swipe(driver, startX, startY, endX, endY);
    }

    public static void swipeTopToBottom(
            @NotNull AppiumDriver driver,
            @NotNull WebElement element) {
        int x1 = element.getLocation().getX();
        int y1 = element.getLocation().getY();

        Dimension size = element.getSize();
        int x2 = x1 + size.width;
        int y2 = y1 + size.height;

        int startX = (x1 + x2) / 2;
        int endX = startX;

        int startY = y1 + (int) (size.height * getPercentage(30));
        int endY = y1 + (int) (size.height * getPercentage(60));

        swipe(driver, startX, startY, endX, endY);
    }

    public static WebElement scrollToElement(
            @NotNull AndroidDriver driver,
            @NotNull By by) {
        return scrollToElement(null, driver, by);
    }

    private static final String DIRECTION_DOWNWARDS = "down";
    private static final String DIRECTION_UPWARDS = "up";

    public static WebElement scrollToElement(
            String name,
            @NotNull AndroidDriver driver,
            @NotNull By by) {

        setImplicitWait(2);

        if (name != null && !name.equals("")) System.out.println("[TEST] Finding: " + name);

        WebElement webElement = scrollToElementDirection(name, driver, by, DIRECTION_DOWNWARDS);
        if (webElement == null) webElement = scrollToElementDirection(name, driver, by, DIRECTION_UPWARDS);

        if (webElement == null) {
            if (name != null && !name.equals(""))
                System.out.println("[TEST] [FAILED] Unable to find: " + name);
            else
                System.out.println("[TEST] [FAILED] Unable to find element");
        }

        setImplicitWait(HooksAndroid.config.getImplicitlyWait());

        assertNotNull(webElement);

        return webElement;
    }

    @SuppressWarnings({
            "checkstyle:CyclomaticComplexity"})
    public static WebElement scrollToElementDirection(
            String name,
            @NotNull AndroidDriver driver,
            @NotNull By by,
            @NotNull String direction) {

        WebElement webElement = null;
        boolean found = false;
        int tries = MAX_TRIES;

        do {
            try {
                webElement = findElement(driver, by);
                if (webElement != null && webElement.isDisplayed()) {
                    if (name != null && !name.equals("")) System.out.println("[TEST] Found: " + name);
                    found = true;
                }
            } catch (NoSuchElementException e) {
                if (name != null && !name.equals(""))
                    System.out.println("[TEST] Unable to find: " + name + ", scrolling " + direction + "... " + tries);
                else
                    System.out.println("[TEST] Unable to find element, scrolling " + direction + "... " + tries);

                if (direction.equals("down")) scrollBottomToTop(driver);
                else if (direction.equals("up")) scrollTopToBottom(driver);

                tries--;
            }
        } while (!found && tries > 0);

        return webElement;
    }

    public static void setImplicitWait(long seconds) {
        HooksAndroid.driver
                .manage()
                .timeouts()
                .implicitlyWait(seconds, TimeUnit.SECONDS);
    }

    public static void hideKeyboard(@NotNull AndroidDriver driver) {
        if (!HooksAndroid.config.getDisableKeypad()) {
            if (driver.isKeyboardShown()) {
                try {
                    driver.hideKeyboard();
                } catch (WebDriverException ignored) {
                }
            }
        }
    }

    public static WebElement findElement(
            @NotNull AndroidDriver driver,
            @NotNull By by) {
        return findElement(null, driver, by);
    }

    public static WebElement findElement(
            String name,
            @NotNull AndroidDriver driver,
            @NotNull By by) {

        hideKeyboard(driver);

        if (name != null && !name.equals("")) System.out.println("[TEST] Finding: " + name);

        FluentWait<AndroidDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofMinutes(1))
                .pollingEvery(Duration.ofSeconds(1));

        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public static void scrollBottomToTop(
            @NotNull AppiumDriver driver) {
        Dimension dimension = driver.manage().window().getSize();
        int startY = (int) (dimension.height * getPercentage(60));
        int endY = (int) (dimension.height * getPercentage(30));
        int startX = dimension.width / 2;
        int endX = startX;

        swipe(driver, startX, startY, endX, endY);
    }

    public static void scrollTopToBottom(
            @NotNull AppiumDriver driver) {
        Dimension dimension = driver.manage().window().getSize();
        int startY = (int) (dimension.height * getPercentage(30));
        int endY = (int) (dimension.height * getPercentage(60));
        int startX = dimension.width / 2;
        int endX = startX;

        swipe(driver, startX, startY, endX, endY);
    }

    private static void swipe(
            @NotNull AppiumDriver driver,
            int startX,
            int startY,
            int endX,
            int endY) {
        TouchAction action = new TouchAction(driver);
        try {
            action.press(new PointOption().withCoordinates(startX, startY))
                    .waitAction(new WaitOptions().withDuration(Duration.ofMillis(SWIPE_SPEED_MILLIS)))
                    .moveTo(new PointOption().withCoordinates(endX, endY))
                    .release()
                    .perform();
        } catch (WebDriverException e) {
        }
    }
}
