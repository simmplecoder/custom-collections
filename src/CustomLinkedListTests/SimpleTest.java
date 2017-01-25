package CustomLinkedListTests;

import CustomCollections.CustomLinkedList;

/**
 * Created by olzhas on 1/25/17.
 */

public class SimpleTest {

    public static void main(String[] args)
    {
        CustomLinkedList<DummyType> list = new CustomLinkedList<>();

        System.out.println("Item adding test:\n" +
                "Initially list is empty.");
        list.add(new DummyType(12));
        System.out.println("Added DummyType(12): " + list);
        list.add(new DummyType(11));
        System.out.println("Added DummyType(11): " + list);
        System.out.println();

        System.out.println("Item getting test:\n" +
                "Currently list = " + list);
        System.out.println("get(0): " + list.get(0));
        System.out.println("get(1): " + list.get(1));
        System.out.println();

        System.out.println("Size test:");
        System.out.println("Currently list = " + list);
        System.out.println("Size = " + list.getSize());
        System.out.println();

        System.out.println("Equality test:");
        System.out.println("First list = " + list);
        CustomLinkedList<DummyType> secondList = new CustomLinkedList<>();
        secondList.add(new DummyType(12));
        secondList.add(new DummyType(11));
        System.out.println("Second list = " + secondList);
        System.out.println("Equality result: list == secondList " + list.equals(secondList));
        System.out.println("Equality result: secondList == list " + secondList.equals(list));
        System.out.println();

        System.out.println("Hash code test:");
        System.out.println("Currently list = " + list);
        System.out.println("Hashcode of list: " + list.hashCode());
        System.out.println("Currently second list = " + secondList);
        System.out.println("Hashcode of secondList: " + secondList.hashCode());
        System.out.println();

        //System.out.println(list.toString());
    }
}
