package custom.llqmap_tests;

import custom.collections.LLQMap;
import custom.collections.Map;

public class LLQMapTests {
    public static void main(String[] args)
    {
        Map<String, Integer> map = new LLQMap<>();

        map.define("pizza", 120);
        map.define("sugar", 20);
        map.define("strawberry", 40);

        System.out.println(map.toString());

        map.define("strawberry", 50);

        System.out.println(map.toString());

        map.remove("pizza");

        System.out.println(map.toString());
    }
}
