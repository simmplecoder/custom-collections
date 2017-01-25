package CustomLinkedListTests;

/**
 * Created by olzhas on 1/25/17.
 */
public class DummyType {
    private int x;

    public DummyType(int number)
    {
        x = number;
    }

    @Override
    public String toString()
    {
        return "" + x;
    }

    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof DummyType))
        {
            return false;
        }

        DummyType another = (DummyType)o;
        return another.x == x;
    }

    @Override
    public int hashCode()
    {
        return x;
    }
}
