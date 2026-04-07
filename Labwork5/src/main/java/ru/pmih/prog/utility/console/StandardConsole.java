package ru.pmih.prog.utility.console;

import java.util.Scanner;
import java.util.NoSuchElementException;

/**
 * Стандартная консоль для ввода из System.in и вывода в System.out.
 * @author pmih
 */

public class StandardConsole implements Console {
    private Scanner userScanner = new Scanner(System.in);

    @Override
    public void print(Object obj) { System.out.print(obj); }

    @Override
    public void println(Object obj) { System.out.println(obj); }

    @Override
    public void printError(Object obj) { System.err.println("Ошибка: " + obj); }

    @Override
    public String readLine() {
        try {
            if (!userScanner.hasNextLine()) return null;
            return userScanner.nextLine();
        } catch (IllegalStateException | NoSuchElementException e) {
            return null;
        }
    }

    @Override
    public boolean isCanRead() {
        try {
            return userScanner != null && userScanner.hasNextLine();
        } catch (IllegalStateException e) {
            return false;
        }
    }

    @Override
    public Scanner getScanner() { return userScanner; }

    @Override
    public void setScanner(Scanner scanner) { this.userScanner = scanner; }
}