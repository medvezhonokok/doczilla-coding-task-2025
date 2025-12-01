package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Move {
    private final int from;
    private final int to;
    private final Drop d;
    private final Move parent;
    private final List<Move> child;
    private int dropCount;

    public Move(int from, int to, Drop d, Move parent) {
        this.from = from;
        this.to = to;
        this.d = d;
        this.dropCount = 0;
        this.parent = parent;
        this.child = new ArrayList<>();
    }

    public int getDropCount() {
        return dropCount;
    }

    public void setDropCount(int dropCount) {
        this.dropCount = dropCount;
    }

    @Override
    public String toString() {
        return from + " -> " + to + " (" + dropCount + ", " + d.toString() + ")";
    }

    public void addChild(Move m) {
        child.add(m);
    }

    public void removeChild(Move move) {
        child.remove(move);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Move that)) return false;
        return this.from == that.from && this.to == that.to &&
               this.d.equals(that.d);
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    public Drop getDrop() {
        return d;
    }

    public List<Move> getChild() {
        return child;
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to, d, parent, child);
    }
}
