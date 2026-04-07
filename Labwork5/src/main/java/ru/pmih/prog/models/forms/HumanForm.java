package ru.pmih.prog.models.forms;

import ru.pmih.prog.exceptions.IncorrectInputInScriptException;
import ru.pmih.prog.models.Human;
import ru.pmih.prog.utility.Interrogator;

/**
 * Форма создания правителя (Human).
 * @author pmih
 */
public class HumanForm extends Form<Human> {
    @Override
    public Human build() {
        Interrogator.getConsole().println("Введите имя правителя (пустая строка — оставить без правителя):");
        String name = Interrogator.getConsole().readLine();

        if (name == null) System.exit(0);

        name = name.trim();

        if (name.isEmpty()) return null;
        return new Human(name);
    }
}