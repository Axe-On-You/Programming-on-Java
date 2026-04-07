package ru.pmih.prog.models;

/**
 * Класс, описывающий правителя (человека).
 * @author pmih
 */
public class Human {
    private String name; // Поле не может быть null, Строка не может быть пустой

    public Human(String name) {
        this.name = name;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @Override
    public String toString() {
        return "Human{name='" + name + "'}";
    }
}