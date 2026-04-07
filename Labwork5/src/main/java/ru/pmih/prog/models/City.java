package ru.pmih.prog.models;

import java.time.LocalDate;
import ru.pmih.prog.utility.Element;

/**
 * Класс City (Город), объекты которого хранятся в коллекции.
 * Реализует интерфейс Comparable для сортировки по умолчанию (по имени города).
 * @author pmih
 */
public class City extends Element {
    private Long id; // Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; // Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; // Поле не может быть null
    private java.time.LocalDate creationDate; // Поле не может быть null, генерируется автоматически
    private Long area; // Значение поля должно быть больше 0, Поле не может быть null
    private Long population; // Значение поля должно быть больше 0, Поле не может быть null
    private float metersAboveSeaLevel;
    private Climate climate; // Поле не может быть null
    private Government government; // Поле может быть null
    private StandardOfLiving standardOfLiving; // Поле может быть null
    private Human governor; // Поле может быть null

    /**
     * Конструктор для создания нового города пользователем.
     * ID и дата создания генерируются автоматически в менеджерах, здесь мы их просто устанавливаем.
     */
    public City(Long id, String name, Coordinates coordinates, LocalDate creationDate, Long area,
                Long population, float metersAboveSeaLevel, Climate climate,
                Government government, StandardOfLiving standardOfLiving, Human governor) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.area = area;
        this.population = population;
        this.metersAboveSeaLevel = metersAboveSeaLevel;
        this.climate = climate;
        this.government = government;
        this.standardOfLiving = standardOfLiving;
        this.governor = governor;
    }

    // Геттеры
    @Override
    public Long getId() { return id; }

    @Override
    public boolean validate() {
        if (id == null || id <= 0) return false;
        if (name == null || name.isEmpty()) return false;
        if (coordinates == null) return false;
        if (creationDate == null) return false;
        if (area == null || area <= 0) return false;
        if (population == null || population <= 0) return false;
        return climate != null;
    }

    public String getName() { return name; }
    public Coordinates getCoordinates() { return coordinates; }
    public LocalDate getCreationDate() { return creationDate; }
    public Long getArea() { return area; }
    public Long getPopulation() { return population; }
    public float getMetersAboveSeaLevel() { return metersAboveSeaLevel; }
    public Climate getClimate() { return climate; }
    public Government getGovernment() { return government; }
    public StandardOfLiving getStandardOfLiving() { return standardOfLiving; }
    public Human getGovernor() { return governor; }

    /**
     * Сортировка по умолчанию (по имени города).
     */
    @Override
    public int compareTo(Element other) {
        return this.id.compareTo(other.getId());
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", area=" + area +
                ", population=" + population +
                ", metersAboveSeaLevel=" + metersAboveSeaLevel +
                ", climate=" + climate +
                ", government=" + government +
                ", standardOfLiving=" + standardOfLiving +
                ", governor=" + governor +
                '}';
    }
}