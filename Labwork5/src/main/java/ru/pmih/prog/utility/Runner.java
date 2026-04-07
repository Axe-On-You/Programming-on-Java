package ru.pmih.prog.utility;

import ru.pmih.prog.commands.Command;
import ru.pmih.prog.exceptions.IncorrectInputInScriptException;
import ru.pmih.prog.managers.CommandManager;
import ru.pmih.prog.utility.console.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class Runner {
    private final Console console;
    private final CommandManager commandManager;

    private final Stack<String> scriptStack = new Stack<>();

    public Runner(Console console, CommandManager commandManager) {
        this.console = console;
        this.commandManager = commandManager;
    }

    /**
     * Интерактивный режим (чтение из консоли).
     */
    public void interactiveMode() {
        console.println("Добро пожаловать! Введите 'help' для получения списка команд.");
        while (true) {
            console.print("> ");
            String input = console.readLine();
            if (input == null) break;

            processLine(input);
        }
    }

    /**
     * Режим выполнения скрипта.
     * @param filePath Путь к файлу.
     */
    public void scriptMode(String filePath) {
        File file = new File(filePath);
        // Проверка на рекурсию
        if (scriptStack.contains(file.getAbsolutePath())) {
            console.printError("Обнаружена рекурсия в скрипте: " + filePath);
            return;
        }

        scriptStack.push(file.getAbsolutePath());

        try (Scanner scriptScanner = new Scanner(file)) {
            Scanner oldScanner = Interrogator.getConsole().getScanner();
            Interrogator.getConsole().setScanner(scriptScanner);
            Interrogator.setFileMode();

            while (scriptScanner.hasNextLine()) {
                String line = scriptScanner.nextLine();
                if (line.trim().isEmpty()) continue;
                processLine(line);
            }

            Interrogator.getConsole().setScanner(oldScanner);
            Interrogator.setUserMode();

        } catch (FileNotFoundException e) {
            console.printError("Файл скрипта не найден: " + filePath);
        } finally {
            scriptStack.pop();
        }
    }

    /**
     * Общий метод для обработки одной строки (команда + аргумент).
     */
    private void processLine(String input) {
        input = input.trim();
        if (input.isEmpty()) return;

        String[] tokens = (input + " ").split(" ", 2);
        String commandName = tokens[0].trim();
        String commandArgument = tokens[1].trim();

        Command command = commandManager.getCommands().get(commandName);
        if (command == null) {
            console.printError("Команда '" + commandName + "' не найдена.");
            return;
        }

        try {
            command.execute(commandArgument);
        } catch (IncorrectInputInScriptException e) {
            console.printError("Ошибка ввода в скрипте.");
        } catch (Exception e) {
            console.printError("Ошибка: " + e.getMessage());
        }
    }
}