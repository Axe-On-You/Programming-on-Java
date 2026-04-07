package ru.pmih.prog;

import ru.pmih.prog.commands.*;
import ru.pmih.prog.managers.CollectionManager;
import ru.pmih.prog.managers.CommandManager;
import ru.pmih.prog.managers.FileManager;
import ru.pmih.prog.utility.Interrogator;
import ru.pmih.prog.utility.Runner;
import ru.pmih.prog.utility.console.Console;
import ru.pmih.prog.utility.console.StandardConsole;

/**
 * Главный класс приложения.
 * @author pmih
 */
public class Main {
    public static void main(String[] args) {
        Console console = new StandardConsole();
        Interrogator.setConsole(console);

        if (args.length == 0) {
            console.printError("Имя файла должно передаваться как аргумент командной строки!");
            console.println("Пример: java -jar app.jar data.csv");
            return;
        }

        String fileName = args[0];

        FileManager fileManager = new FileManager(fileName);
        CollectionManager collectionManager = new CollectionManager(fileManager);
        CommandManager commandManager = new CommandManager();

        collectionManager.loadCollection();

        commandManager.register("help", new HelpCommand(commandManager, console));
        commandManager.register("info", new InfoCommand(collectionManager, console));
        commandManager.register("show", new ShowCommand(collectionManager, console));
        commandManager.register("add", new AddCommand(collectionManager, console));
        // TODO: Здесь мы будем регистрировать остальные команды

        Runner runner = new Runner(console, commandManager);
        runner.interactiveMode();
    }
}