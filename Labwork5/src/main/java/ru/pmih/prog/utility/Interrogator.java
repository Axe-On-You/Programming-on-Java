package ru.pmih.prog.utility;

import ru.pmih.prog.utility.console.Console;
import java.util.Scanner;

/**
 * Класс, хранящий состояние ввода (откуда читаем).
 * @author pmih
 */
public class Interrogator {
    private static Console console;
    private static Scanner userScanner;
    private static boolean fileMode = false;

    public static Console getConsole() {
        return console;
    }

    public static void setConsole(Console console) {
        Interrogator.console = console;
        Interrogator.userScanner = console.getScanner();
    }

    public static Scanner getUserScanner() {
        return userScanner;
    }

    public static boolean isFileMode() {
        return fileMode;
    }

    public static void setFileMode() {
        Interrogator.fileMode = true;
    }

    public static void setUserMode() {
        Interrogator.fileMode = false;
    }
}