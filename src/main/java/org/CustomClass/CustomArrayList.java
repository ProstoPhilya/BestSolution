package org.CustomClass;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class CustomArrayList<T> implements Iterable<T> {
    private Object[] elements;
    private int size = 10;
    private int capacity = 0;

    public CustomArrayList() {
        elements = new Object[size];
    }

    public CustomArrayList(int size) {
        elements = new Object[size];
        this.size = size;
    }

    public int size() {
        return capacity;
    }

    public T get(int index) {
        indexValidation(index);
        return (T) elements[index];
    }

    public void add(T elemet) {
        if (capacity + 1 == size) resize();
        elements[capacity++] = elemet;
    }

    public void set(int index, T element) {
        indexValidation(index);
        elements[index] = element;
    }

    public void remove(int index) {
        indexValidation(index);

        if (index != capacity) {
            for (int i = index; i < capacity - 1; i++) {
                elements[i] = elements[i + 1];
            }
        }
        elements[capacity--] = null;
    }

    private void resize() {
        int newSize = size + (size >> 1);
        elements = Arrays.copyOf(elements, newSize);
        size = newSize;
    }

    public boolean isEmpty() {
        return capacity == 0;
    }

    public void print() {
        System.out.println(this);
    }

    public void println() {
        for (int i = 0; i < capacity; i++) {
            System.out.print(elements[i]);
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

    public void clear() {
        while (capacity != 0) {
            elements[capacity--] = null;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new CustomIterator();
    }

    @Override
    public String toString() {
        if (capacity == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < capacity; i++) {
            sb.append(elements[i]);
            if (i < capacity - 1) {
                sb.append(", ");
            }
        }
        sb.append(']');
        return sb.toString();
    }

    public void add(Barrel barrel) {

    }

    private class CustomIterator implements Iterator<T> {
        private int currentIndex = 0;
        private boolean canRemove = false;

        @Override
        public boolean hasNext() {
            return currentIndex < capacity;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            canRemove = true;
            return (T) elements[currentIndex++];
        }

        @Override
        public void remove() {
            if (!canRemove) {
                throw new IllegalStateException("метод next() должен быть вызван перед remove()");
            }
            CustomArrayList.this.remove(currentIndex - 1);
            currentIndex--;
            canRemove = false;
        }
    }
}
