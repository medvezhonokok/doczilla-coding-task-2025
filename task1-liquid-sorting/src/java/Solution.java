import model.Color;
import model.Drop;
import model.Flask;
import model.Move;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Solution {
    public boolean canSolve(List<Flask> flasks, Move root, Set<String> visited) {
        if (isSolved(flasks)) {
            return true;
        }

        String state = flasks.stream().map(String::valueOf).collect(Collectors.joining());

        if (visited.contains(state)) {
            return false;
        }

        visited.add(state);

        for (Move move : getGoodMoves(flasks, root)) {
            commit(move, flasks);
            root.addChild(move);

            if (canSolve(flasks, move, visited)) {
                return true;
            }

            rollback(move, flasks);
            root.removeChild(move);
        }

        visited.remove(state);
        return false;
    }

    public List<Move> getGoodMoves(List<Flask> flasks, Move parent) {
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

    public void commit(Move m, List<Flask> flasks) {
        Flask from = flasks.get(m.getFrom());
        Flask to = flasks.get(m.getTo());

        int dropCount = 0;

        while (!from.isEmpty() && to.canFill(from.last())) {
            to.fill(from.pollLast());
            dropCount++;
        }

        m.setDropCount(dropCount);
    }

    public void rollback(Move m, List<Flask> flasks) {
        Flask from = flasks.get(m.getFrom());
        Flask to = flasks.get(m.getTo());

        int dropCount = m.getDropCount();
        Drop color = m.getDrop();

        while (dropCount > 0 && !to.isEmpty() && to.last().equals(color)) {
            from.fill(to.pollLast());
            dropCount--;
        }
    }

    public boolean isSolved(List<Flask> flasks) {
        for (Flask flask : flasks) {
            if (!flask.isCompleted()) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        List<Flask> flasks = new ArrayList<>();

        // [R, R, G]
        Flask f1 = new Flask(3);
        f1.fill(new Drop(new Color("r")));
        f1.fill(new Drop(new Color("r")));
        f1.fill(new Drop(new Color("g")));

        // [R, G]
        Flask f2 = new Flask(3);
        f2.fill(new Drop(new Color("r")));
        f2.fill(new Drop(new Color("g")));

        // []
        Flask f3 = new Flask(3);

        flasks.add(f1);
        flasks.add(f2);
        flasks.add(f3);

        System.out.println("Initial state:");
        System.out.println(flasks);

        Move root = new Move(-1, -1, null, null);

        if (new Solution().canSolve(flasks, root, new HashSet<>())) {
            System.out.println("Solved");
            printSolutionPath(root);
//            System.out.println(flasks);
        } else {
            System.out.println("Can't solve");
        }
    }

    private static void printSolutionPath(Move root) {
        Move move = root;

        while (!move.getChild().isEmpty()) {
            move = move.getChild().getFirst();
            System.out.println(move);
        }
    }
}