package org.CustomClass;

// Кастомный класс Бочка с использованием Builder
class Barrel implements Comparable<Barrel> {
    private double volume;
    private String storedMaterial;
    private String material;

    private Barrel(Builder builder) {
        this.volume = builder.volume;
        this.storedMaterial = builder.storedMaterial;
        this.material = builder.material;
    }

    public double getVolume() {
        return volume;
    }

    public String getStoredMaterial() {
        return storedMaterial;
    }

    public String getMaterial() {
        return material;
    }

    @Override
    public int compareTo(Barrel other) {
        return Double.compare(this.volume, other.volume);
    }

    @Override
    public String toString() {
        return "Barrel{volume=" + volume + ", storedMaterial='" + storedMaterial + "', material='" + material + "'}";
    }

    // Builder для Barrel
    public static class Builder {
        private double volume;
        private String storedMaterial;
        private String material;

        public Builder setVolume(double volume) {
            if (volume < 0) {
                throw new IllegalArgumentException("Объем не может быть отрицательным");
            }
            this.volume = volume;
            return this;
        }

        public Builder setStoredMaterial(String storedMaterial) {
            this.storedMaterial = storedMaterial;
            return this;
        }

        public Builder setMaterial(String material) {
            this.material = material;
            return this;
        }

        public Barrel build() {
            return new Barrel(this);
        }
    }
}