package ru.pmih.prog.commands;

import ru.pmih.prog.exceptions.IncorrectInputInScriptException;

/**
 * Интерфейс для исполняемых команд.
 * @author pmih
 */
public interface Executable {
    /**
     * Выполняет команду.
     * @param argument аргумент, переданный команде.
     * @return статус выполнения команды.
     */
    boolean execute(String argument) throws IncorrectInputInScriptException;
}