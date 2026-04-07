package ru.pmih.prog.utility;

/**
 * Абстрактный класс для всех объектов, хранящихся в коллекции.
 * @author pmih
 */
public abstract class Element implements Comparable<Element> {
    public abstract Long getId();
    public abstract boolean validate();
}