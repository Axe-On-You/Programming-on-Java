package ru.pmih.prog.commands;

import ru.pmih.prog.managers.CommandManager;
import ru.pmih.prog.utility.console.Console;

/**
 * Команда 'help'. Выводит справку по доступным командам.
 * @author pmih
 */
public class HelpCommand extends Command {
    private final CommandManager commandManager;
    private final Console console;

    public HelpCommand(CommandManager commandManager, Console console) {
        super("help", "вывести справку по доступным командам");
        this.commandManager = commandManager;
        this.console = console;
    }

    @Override
    public boolean execute(String argument) {
        if (!argument.isEmpty()) {
            console.println("Использование: '" + getName() + "'");
            return false;
        }
        commandManager.getCommands().values().forEach(command -> {
            console.println(String.format(" %-35s : %s", command.getName(), command.getDescription()));
        });
        return true;
    }
}