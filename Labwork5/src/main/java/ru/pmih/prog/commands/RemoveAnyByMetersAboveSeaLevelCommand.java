package ru.pmih.prog.commands;

import ru.pmih.prog.managers.CollectionManager;
import ru.pmih.prog.models.City;
import ru.pmih.prog.utility.Interrogator;

/**
 * Команда 'remove_any_by_meters_above_sea_level'. Удаляет из коллекции один элемент, значение поля metersAboveSeaLevel которого эквивалентно заданному.
 * @author pmih
 */

public class RemoveAnyByMetersAboveSeaLevelCommand extends Command {
    private final CollectionManager collectionManager;

    public RemoveAnyByMetersAboveSeaLevelCommand(CollectionManager collectionManager) {
        super("remove_any_by_meters_above_sea_level meters", "удалить один элемент по значению высоты");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) {
                Interrogator.getConsole().printError("Введите значение metersAboveSeaLevel.");
                return false;
            }
            float meters = Float.parseFloat(argument);
            City toRemove = collectionManager.getCollection().stream()
                    .filter(c -> c.getMetersAboveSeaLevel() == meters)
                    .findFirst()
                    .orElse(null);

            if (toRemove != null) {
                collectionManager.getCollection().remove(toRemove);
                Interrogator.getConsole().println("Один элемент удален.");
                return true;
            }
            Interrogator.getConsole().println("Элемент с такой высотой не найден.");
            return false;
        } catch (NumberFormatException e) {
            Interrogator.getConsole().printError("Аргумент должен быть числом.");
            return false;
        }
    }
}