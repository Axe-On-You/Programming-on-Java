package ru.pmih.prog.commands;

import ru.pmih.prog.utility.Interrogator;

/**
 * Команда 'exit'. Завершает программу (без сохранения в файл).
 * @author pmih
 */

public class ExitCommand extends Command {
    public ExitCommand() {
        super("exit", "завершить программу (без сохранения в файл)");
    }

    @Override
    public boolean execute(String argument) {
        Interrogator.getConsole().println("Завершение работы...");
        System.exit(0);
        return true;
    }
}