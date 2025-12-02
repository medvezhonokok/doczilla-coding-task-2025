import model.Game;

public class Solution {
    public static void main(String[] args) {
        run(new Game(
                new Object[][]{
                        {1, 2, 1},
                        {2, 1, 2},
                        {},
                        {}
                }, 3
        ));
    }

    private static void run(Game game) {
        game.solve();
    }
}