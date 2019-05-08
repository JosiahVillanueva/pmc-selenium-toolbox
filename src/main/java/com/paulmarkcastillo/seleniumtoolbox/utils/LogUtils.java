package com.paulmarkcastillo.seleniumtoolbox.utils;

public class LogUtils {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\033[0;32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    //info
    public static void i(String className, String message) {
        System.out.println(ANSI_GREEN + className + " : " + message + ANSI_RESET);
    }

    //error
    public static void e(String className, String message) {
        System.out.println(ANSI_RED + className + " : " + message + ANSI_RESET);
    }

    //debug
    public static void d(String className, String message) {
        System.out.println(ANSI_BLUE + className + " : " + message + ANSI_RESET);
    }

    //warning
    public static void w(String className, String message) {
        System.out.println(ANSI_YELLOW + className + " : " + message + ANSI_RESET);
    }

    public static void loaded(String message) {
        System.out.println(ANSI_CYAN + "[LOADED]: " + message + ANSI_RESET);
    }

    public static void scenarioStart(String title) {
        System.out.println(ANSI_YELLOW + "\n\n[SCENARIO][START]: " + title + ANSI_RESET);
    }

    public static void scenarioPassed(String title) {
        System.out.println(ANSI_YELLOW + "[SCENARIO][PASSED] : " + title + ANSI_RESET);
        System.out.println("\n\n");
    }

    public static void scenarioFailed(String title) {
        System.out.println(ANSI_RED + "[SCENARIO][FAILED] : " + title + ANSI_RESET);
        System.out.println("\n\n");
    }

    public static void stepPassed(String message) {
        System.out.println(ANSI_GREEN + "  [STEP][PASSED]: " + message + ANSI_RESET);
    }

    public static void stepInfo(String message) {
        System.out.println(ANSI_CYAN + "  [INFO]: " + message + ANSI_RESET);
    }

    public static void stepFailed(String message) {
        System.out.println(ANSI_RED + "  [STEP][FAILED]: " + message + ANSI_RESET);
    }
}
