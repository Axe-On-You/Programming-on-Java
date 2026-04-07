package ru.pmih.prog.commands;

import ru.pmih.prog.managers.CollectionManager;
import ru.pmih.prog.utility.Interrogator;

/**
 * Команда 'remove_by_id'. Удаляет элемент из коллекции по его id.
 * @author pmih
 */

public class RemoveByIdCommand extends Command {
    private final CollectionManager collectionManager;

    public RemoveByIdCommand(CollectionManager collectionManager) {
        super("remove_by_id id", "удалить элемент из коллекции по его id");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) {
                Interrogator.getConsole().printError("Использование: 'remove_by_id id'");
                return false;
            }
            long id = Long.parseLong(argument);
            if (collectionManager.getById(id) == null) {
                Interrogator.getConsole().printError("Элемента с таким ID нет.");
                return false;
            }
            collectionManager.removeById(id);
            Interrogator.getConsole().println("Элемент удален.");
            return true;
        } catch (NumberFormatException e) {
            Interrogator.getConsole().printError("ID должен быть числом.");
            return false;
        }
    }
}