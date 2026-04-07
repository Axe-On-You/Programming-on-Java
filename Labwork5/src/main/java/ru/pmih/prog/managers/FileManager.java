package ru.pmih.prog.managers;

import ru.pmih.prog.models.*;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashSet;

/**
 * Класс для работы с файлом (загрузка и сохранение коллекции в формате CSV).
 * @author pmih
 */
public class FileManager {
    private final String fileName;
    private static final String CSV_SEPARATOR = ";";

    /**
     * Конструктор менеджера файлов.
     * @param fileName имя файла из переменной окружения или аргумента командной строки
     */
    public FileManager(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Сохраняет коллекцию в файл в формате CSV.
     * @param collection коллекция для сохранения
     */
    public void writeCollection(HashSet<City> collection) {
        if (fileName == null || fileName.isEmpty()) {
            System.out.println("Имя файла не указано. Сохранение невозможно.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("id;name;coord_x;coord_y;creationDate;area;population;metersAboveSeaLevel;climate;government;standardOfLiving;governorName");
            writer.newLine();

            for (City city : collection) {
                String[] cityData = {
                        city.getId().toString(),
                        city.getName(),
                        city.getCoordinates().getX().toString(),
                        String.valueOf(city.getCoordinates().getY()),
                        city.getCreationDate().toString(),
                        city.getArea().toString(),
                        city.getPopulation().toString(),
                        String.valueOf(city.getMetersAboveSeaLevel()),
                        city.getClimate().name(),
                        city.getGovernment() != null ? city.getGovernment().name() : "",
                        city.getStandardOfLiving() != null ? city.getStandardOfLiving().name() : "",
                        city.getGovernor() != null ? city.getGovernor().getName() : ""
                };
                writer.write(String.join(CSV_SEPARATOR, cityData));
                writer.newLine();
            }
            System.out.println("Коллекция успешно сохранена в файл: " + fileName);
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }

    /**
     * Считывает коллекцию из файла CSV.
     * @return загруженная коллекция (HashSet)
     */
    public HashSet<City> readCollection() {
        HashSet<City> collection = new HashSet<>();

        if (fileName == null || fileName.isEmpty()) {
            System.out.println("Имя файла не указано. Загружена пустая коллекция.");
            return collection;
        }

        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("Файл не найден. Будет создана новая пустая коллекция.");
            return collection;
        }
        if (!file.canRead()) {
            System.out.println("Нет прав на чтение файла!");
            return collection;
        }

        try (InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
             BufferedReader bufferedReader = new BufferedReader(reader)) {

            String line;
            boolean isFirstLine = true;

            while ((line = bufferedReader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                if (line.trim().isEmpty()) continue;

                String[] data = line.split(CSV_SEPARATOR, -1); // -1, чтобы сохранять пустые строки как элементы
                if (data.length < 12) {
                    System.out.println("Некорректная строка в файле: недостаточно данных. Пропуск...");
                    continue;
                }

                try {
                    // Парсинг данных из строки CSV
                    Long id = Long.parseLong(data[0]);
                    String name = data[1];
                    Coordinates coords = new Coordinates(Float.parseFloat(data[2]), Float.parseFloat(data[3]));
                    LocalDate creationDate = LocalDate.parse(data[4]);
                    Long area = Long.parseLong(data[5]);
                    Long population = Long.parseLong(data[6]);
                    float metersAboveSea = Float.parseFloat(data[7]);
                    Climate climate = Climate.valueOf(data[8]);

                    Government government = data[9].isEmpty() ? null : Government.valueOf(data[9]);
                    StandardOfLiving standardOfLiving = data[10].isEmpty() ? null : StandardOfLiving.valueOf(data[10]);
                    Human governor = data[11].isEmpty() ? null : new Human(data[11]);

                    City city = new City(id, name, coords, creationDate, area, population, metersAboveSea, climate, government, standardOfLiving, governor);
                    collection.add(city);

                } catch (IllegalArgumentException | DateTimeParseException e) {
                    System.out.println("Ошибка парсинга данных в строке: " + line + ". Пропуск...");
                }
            }
            System.out.println("Коллекция успешно загружена из файла. Элементов: " + collection.size());
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }

        return collection;
    }
}