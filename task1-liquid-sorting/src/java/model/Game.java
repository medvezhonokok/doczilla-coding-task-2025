package model;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Game {
    private final List<Flask> flasks;

    public Game(Object[][] flasks, int capacity) {
        this.flasks = new ArrayList<>();

        for (Object[] flask : flasks) {
            Flask f = new Flask(capacity);

            for (Object color : flask) {
                Drop drop = switch (color) {
                    case Integer i -> new Drop(new Color(i));
                    case String s -> new Drop(new Color(s));
                    case Character ch -> new Drop(new Color(Character.toString(ch)));
                    default -> throw new IllegalArgumentException("Color must be Integer or String");
                };

                f.fill(drop);
            }

            this.flasks.add(f);
        }
    }

    private boolean isSolved() {
        for (Flask flask : flasks) {
            if (!flask.isCompleted()) {
                return false;
            }
        }

        return true;
    }

    private Move canSolve(Move root, Set<String> visited) {
        String state = flasks.stream().map(String::valueOf).collect(Collectors.joining(", "));

        if (isSolved()) {
            visited.add(state);
            return root;
        }

        if (visited.contains(state)) {
            return null;
        }

        visited.add(state);

        for (Move move : getGoodMoves(root)) {
            commit(move);

            Move lastMove = canSolve(move, visited);
            if (lastMove != null) {
                return lastMove;
            }

            rollback(move);
        }

        return null;
    }

    private List<Move> getGoodMoves(Move parent) {
        List<Move> moves = new ArrayList<>();

        for (int i = 0; i < flasks.size(); i++) {
            Flask g = flasks.get(i);
            if (g.isEmpty()) continue;

            for (int j = 0; j < flasks.size(); j++) {
                if (i == j) continue;

                Flask f = flasks.get(j);
                if (f.isFull()) continue;

                if (f.isEmpty() || g.last().equals(f.last())) {
                    moves.add(new Move(i, j, g.last(), parent));
                }
            }
        }

        return moves;
    }

    private void commit(Move m) {
        Flask from = flasks.get(m.getFrom());
        Flask to = flasks.get(m.getTo());

        int dropCount = 0;

        while (!from.isEmpty() && to.canFill(from.last())) {
            to.fill(from.pollLast());
            dropCount++;
        }

        m.setDropCount(dropCount);
    }

    private void rollback(Move m) {
        Flask from = flasks.get(m.getFrom());
        Flask to = flasks.get(m.getTo());

        int dropCount = m.getDropCount();
        Drop color = m.getDrop();

        while (dropCount > 0 && !to.isEmpty() && to.last().equals(color)) {
            from.fill(to.pollLast());
            dropCount--;
        }
    }

    public void solve() {
        System.out.println(this);

        Set<String> visited = new LinkedHashSet<>();
        Move last = canSolve(null, visited);

        if (last != null || isSolved()) {
            System.out.println("Solution found!");
            List<Move> movesHistory = new ArrayList<>();

            while (last != null) {
                movesHistory.add(last);
                last = last.getParent();
            }

            System.out.println("Need " + movesHistory.size() + " moves");
            movesHistory.reversed().forEach(System.out::println);
        } else {
            System.out.println("No solution found!");
        }
    }

    @Override
    public String toString() {
        return "Game=[\n\t" + flasks.stream().map(Flask::toString).collect(Collectors.joining("\n\t")) + "\n]";
    }
}
