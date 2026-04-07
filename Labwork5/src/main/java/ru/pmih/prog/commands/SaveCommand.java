package ru.pmih.prog.commands;

import ru.pmih.prog.managers.CollectionManager;

/**
 * Команда 'save'. Сохраняет коллекцию в файл.
 * @author pmih
 */

public class SaveCommand extends Command {
    private final CollectionManager collectionManager;

    public SaveCommand(CollectionManager collectionManager) {
        super("save", "сохранить коллекцию в файл");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument) {
        collectionManager.saveCollection();
        return true;
    }
}