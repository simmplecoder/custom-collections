package CustomCollections;

public class CustomLinkedList<T> {
    private class Node
    {
        public Node prev;
        public T value;
        public Node next;

        public Node(Node prev, T value, Node next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }
    }

    private Node root;
    private int size = 0;

    public boolean add(T object)
    {
        if (root == null)
        {
            root = new Node(null, object, null);
            ++size;
            return true;
        }

        Node current = root;
        while (current.next != null)
        {
            current = current.next;
        }

        current.next = new Node(current, object, null);

        ++size;
        return true;
    }

    public T get(int index)
    {
        if (index < 0)
        {
            throw new IndexOutOfBoundsException();
        }

        Node current = root;
        while (index > 0 && current.next != null)
        {
            current = current.next;
            index--;
        }

        if (index != 0)
        {
            throw new IndexOutOfBoundsException();
        }

        return current.value;
    }

    public int getSize()
    {
        return size;
    }

    @Override
    public String toString()
    {
        StringBuilder result = new StringBuilder("[");
        Node current = root;
        while (current != null)
        {
            result.append(current.value.toString() + ' ');
            current = current.next;
        }

        result.deleteCharAt(result.length() - 1);
        result.append(']');

        return result.toString();
    }

    @Override
    public int hashCode()
    {
        int result = 2028; //random number

        Node current = root;
        while (current != null)
        {
            /*
            picked 29 since its prime. Primes should lead to less hash collisions
             */
            result += 29 * current.value.hashCode();
            current = current.next;
        }

        return result;
    }

    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof CustomLinkedList))
        {
            return false;
        }

        CustomLinkedList another = (CustomLinkedList)o;

        if (size != another.size)
        {
            return false;
        }

        Node current = root;
        Node currentAnother = another.root;

        int counter = size;
        while (counter > 0 &&
                current.value.equals(currentAnother.value))
        {
            current = current.next;
            currentAnother = currentAnother.next;
            --counter;
        }

        return counter == 0;
    }
}
