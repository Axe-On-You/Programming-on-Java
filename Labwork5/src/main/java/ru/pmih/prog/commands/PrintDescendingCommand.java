package ru.pmih.prog.commands;

import ru.pmih.prog.managers.CollectionManager;
import ru.pmih.prog.utility.Interrogator;

/**
 * Команда 'print_descending'. Выводит элементы коллекции в порядке убывания.
 * @author pmih
 */

public class PrintDescendingCommand extends Command {
    private final CollectionManager collectionManager;

    public PrintDescendingCommand(CollectionManager collectionManager) {
        super("print_descending", "вывести элементы коллекции в порядке убывания");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument) {
        collectionManager.getDescendingList().forEach(city ->
                Interrogator.getConsole().println(city));
        return true;
    }
}