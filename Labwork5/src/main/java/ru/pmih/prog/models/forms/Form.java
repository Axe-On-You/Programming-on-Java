package ru.pmih.prog.models.forms;

import ru.pmih.prog.exceptions.IncorrectInputInScriptException;

/**
 * Общий интерфейс для форм ввода объектов.
 * @author pmih
 * @param <T> тип создаваемого объекта
 */
public abstract class Form<T> {
    public abstract T build() throws IncorrectInputInScriptException;
}