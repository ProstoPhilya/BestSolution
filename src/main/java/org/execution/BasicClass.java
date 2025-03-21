package org.execution;

import org.CustomClass.Enums.Classes;

public class BasicClass<T> {
    private Object element;

    public BasicClass(Classes nameClass) {
        this.element = element;
    }

    public T getElement() {
        return (T) element;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "(ID=" + id + ")";
    }
}
