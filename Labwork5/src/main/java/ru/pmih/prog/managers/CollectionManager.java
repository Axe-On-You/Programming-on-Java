package ru.pmih.prog.managers;

import ru.pmih.prog.models.City;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Collections;

/**
 * Класс для управления самой коллекцией объектов City.
 * @author pmih
 */
public class CollectionManager {
    private HashSet<City> collection = new HashSet<>();
    private final LocalDate lastInitTime;
    private final FileManager fileManager;

    public CollectionManager(FileManager fileManager) {
        this.lastInitTime = LocalDate.now();
        this.fileManager = fileManager;
    }

    public void loadCollection() {
        this.collection = fileManager.readCollection();
    }

    public HashSet<City> getCollection() {
        return collection;
    }

    public LocalDate getLastInitTime() {
        return lastInitTime;
    }

    public String getCollectionType() {
        return collection.getClass().getName();
    }

    public int collectionSize() {
        return collection.size();
    }

    public void clearCollection() {
        collection.clear();
    }

    public void addElement(City city) {
        collection.add(city);
    }

    /**
     * Генерирует новый уникальный ID для города на основе текущих элементов.
     * @return уникальный ID
     */
    public Long generateNextId() {
        if (collection.isEmpty()) return 1L;
        return collection.stream()
                .mapToLong(City::getId)
                .max()
                .getAsLong() + 1;
    }

    public City getById(Long id) {
        return collection.stream()
                .filter(city -> city.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void removeById(Long id) {
        collection.removeIf(city -> city.getId().equals(id));
    }

    /**
     * @return Список городов, отсортированный в обратном порядке (для команды print_descending)
     */
    public List<City> getDescendingList() {
        return collection.stream()
                .sorted(Collections.reverseOrder())
                .collect(Collectors.toList());
    }

    public void saveCollection() {
        fileManager.writeCollection(collection);
    }

    @Override
    public String toString() {
        if (collection.isEmpty()) return "Коллекция пуста.";
        StringBuilder info = new StringBuilder();
        for (City city : collection) {
            info.append(city).append("\n");
        }
        return info.toString().trim();
    }
}