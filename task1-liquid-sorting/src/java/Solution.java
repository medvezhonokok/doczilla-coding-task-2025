import model.Game;

public class Solution {
    public static void main(String[] args) {
//        run(new Game(
//                new Object[][]{
//                        {1, 2, 1},
//                        {2, 1, 2},
//                        {},
//                        {}
//                }, 3
//        ));

//        run(new Game(
//                new Object[][]{
//                        {},
//                        {},
//                        {},
//                        {},
//                        {},
//                        {},
//                        {'к', 'о', 'ж', 'з', 'г', 'с', 'ф'},
//                        {}
//                }, 10
//        ));

//        run(new Game(
//                new Object[][]{
//                        {"r", "r", "b", "b"},
//                        {"b", "r", "b", "r"},
//                        {},
//                        {}
//                }, 4
//        ));

//        run(new Game(
//                new Object[][]{
//                        {"r", "g", "b", "y"},
//                        {"g", "y", "r", "b"},
//                        {"b", "r", "y", "g"},
//                        {"y", "b", "g", "r"},
//                        {},
//                        {}
//                }, 4
//        ));

        run(new Game(
                new Object[][]{
                        {"r", "b", "r"},
                        {"b", "r", "b"},
                        {},
                        {}
                }, 3
        ));
    }

    private static void run(Game game) {
        game.solve();
    }
}