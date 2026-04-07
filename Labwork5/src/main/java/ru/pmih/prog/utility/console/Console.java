package ru.pmih.prog.utility.console;

import java.util.Scanner;

/**
 * Интерфейс для управления вводом и выводом.
 * @author pmih
 */
public interface Console {
    void print(Object obj);
    void println(Object obj);
    void printError(Object obj);
    String readLine();
    boolean isCanRead();
    Scanner getScanner();
    void setScanner(Scanner scanner);
}