package ru.pmih.prog.commands;

import ru.pmih.prog.managers.CollectionManager;
import ru.pmih.prog.exceptions.IncorrectInputInScriptException;
import ru.pmih.prog.models.City;
import ru.pmih.prog.models.forms.CityForm;
import ru.pmih.prog.utility.Interrogator;

/**
 * Команда 'remove_greater'. Удаляет из коллекции все элементы, превышающие заданный.
 * @author pmih
 */

public class RemoveGreaterCommand extends Command {
    private final CollectionManager collectionManager;

    public RemoveGreaterCommand(CollectionManager collectionManager) {
        super("remove_greater {element}", "удалить из коллекции все элементы, превышающие заданный");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument) throws IncorrectInputInScriptException {
        Interrogator.getConsole().println("* Создание эталонного города для сравнения *");
        City compareCity = new CityForm().build();

        int sizeBefore = collectionManager.getCollection().size();
        collectionManager.getCollection().removeIf(c -> c.compareTo(compareCity) > 0);

        Interrogator.getConsole().println("Удалено элементов: " + (sizeBefore - collectionManager.getCollection().size()));
        return true;
    }
}