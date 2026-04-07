package ru.pmih.prog.models.forms;

import ru.pmih.prog.exceptions.IncorrectInputInScriptException;
import ru.pmih.prog.models.*;
import ru.pmih.prog.utility.Interrogator;

/**
 * Главная форма для создания объекта City.
 * @author pmih
 */
public class CityForm extends Form<City> {
    @Override
    public City build() throws IncorrectInputInScriptException {
        return new City(
                null,
                askName(),
                new CoordinatesForm().build(),
                null,
                askArea(),
                askPopulation(),
                askMetersAboveSeaLevel(),
                new ClimateForm().build(),
                new GovernmentForm().build(),
                new StandardOfLivingForm().build(),
                new HumanForm().build()
        );
    }

    private String askName() {
        while (true) {
            Interrogator.getConsole().println("Введите название города:");
            String s = Interrogator.getConsole().readLine();
            if (s == null) System.exit(0);

            String input = s.trim();
            if (!input.isEmpty()) return input;
            Interrogator.getConsole().printError("Название не может быть пустым.");
        }
    }

    private Long askArea() {
        while (true) {
            try {
                Interrogator.getConsole().println("Введите площадь (Long, > 0):");
                String input = Interrogator.getConsole().readLine();
                if (input == null) System.exit(0);

                Long area = Long.parseLong(input.trim());
                if (area > 0) return area;
                Interrogator.getConsole().printError("Площадь должна быть больше 0.");
            } catch (NumberFormatException e) {
                Interrogator.getConsole().printError("Введите целое число.");
            }
        }
    }

    private Long askPopulation() {
        while (true) {
            try {
                Interrogator.getConsole().println("Введите население (Long, > 0):");
                String input = Interrogator.getConsole().readLine();
                if (input == null) System.exit(0);

                Long pop = Long.parseLong(input.trim());
                if (pop > 0) return pop;
                Interrogator.getConsole().printError("Население должно быть больше 0.");
            } catch (NumberFormatException e) {
                Interrogator.getConsole().printError("Введите целое число.");
            }
        }
    }

    private float askMetersAboveSeaLevel() {
        while (true) {
            try {
                Interrogator.getConsole().println("Введите высоту над уровнем моря (float):");
                String input = Interrogator.getConsole().readLine();
                if (input == null) System.exit(0);

                return Float.parseFloat(input.trim());
            } catch (NumberFormatException e) {
                Interrogator.getConsole().printError("Введите число с плавающей точкой.");
            }
        }
    }
}