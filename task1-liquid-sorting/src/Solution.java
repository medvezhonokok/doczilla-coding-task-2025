import model.Drop;
import model.Flask;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        final int V = 10;
        Flask f = new Flask(V);

        List<Drop> drops = new ArrayList<>();

        drops.add(new Drop("red"));
        drops.add(new Drop("green"));
        drops.add(new Drop("green"));
        drops.add(new Drop("green"));
        drops.add(new Drop("green"));
        drops.add(new Drop("red"));
        drops.add(new Drop(1, "green"));

        f.fill(drops);

        System.out.println(f.canFill(new Drop(1)));
        System.out.println(f.canFill(new Drop(1, "green")));
        System.out.println(f.canFill(new Drop("green")));

        System.out.println(f.canFill(new Drop(1, "red")));
        System.out.println(f.canFill(new Drop("g")));
        System.out.println(f.canFill(new Drop("")));
        System.out.println(f.canFill(new Drop(null, null)));

        System.out.println(f);
        System.out.println(f.isEmpty());

        System.out.println(f.pollLast());
        System.out.println(f);
        System.out.println(f.isEmpty());

        System.out.println(f.pollLast());
        System.out.println(f);
        System.out.println(f.isEmpty());

        System.out.println(f.pollLast());
        System.out.println(f);
        System.out.println(f.isEmpty());

        System.out.println(new Drop(1, "red").equals(new Drop(1, "red")));
        System.out.println(new Drop(1, "red").equals(new Drop("red")));
        System.out.println(new Drop(1).equals(new Drop(1, "red")));
    }
}
