package ru.pmih.prog.commands;

import ru.pmih.prog.utility.Runner;

/**
 * Команда 'execute_script'. Считывает и исполняет скрипт из указанного файла.
 * @author pmih
 */
public class ExecuteScriptCommand extends Command {
    private final Runner runner;

    public ExecuteScriptCommand(Runner runner) {
        super("execute_script file_name", "считать и исполнить скрипт из указанного файла");
        this.runner = runner;
    }

    @Override
    public boolean execute(String argument) {
        if (argument.isEmpty()) {
            System.err.println("Использование: execute_script <file_name>");
            return false;
        }

        runner.scriptMode(argument);
        return true;
    }
}