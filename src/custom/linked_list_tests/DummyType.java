package custom.linked_list_tests;

public class DummyType {
    private int x;

    /**
     * @noinspection WeakerAccess
     */
    public DummyType(int number) {
        x = number;
    }

    @Override
    public String toString() {
        return "" + x;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof DummyType)) {
            return false;
        }

        DummyType another = (DummyType) o;
        return another.x == x;
    }

    @Override
    public int hashCode() {
        return x;
    }
}
