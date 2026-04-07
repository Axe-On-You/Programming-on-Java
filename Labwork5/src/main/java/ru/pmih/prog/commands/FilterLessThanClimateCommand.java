package ru.pmih.prog.commands;

import ru.pmih.prog.managers.CollectionManager;
import ru.pmih.prog.models.Climate;
import ru.pmih.prog.utility.Interrogator;

/**
 * Команда 'filter_less_than_climate'. Выводит элементы, значение поля climate которых меньше заданного.
 * @author pmih
 */

public class FilterLessThanClimateCommand extends Command {
    private final CollectionManager collectionManager;

    public FilterLessThanClimateCommand(CollectionManager collectionManager) {
        super("filter_less_than_climate climate", "вывести элементы, значение климата которых меньше заданного");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) {
                Interrogator.getConsole().printError("Введите значение Climate.");
                return false;
            }
            Climate target = Climate.valueOf(argument.toUpperCase());
            collectionManager.getCollection().stream()
                    .filter(c -> c.getClimate().compareTo(target) < 0)
                    .forEach(c -> Interrogator.getConsole().println(c));
            return true;
        } catch (IllegalArgumentException e) {
            Interrogator.getConsole().printError("Такого климата не существует.");
            return false;
        }
    }
}