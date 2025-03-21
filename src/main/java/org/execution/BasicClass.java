package org.execution;

public class BasicClass {
    private final int id;

    public BasicClass(int id) {
        this.id = id;
        System.out.println("Object with ID " + id + " created.");
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "(ID=" + id + ")";
    }
}
