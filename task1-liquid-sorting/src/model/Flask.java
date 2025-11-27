package model;

import exceptions.FilledFlaskException;

import java.util.ArrayList;
import java.util.List;

public record Flask(List<Drop> drops, int capacity) {
    public Flask(int capacity) {
        this(new ArrayList<>(capacity), capacity);
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

    public boolean canFill(Drop drop) {
        if (isFull()) return false;
        if (isEmpty()) return true;

        return last().equals(drop);
    }

    private void fill(Drop drop) {
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
        if (!isFull()) return false;

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
}
