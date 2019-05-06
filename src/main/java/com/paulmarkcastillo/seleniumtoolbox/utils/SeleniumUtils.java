package com.paulmarkcastillo.seleniumtoolbox.utils;

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

    private AndroidDriver driver;
    private long implicitWait;
    private boolean keyboardDisabled;

    public SeleniumUtils(
            AndroidDriver driver,
            long implicitWait,
            boolean keyboardDisabled) {
        this.driver = driver;
        this.implicitWait = implicitWait;
        this.keyboardDisabled = keyboardDisabled;
    }

    @Contract(pure = true)
    private double getPercentage(double num) {
        return num / 100;
    }

    public void singleTap(@NotNull WebElement element) {
        int x1 = element.getLocation().getX();
        int y1 = element.getLocation().getY();

        Dimension size = element.getSize();
        int x2 = x1 + size.width;
        int y2 = y1 + size.height;

        int targetX = (x1 + x2) / 2;
        int targetY = (y1 + y2) / 2;

        tap(targetX, targetY);
    }

    public void doubleTap(@NotNull WebElement element) {
        int x1 = element.getLocation().getX();
        int y1 = element.getLocation().getY();

        Dimension size = element.getSize();
        int x2 = x1 + size.width;
        int y2 = y1 + size.height;

        int targetX = (x1 + x2) / 2;
        int targetY = (y1 + y2) / 2;

        tap(targetX, targetY);
        tap(targetX, targetY);
    }

    private void tap(int targetX,
                     int targetY) {
        TouchAction action = new TouchAction(driver);
        System.out.println("[TEST] Tapping: X=" + targetX + ";Y=" + targetY);
        action.tap(PointOption.point(targetX, targetY))
                .tap(PointOption.point(targetX, targetY))
                .perform();
    }

    public void swipeRightToLeft(@NotNull WebElement element) {
        int x1 = element.getLocation().getX();
        int y1 = element.getLocation().getY();

        Dimension size = element.getSize();
        int x2 = x1 + size.width;
        int y2 = y1 + size.height;

        int startX = x1 + (int) (size.width * getPercentage(60));
        int endX = x1 + (int) (size.width * getPercentage(30));

        int startY = (y1 + y2) / 2;
        int endY = startY;

        swipe(startX, startY, endX, endY);
    }

    public void swipeRightToLeftCompletely(@NotNull WebElement element) {
        int x1 = element.getLocation().getX();
        int y1 = element.getLocation().getY();

        Dimension size = element.getSize();
        int x2 = x1 + size.width;
        int y2 = y1 + size.height;

        int startX = x1 + (int) (size.width * getPercentage(99));
        int endX = x1 + (int) (size.width * getPercentage(1));

        int startY = (y1 + y2) / 2;
        int endY = startY;

        swipe(startX, startY, endX, endY);
    }

    public void swipeLeftToRight(@NotNull WebElement element) {
        int x1 = element.getLocation().getX();
        int y1 = element.getLocation().getY();

        Dimension size = element.getSize();
        int x2 = x1 + size.width;
        int y2 = y1 + size.height;

        int startX = x1 + (int) (size.width * getPercentage(30));
        int endX = x1 + (int) (size.width * getPercentage(60));

        int startY = (y1 + y2) / 2;
        int endY = startY;

        swipe(startX, startY, endX, endY);
    }

    public void swipeLeftToRightCompletely(@NotNull WebElement element) {
        int x1 = element.getLocation().getX();
        int y1 = element.getLocation().getY();

        Dimension size = element.getSize();
        int x2 = x1 + size.width;
        int y2 = y1 + size.height;

        int startX = x1 + (int) (size.width * getPercentage(1));
        int endX = x1 + (int) (size.width * getPercentage(99));

        int startY = (y1 + y2) / 2;
        int endY = startY;

        swipe(startX, startY, endX, endY);
    }

    public void swipeBottomToTop(@NotNull WebElement element) {
        int x1 = element.getLocation().getX();
        int y1 = element.getLocation().getY();

        Dimension size = element.getSize();
        int x2 = x1 + size.width;
        int y2 = y1 + size.height;

        int startX = (x1 + x2) / 2;
        int endX = startX;

        int startY = y1 + (int) (size.height * getPercentage(60));
        int endY = y1 + (int) (size.height * getPercentage(30));

        swipe(startX, startY, endX, endY);
    }

    public void swipeTopToBottom(@NotNull WebElement element) {
        int x1 = element.getLocation().getX();
        int y1 = element.getLocation().getY();

        Dimension size = element.getSize();
        int x2 = x1 + size.width;
        int y2 = y1 + size.height;

        int startX = (x1 + x2) / 2;
        int endX = startX;

        int startY = y1 + (int) (size.height * getPercentage(30));
        int endY = y1 + (int) (size.height * getPercentage(60));

        swipe(startX, startY, endX, endY);
    }

    public WebElement scrollToElement(@NotNull By by) {
        return scrollToElement(null, by);
    }

    private static final String DIRECTION_DOWNWARDS = "down";
    private static final String DIRECTION_UPWARDS = "up";

    public WebElement scrollToElement(
            String name, @NotNull By by) {

        setImplicitWait(2);

        if (name != null && !name.equals("")) System.out.println("[TEST] Finding: " + name);

        WebElement webElement = scrollToElementDirection(name, by, DIRECTION_DOWNWARDS);
        if (webElement == null) webElement = scrollToElementDirection(name, by, DIRECTION_UPWARDS);

        if (webElement == null) {
            if (name != null && !name.equals(""))
                System.out.println("[TEST] [FAILED] Unable to find: " + name);
            else
                System.out.println("[TEST] [FAILED] Unable to find element");
        }

        setImplicitWait(implicitWait);

        assertNotNull(webElement);

        return webElement;
    }

    @SuppressWarnings({
            "checkstyle:CyclomaticComplexity"})
    public WebElement scrollToElementDirection(
            String name,
            @NotNull By by,
            @NotNull String direction) {

        WebElement webElement = null;
        boolean found = false;
        int tries = MAX_TRIES;

        do {
            try {
                webElement = findElement(by);
                if (webElement != null && webElement.isDisplayed()) {
                    if (name != null && !name.equals("")) System.out.println("[TEST] Found: " + name);
                    found = true;
                }
            } catch (NoSuchElementException e) {
                if (name != null && !name.equals(""))
                    System.out.println("[TEST] Unable to find: " + name + ", scrolling " + direction + "... " + tries);
                else
                    System.out.println("[TEST] Unable to find element, scrolling " + direction + "... " + tries);

                if (direction.equals("down")) scrollBottomToTop();
                else if (direction.equals("up")) scrollTopToBottom();

                tries--;
            }
        } while (!found && tries > 0);

        return webElement;
    }

    public void setImplicitWait(long seconds) {
        driver
                .manage()
                .timeouts()
                .implicitlyWait(seconds, TimeUnit.SECONDS);
    }

    public void hideKeyboard() {
        if (!keyboardDisabled) {
            if (driver.isKeyboardShown()) {
                try {
                    driver.hideKeyboard();
                } catch (WebDriverException ignored) {
                }
            }
        }
    }

    public WebElement findElement(@NotNull By by) {
        return findElement(null, by);
    }

    public WebElement findElement(
            String name,
            @NotNull By by) {

        hideKeyboard();

        if (name != null && !name.equals("")) System.out.println("[TEST] Finding: " + name);

        FluentWait<AndroidDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofMinutes(1))
                .pollingEvery(Duration.ofSeconds(1));

        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void scrollBottomToTop() {
        Dimension dimension = driver.manage().window().getSize();
        int startY = (int) (dimension.height * getPercentage(60));
        int endY = (int) (dimension.height * getPercentage(30));
        int startX = dimension.width / 2;
        int endX = startX;

        swipe(startX, startY, endX, endY);
    }

    public void scrollTopToBottom() {
        Dimension dimension = driver.manage().window().getSize();
        int startY = (int) (dimension.height * getPercentage(30));
        int endY = (int) (dimension.height * getPercentage(60));
        int startX = dimension.width / 2;
        int endX = startX;

        swipe(startX, startY, endX, endY);
    }

    private void swipe(
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
