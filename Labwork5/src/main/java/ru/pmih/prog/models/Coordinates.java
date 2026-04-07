package ru.pmih.prog.models;

/**
 * Класс, описывающий координаты города.
 * @author pmih
 */
public class Coordinates {
    private Float x; // Значение поля должно быть больше -223, Поле не может быть null
    private float y;

    public Coordinates(Float x, float y) {
        this.x = x;
        this.y = y;
    }

    // Геттеры и сеттеры
    public Float getX() { return x; }
    public void setX(Float x) { this.x = x; }

    public float getY() { return y; }
    public void setY(float y) { this.y = y; }

    @Override
    public String toString() {
        return "Coordinates{x=" + x + ", y=" + y + "}";
    }
}