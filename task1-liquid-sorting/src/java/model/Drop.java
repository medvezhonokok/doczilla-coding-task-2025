package model;

import java.util.Objects;

public record Drop(Color color) {
    @Override
    public String toString() {
        return color.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Drop(Color color1))) return false;
        return Objects.equals(color, color1);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(color);
    }
}
