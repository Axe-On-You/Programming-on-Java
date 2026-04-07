package ru.pmih.prog.utility;

import ru.pmih.prog.commands.Command;
import ru.pmih.prog.exceptions.IncorrectInputInScriptException;
import ru.pmih.prog.managers.CommandManager;
import ru.pmih.prog.utility.console.Console;

import java.util.NoSuchElementException;

/**
 * Класс для работы интерактивного режима (цикл чтения и выполнения команд).
 * @author pmih
 */

public class Runner {
    private final Console console;
    private final CommandManager commandManager;

    public Runner(Console console, CommandManager commandManager) {
        this.console = console;
        this.commandManager = commandManager;
    }

    public void interactiveMode() {
        console.println("Добро пожаловать! Введите 'help' для получения списка команд.");
        try {
            while (true) {
                console.print("> ");
                String input = console.readLine();

                if (input == null) {
                    console.println("\nЗавершение работы (Ctrl+D).");
                    System.exit(0);
                }

                input = input.trim();
                if (input.isEmpty()) continue;

                String[] tokens = (input + " ").split(" ", 2);
                String commandName = tokens[0].trim();
                String commandArgument = tokens[1].trim();

                Command command = commandManager.getCommands().get(commandName);
                if (command == null) {
                    console.printError("Команда '" + commandName + "' не найдена. Введите 'help' для справки.");
                    continue;
                }

                try {
                    command.execute(commandArgument);
                } catch (IncorrectInputInScriptException e) {
                    console.println("\n[!] Ошибка ввода в скрипте.");
                } catch (Exception e) {
                    console.printError("Произошла ошибка: " + e.getMessage());
                }
            }
        } catch (NoSuchElementException e) {
            console.printError("Пользовательский ввод не обнаружен.");
        }
    }
}