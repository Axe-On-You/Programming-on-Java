package ru.pmih.prog.models.forms;

import ru.pmih.prog.exceptions.IncorrectInputInScriptException;
import ru.pmih.prog.exceptions.MustBeNotEmptyException;
import ru.pmih.prog.exceptions.NotInDeclaredLimitsException;
import ru.pmih.prog.models.Coordinates;
import ru.pmih.prog.utility.Interrogator;

/**
 * Форма создания координат.
 * @author pmih
 */
public class CoordinatesForm extends Form<Coordinates> {

    @Override
    public Coordinates build() throws IncorrectInputInScriptException {
        return new Coordinates(askX(), askY());
    }

    private Float askX()  {
        while (true) {
            try {
                Interrogator.getConsole().println("Введите координату X (Float, > -223):");
                String input = Interrogator.getConsole().readLine();
                if (input == null) System.exit(0);

                input = input.trim();

                if (input.isEmpty()) throw new MustBeNotEmptyException();
                if (input.length() > 15) throw new NotInDeclaredLimitsException();

                double x = Double.parseDouble(input);
                if (x <= -223) throw new NotInDeclaredLimitsException();

                return (float) x;
            } catch (MustBeNotEmptyException e) {
                Interrogator.getConsole().printError("Значение X не может быть пустым.");
            } catch (NumberFormatException e) {
                Interrogator.getConsole().printError("X должен быть числом.");
            } catch (NotInDeclaredLimitsException e) {
                Interrogator.getConsole().printError("Число должно быть > -223 и не слишком длинным.");
            }
        }
    }

    private float askY() {
        while (true) {
            try {
                Interrogator.getConsole().println("Введите координату Y (float):");
                String input = Interrogator.getConsole().readLine();
                if (input == null) System.exit(0);

                input = input.trim();
                if (input.isEmpty()) throw new MustBeNotEmptyException();

                return Float.parseFloat(input);
            } catch (MustBeNotEmptyException e) {
                Interrogator.getConsole().printError("Значение Y не может быть пустым.");
            } catch (NumberFormatException e) {
                Interrogator.getConsole().printError("Y должен быть числом.");
            }
        }
    }
}