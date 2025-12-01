package model;

import java.util.Objects;

public record Color(Integer numColor, String strColor) {
    public Color(Integer numColor) {
        this(numColor, null);
    }

    public Color(String strColor) {
        this(null, strColor);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Color that)) return false;
        return Objects.equals(this.numColor, that.numColor) && Objects.equals(this.strColor, that.strColor)
               || Objects.equals(this.numColor, that.numColor) && (this.strColor == null || that.strColor == null)
               || Objects.equals(this.strColor, that.strColor) && (this.numColor == null || that.numColor == null);
    }

    @Override
    public String toString() {
        if (numColor != null) return numColor.toString();
        else return "" + Character.toUpperCase(strColor.charAt(0));
    }
}