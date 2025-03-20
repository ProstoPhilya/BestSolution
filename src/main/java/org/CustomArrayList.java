package org;

import java.util.Arrays;

public class CustomArrayList<T> {
    private Object[] data;
    private int size;

    public CustomArrayList(int size){
        this.data = new Object[size];
        this.size = size;
    }

    public void add(T element){
        if (size == data.length) {
            resize();
        }
        this.data[size++] = element;
    }
    @SuppressWarnings("unchecked")
    public T get(int index){
        return (T) data[index];
    }

    private void resize() {
        data = Arrays.copyOf(data, data.length + 10);
    }
}
