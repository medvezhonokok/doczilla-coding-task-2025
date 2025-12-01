package model;

import exceptions.FilledFlaskException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Flask {
    private final List<Drop> drops;
    private final int capacity;

    public Flask(List<Drop> drops, int capacity) {
        this.drops = drops;
        this.capacity = capacity;
    }

    public Flask(int capacity) {
        this(new ArrayList<>(capacity), capacity);
    }

    public List<Drop> getDrops() {
        return drops;
    }

    public int getCapacity() {
        return capacity;
    }

    public int size() {
        return drops.size();
    }

    public boolean isEmpty() {
        return drops == null || drops.isEmpty();
    }

    public boolean isFull() {
        return drops != null && size() == capacity;
    }

    public boolean canFill(Flask flask) {
        if (isEmpty() || flask.isFull() || flask.isEmpty()) return false;
        return last().equals(flask.last());
    }

    public boolean canFill(Drop drop) {
        if (isFull()) return false;
        if (isEmpty() && capacity > 0) return true;

        return last().equals(drop);
    }

    public void fill(Drop drop) {
        assert drop != null;

        if (isFull()) {
            throw new FilledFlaskException(String.format("Flask is full [%d/%d]", drops.size(), capacity));
        }

        drops.add(drop);
    }

    public void fill(List<Drop> drops) {
        for (Drop drop : drops) {
            fill(drop);
        }
    }

    public Drop last() {
        return drops.getLast();
    }

    public Drop pollLast() {
        return this.drops.removeLast();
    }

    public boolean isCompleted() {
        if (isEmpty()) return true;

        Drop first = this.drops.getFirst();
        for (int i = 1; i < size(); i++) {
            if (!first.equals(this.drops.get(i))) return false;
        }

        return true;
    }

    @Override
    public String toString() {
        int filled = drops.size();

        return "(" + String.join(" | ", drops.stream().map(Drop::toString).toArray(String[]::new)) + ")"
               + " [" + filled + "/" + capacity + "]";
    }

    public List<Drop> drops() {
        return drops;
    }

    public int capacity() {
        return capacity;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Flask) obj;
        return Objects.equals(this.drops, that.drops) &&
               this.capacity == that.capacity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(drops, capacity);
    }

}
