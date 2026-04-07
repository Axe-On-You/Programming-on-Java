package ru.pmih.prog.commands;

import ru.pmih.prog.exceptions.IncorrectInputInScriptException;
import ru.pmih.prog.managers.CollectionManager;
import ru.pmih.prog.models.City;
import ru.pmih.prog.models.forms.CityForm;
import ru.pmih.prog.utility.Interrogator;
import ru.pmih.prog.utility.console.Console;

import java.time.LocalDate;

/**
 * Команда 'add'. Добавляет новый элемент в коллекцию.
 * @author pmih
 */
public class AddCommand extends Command {
    private final CollectionManager collectionManager;

    public AddCommand(CollectionManager collectionManager, Console console) {
        super("add {element}", "добавить новый элемент в коллекцию");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) {
                Interrogator.getConsole().printError("Использование: 'add'");
                return false;
            }

            Interrogator.getConsole().println("* Создание нового города *");

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

            collectionManager.addElement(cityToAdd);
            Interrogator.getConsole().println("Город успешно добавлен в коллекцию!");
            return true;
        } catch (NullPointerException | IncorrectInputInScriptException e) {
            Interrogator.getConsole().println("\nВвод остановлен.");
            return false; // Просто выходим из команды в Runner
        }
    }
}