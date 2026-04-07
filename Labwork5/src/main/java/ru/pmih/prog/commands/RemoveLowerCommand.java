package ru.pmih.prog.commands;

import ru.pmih.prog.managers.CollectionManager;
import ru.pmih.prog.exceptions.IncorrectInputInScriptException;
import ru.pmih.prog.models.City;
import ru.pmih.prog.models.forms.CityForm;
import ru.pmih.prog.utility.Interrogator;

/**
 * Команда 'remove_lower'. Удаляет из коллекции все элементы, меньшие, чем заданный.
 * @author pmih
 */

public class RemoveLowerCommand extends Command {
    private final CollectionManager collectionManager;

    public RemoveLowerCommand(CollectionManager collectionManager) {
        super("remove_lower {element}", "удалить из коллекции все элементы, не превышающие заданный");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument) throws IncorrectInputInScriptException {
        Interrogator.getConsole().println("* Создание эталонного города для сравнения *");
        City compareCity = new CityForm().build();

        int sizeBefore = collectionManager.getCollection().size();
        collectionManager.getCollection().removeIf(c -> c.compareTo(compareCity) < 0);

        Interrogator.getConsole().println("Удалено элементов: " + (sizeBefore - collectionManager.getCollection().size()));
        return true;
    }
}