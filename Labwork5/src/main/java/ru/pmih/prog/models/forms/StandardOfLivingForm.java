package ru.pmih.prog.models.forms;

import ru.pmih.prog.exceptions.IncorrectInputInScriptException;
import ru.pmih.prog.models.StandardOfLiving;
import ru.pmih.prog.utility.Interrogator;
import java.util.Arrays;

/**
 * Форма выбора уровня жизни.
 * @author pmih
 */
public class StandardOfLivingForm extends Form<StandardOfLiving> {
    @Override
    public StandardOfLiving build() {
        String input;
        while (true) {
            Interrogator.getConsole().println("Список доступных вариантов (StandardOfLiving): " + Arrays.toString(StandardOfLiving.values()));
            Interrogator.getConsole().println("Введите уровень жизни (пустая строка для null):");
            input = Interrogator.getConsole().readLine();

            if (input == null) System.exit(0);

            input = input.trim().toUpperCase();

            if (input.isEmpty()) return null;

            try {
                return StandardOfLiving.valueOf(input);
            } catch (IllegalArgumentException e) {
                Interrogator.getConsole().printError("Такого уровня жизни нет в списке.");
            }
        }
    }
}