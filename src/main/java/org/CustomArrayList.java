package org;

import java.util.Arrays;

public class CustomArrayList<T> {
    private Object[] array;
    private int size = 10;
    private int capacity = 0;

    public CustomArrayList() {
        array = new Object[size];
    }

    public CustomArrayList(int size) {
        array = new Object[size];
        this.size = size;
    }

    public int size() {
        return capacity;
    }

    public T get(int index) {
        indexValidation(index);
        return (T) array[index];
    }

    public void add(T elemet) {
        if (capacity + 1 == size) resize();
        array[capacity++] = elemet;
    }

    public void set(int index, T element) {
        indexValidation(index);
        array[index] = element;
    }

    public void remove(int index) {
        indexValidation(index);

        if (index != capacity) {
            for (int i = index; i < capacity - 1; i++) {
                array[i] = array[i + 1];
            }
        }
        array[capacity--] = null;
    }

    private void resize() {
        int newSize = size + (size >> 1);
        array = Arrays.copyOf(array, newSize);
        size = newSize;
    }

    public boolean isEmpty() {
        return capacity == 0;
    }

    public void print() {
        System.out.println(this);
    }

    //  объекты выводятся построчно
    public void println() {
        for (int i = 0; i < capacity; i++) {
            System.out.print(array[i]);
            if (i < capacity - 1)
                System.out.println(", ");
        }
        System.out.println();
    }

    private void indexValidation(int index) {
        if (index < 0 || index >= capacity) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + capacity);
        }
    }

    @Override
    public String toString() {
        if (capacity == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('[');

        for (int i = 0; i < capacity; i++) {
            sb.append(array[i]);
            if (i < capacity - 1) {
                sb.append(", ");
            }
        }

        sb.append(']');
        return sb.toString();
    }
}
