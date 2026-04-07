package ru.pmih.prog.commands;

import ru.pmih.prog.managers.CollectionManager;
import ru.pmih.prog.utility.console.Console;

/**
 * Команда 'show'. Выводит все элементы коллекции.
 * @author pmih
 */
public class ShowCommand extends Command {
    private final CollectionManager collectionManager;
    private final Console console;

    public ShowCommand(CollectionManager collectionManager, Console console) {
        super("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        this.collectionManager = collectionManager;
        this.console = console;
    }

    @Override
    public boolean execute(String argument) {
        if (!argument.isEmpty()) {
            console.println("Использование: '" + getName() + "'");
            return false;
        }
        if (collectionManager.getCollection().isEmpty()) {
            console.println("Коллекция пуста.");
            return true;
        }
        collectionManager.getCollection().forEach(city -> console.println(city.toString()));
        return true;
    }
}