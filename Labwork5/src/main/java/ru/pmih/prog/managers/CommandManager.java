package ru.pmih.prog.managers;

import ru.pmih.prog.commands.Command;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс для управления командами (регистрация и хранение).
 * @author pmih
 */
public class CommandManager {
    private final Map<String, Command> commands = new HashMap<>();
    private final List<String> commandHistory = new ArrayList<>();

    /**
     * Регистрирует новую команду.
     * @param commandName Имя команды
     * @param command Объект команды
     */
    public void register(String commandName, Command command) {
        commands.put(commandName, command);
    }

    /**
     * Возвращает коллекцию всех зарегистрированных команд.
     * @return Словарь команд
     */
    public Map<String, Command> getCommands() {
        return commands;
    }

    /**
     * Возвращает историю команд.
     * @return Список истории
     */
    public List<String> getCommandHistory() {
        return commandHistory;
    }

    /**
     * Добавляет команду в историю.
     * @param commandName Имя команды
     */
    public void addToHistory(String commandName) {
        commandHistory.add(commandName);
    }
}