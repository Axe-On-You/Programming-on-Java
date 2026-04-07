package ru.pmih.prog.commands;

import ru.pmih.prog.managers.CollectionManager;
import ru.pmih.prog.utility.Interrogator;

/**
 * Команда 'clear'. Очищает коллекцию.
 * @author pmih
 */
public class ClearCommand extends Command {
    private final CollectionManager collectionManager;

    public ClearCommand(CollectionManager collectionManager) {
        super("clear", "очистить коллекцию");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument) {
        if (!argument.isEmpty()) {
            Interrogator.getConsole().printError("Использование: '" + getName() + "'");
            return false;
        }
        collectionManager.clearCollection();
        Interrogator.getConsole().println("Коллекция успешно очищена.");
        return true;
    }
}