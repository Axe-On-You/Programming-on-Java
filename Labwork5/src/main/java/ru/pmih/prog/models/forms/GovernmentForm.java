package ru.pmih.prog.models.forms;

import ru.pmih.prog.exceptions.IncorrectInputInScriptException;
import ru.pmih.prog.models.Government;
import ru.pmih.prog.utility.Interrogator;
import java.util.Arrays;

/**
 * Форма выбора формы правления.
 * @author pmih
 */
public class GovernmentForm extends Form<Government> {
    @Override
    public Government build() {
        String input;
        while (true) {
            Interrogator.getConsole().println("Список доступных вариантов (Government): " + Arrays.toString(Government.values()));
            Interrogator.getConsole().println("Введите форму правления (пустая строка для null):");
            input = Interrogator.getConsole().readLine();

            if (input == null) System.exit(0);

            input = input.trim().toUpperCase();

            if (input.isEmpty()) return null;

            try {
                return Government.valueOf(input);
            } catch (IllegalArgumentException e) {
                Interrogator.getConsole().printError("Такой формы правления нет в списке.");
            }
        }
    }
}