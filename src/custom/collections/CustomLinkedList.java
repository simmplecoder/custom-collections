package custom.collections;

public class CustomLinkedList<T> {
    private class Node
    {
        private Node prev;
        private T value;
        private Node next;

        public Node(Node prev, T value, Node next) {
            this.setPrev(prev);
            this.setValue(value);
            this.setNext(next);
        }

        public Node getPrev() {
            return prev;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
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
        while (current.getNext() != null)
        {
            current = current.getNext();
        }

        current.setNext(new Node(current, object, null));

        ++size;
        return true;
    }

    public void clear()
    {
        root = null;
        size = 0;
    }

    boolean contains(Object o)
    {
        Node current = root;
        while (current != null)
        {
            if (current.getValue().equals(o))
            {
                return true;
            }

            current = current.getNext();
        }

        return false;
    }

    public boolean isEmpty()
    {
        return size == 0;
    }

    public boolean remove(Object o)
    {
        Node current = root;
        while (current != null)
        {
            if (current.getValue().equals(o))
            {
                if (current == root)
                {
                    root = current.getNext();
                }
                else if (current.getNext() == null)
                {
                    current.getPrev().setNext(null);
                }
                else
                {
                    current
                            .getPrev()
                            .setNext(current.getNext());
                }

                --size;
                return true;
            }

            current = current.getNext();
        }

        return false;
    }

    public T get(int index)
    {
        if (index < 0)
        {
            throw new IndexOutOfBoundsException();
        }

        Node current = root;
        while (index > 0 && current.getNext() != null)
        {
            current = current.getNext();
            index--;
        }

        if (index != 0)
        {
            throw new IndexOutOfBoundsException();
        }

        return current.getValue();
    }

    public int size()
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
            result.append(current.getValue().toString() + ' ');
            current = current.getNext();
        }

        if (result.length() > 1)
        {
            result.deleteCharAt(result.length() - 1);
        }

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
            result = 29 * result + current.getValue().hashCode();
            current = current.getNext();
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

        while (current != null && currentAnother != null)
        {
            if (!current.getValue().
                    equals(currentAnother.getValue()))
            {
                return false;
            }

            current = current.getNext();
            currentAnother = currentAnother.getNext();
        }

        return true;
    }
}
