package ru.pmih.prog.commands;

import ru.pmih.prog.managers.CollectionManager;
import ru.pmih.prog.exceptions.IncorrectInputInScriptException;
import ru.pmih.prog.models.City;
import ru.pmih.prog.models.forms.CityForm;
import ru.pmih.prog.utility.Interrogator;
import java.time.LocalDate;

/**
 * Команда 'update'. Обновляет значение элемента коллекции, id которого равен заданному.
 * @author pmih
 */

public class UpdateCommand extends Command {
    private final CollectionManager collectionManager;

    public UpdateCommand(CollectionManager collectionManager) {
        super("update id {element}", "обновить значение элемента коллекции, id которого равен заданному");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument) throws IncorrectInputInScriptException {
        try {
            if (argument.isEmpty()) {
                Interrogator.getConsole().printError("Использование: 'update id'");
                return false;
            }
            long id = Long.parseLong(argument);
            City oldCity = collectionManager.getById(id);

            if (oldCity == null) {
                Interrogator.getConsole().printError("Город с таким ID не найден.");
                return false;
            }

            Interrogator.getConsole().println("* Обновление города (ID: " + id + ") *");
            City rawCity = new CityForm().build();

            // Создаем новый объект на основе введенных данных, но сохраняем старый ID
            City updatedCity = new City(
                    id,
                    rawCity.getName(),
                    rawCity.getCoordinates(),
                    LocalDate.now(),
                    rawCity.getArea(),
                    rawCity.getPopulation(),
                    rawCity.getMetersAboveSeaLevel(),
                    rawCity.getClimate(),
                    rawCity.getGovernment(),
                    rawCity.getStandardOfLiving(),
                    rawCity.getGovernor()
            );

            collectionManager.removeById(id);
            collectionManager.addElement(updatedCity);
            Interrogator.getConsole().println("Элемент успешно обновлен.");
            return true;
        } catch (NumberFormatException e) {
            Interrogator.getConsole().printError("ID должен быть числом.");
            return false;
        }
    }
}