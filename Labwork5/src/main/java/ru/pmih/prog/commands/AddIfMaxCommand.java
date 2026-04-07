package ru.pmih.prog.commands;

import ru.pmih.prog.managers.CollectionManager;
import ru.pmih.prog.exceptions.IncorrectInputInScriptException;
import ru.pmih.prog.models.City;
import ru.pmih.prog.models.forms.CityForm;
import ru.pmih.prog.utility.Interrogator;
import java.time.LocalDate;

/**
 * Команда 'add_if_max'. Добавляет новый элемент в коллекцию, если его значение превышает значение наибольшего элемента.
 * @author pmih
 */

public class AddIfMaxCommand extends Command {
    private final CollectionManager collectionManager;

    public AddIfMaxCommand(CollectionManager collectionManager) {
        super("add_if_max {element}", "добавить элемент, если он больше наибольшего в коллекции");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument) throws IncorrectInputInScriptException {
        Interrogator.getConsole().println("* Создание города для сравнения (max) *");
        City rawCity = new CityForm().build();

        City cityToAdd = new City(
                collectionManager.generateNextId(),
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

        City maxCity = collectionManager.getCollection().stream()
                .max(City::compareTo)
                .orElse(null);

        if (maxCity == null || cityToAdd.compareTo(maxCity) > 0) {
            collectionManager.addElement(cityToAdd);
            Interrogator.getConsole().println("Город добавлен (он максимальный).");
            return true;
        } else {
            Interrogator.getConsole().println("Город не добавлен: он меньше максимального.");
            return false;
        }
    }
}