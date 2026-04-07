package ru.pmih.prog.commands;

import ru.pmih.prog.managers.CollectionManager;
import ru.pmih.prog.utility.console.Console;

/**
 * Команда 'info'. Выводит информацию о коллекции.
 * @author pmih
 */
public class InfoCommand extends Command {
    private final CollectionManager collectionManager;
    private final Console console;

    public InfoCommand(CollectionManager collectionManager, Console console) {
        super("info", "вывести в стандартный поток вывода информацию о коллекции");
        this.collectionManager = collectionManager;
        this.console = console;
    }

    @Override
    public boolean execute(String argument) {
        if (!argument.isEmpty()) {
            console.println("Использование: '" + getName() + "'");
            return false;
        }
        console.println("Сведения о коллекции:");
        console.println(" Тип: " + collectionManager.getCollectionType());
        console.println(" Дата инициализации: " + collectionManager.getLastInitTime());
        console.println(" Количество элементов: " + collectionManager.collectionSize());
        return true;
    }
}