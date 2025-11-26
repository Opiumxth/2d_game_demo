package util;

import java.util.ArrayList;
import java.util.List;

public class Inventory<T> {
    private List<T> items;
    public Inventory() {
        items = new ArrayList<>();
    }
    // Agregar un objeto al inventario
    public void add(T item) {
        items.add(item);
    }
    // Remover un objeto
    public void remove(T item) {
        items.remove(item);
    }
    // Obtener todos los objetos
    public List<T> getItems() {
        return items;
    }
    // Saber cuántos objetos hay
    public int size() {
        return items.size();
    }
    // Ver si contiene un objeto
    public boolean contains(T item) {
        return items.contains(item);
    }
}