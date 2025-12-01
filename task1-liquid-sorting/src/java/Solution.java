import model.Game;

public class Solution {
    public static void main(String[] args) {
        Game game2 = new Game(
                new Object[][]{
                        {1, 2, 1},
                        {2, 1, 2},
                        {},
                        {}
                }, 3
        );

        game2.solve();
    }
}