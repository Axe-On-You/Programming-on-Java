package ru.pmih.prog.models.forms;

import ru.pmih.prog.exceptions.IncorrectInputInScriptException;
import ru.pmih.prog.models.Climate;
import ru.pmih.prog.utility.Interrogator;
import java.util.Arrays;

/**
 * Форма выбора климата.
 * @author pmih
 */
public class ClimateForm extends Form<Climate> {
    @Override
    public Climate build() {
        while (true) {
            Interrogator.getConsole().println("Список доступных вариантов (Climate): " + Arrays.toString(Climate.values()));
            Interrogator.getConsole().println("Введите климат:");
            String input = Interrogator.getConsole().readLine();

            if (input == null) System.exit(0);

            String name = input.trim().toUpperCase();

            try {
                return Climate.valueOf(name);
            } catch (IllegalArgumentException e) {
                Interrogator.getConsole().printError("Такого климата нет в списке.");
            }
        }
    }
}