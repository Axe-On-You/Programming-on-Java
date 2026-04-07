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

        // Создаем Runner до регистрации команд, которым он нужен (например, execute_script)
        Runner runner = new Runner(console, commandManager);

        // Базовые команды
        commandManager.register("help", new HelpCommand(commandManager, console));
        commandManager.register("info", new InfoCommand(collectionManager, console));
        commandManager.register("show", new ShowCommand(collectionManager, console));
        commandManager.register("exit", new ExitCommand());

        // Команды изменения коллекции
        commandManager.register("add", new AddCommand(collectionManager, console));
        commandManager.register("update", new UpdateCommand(collectionManager));
        commandManager.register("remove_by_id", new RemoveByIdCommand(collectionManager));
        commandManager.register("clear", new ClearCommand(collectionManager));
        commandManager.register("save", new SaveCommand(collectionManager));

        // Команды со скриптами
        commandManager.register("execute_script", new ExecuteScriptCommand(runner));

        // Условные команды и фильтрация
        commandManager.register("add_if_max", new AddIfMaxCommand(collectionManager));
        commandManager.register("remove_greater", new RemoveGreaterCommand(collectionManager));
        commandManager.register("remove_lower", new RemoveLowerCommand(collectionManager));

        // Специфические фильтры
        commandManager.register("remove_any_by_meters_above_sea_level", new RemoveAnyByMetersAboveSeaLevelCommand(collectionManager));
        commandManager.register("filter_less_than_climate", new FilterLessThanClimateCommand(collectionManager));
        commandManager.register("print_descending", new PrintDescendingCommand(collectionManager));

        // Запуск
        runner.interactiveMode();
    }
}