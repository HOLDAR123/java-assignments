package assignment.first.model;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DataPool<T extends Entity> {
    private List<T> data;

    public DataPool() {
        this.data = new ArrayList<>();
    }

    public void add(T item) {
        if (item != null) {
            data.add(item);
        }
    }

    public void addAll(Collection<T> items) {
        if (items != null) {
            data.addAll(items);
        }
    }

    public List<T> getAll() {
        return new ArrayList<>(data);
    }

    public List<T> filter(Predicate<T> predicate) {
        return data.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    public Optional<T> search(Predicate<T> predicate) {
        return data.stream()
                .filter(predicate)
                .findFirst();
    }

    public List<T> searchAll(Predicate<T> predicate) {
        return data.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    public void sort(Comparator<T> comparator) {
        data.sort(comparator);
    }

    public List<T> getSorted(Comparator<T> comparator) {
        return data.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    public int size() {
        return data.size();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    public void clear() {
        data.clear();
    }

    public boolean remove(T item) {
        return data.remove(item);
    }
}
