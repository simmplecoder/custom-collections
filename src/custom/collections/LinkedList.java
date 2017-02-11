package custom.collections;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class LinkedList<E> implements List<E>{
    private class Node
    {
        private Node prev;
        private E value;
        private Node next;

        Node(Node prev, E value, Node next) {
            this.setPrev(prev);
            this.setValue(value);
            this.setNext(next);
        }

        Node getPrev() {
            return prev;
        }

        void setPrev(Node prev) {
            this.prev = prev;
        }

        E getValue() {
            return value;
        }

        void setValue(E value) {
            this.value = value;
        }

        Node getNext() {
            return next;
        }

        void setNext(Node next) {
            this.next = next;
        }
    }

    private class Iterator implements java.util.ListIterator<E>
    {
        private Node next;
        boolean moved = false;
        private Node previous;
        int index = 0;

        Iterator(Node root)
        {
            next = root;
            moved = true;
            previous = null;
            index = 0;
        }

        @Override
        public boolean hasNext()
        {
            return next != null;
        }

        @Override
        public E next()
        {
            if (!hasNext())
            {
                throw new NoSuchElementException();
            }

            E result = next.getValue();
            previous = next;
            next = next.getNext();
            moved = true;
            ++index;

            return result;
        }

        @Override
        public int nextIndex()
        {
            return index;
        }

        @Override
        public boolean hasPrevious()
        {
            return previous == null;
        }

        @Override
        public E previous()
        {
            if (!hasPrevious())
            {
                throw new NoSuchElementException();
            }

            E result = previous.getValue();
            next = previous;
            previous = previous.getPrev();
            moved = true;
            --index;
            return result;
        }

        @Override
        public int previousIndex()
        {
            return index - 1;
        }

        @Override
        public void remove()
        {
            if (!moved)
            {
                throw new IllegalStateException();
            }

            LinkedList.this.remove(next);
            moved = false;
        }

        @Override
        public void set(E element)
        {
            if (!moved)
            {
                throw new IllegalStateException();
            }

            next.setValue(element);
            moved = false;
        }

        @Override
        public void add(E element)
        {
            LinkedList.this.addAtNode(next, element);
        }
    }

    private Node root;
    private int size;

    public LinkedList()
    {
        size = 0;
    }

    @Override
    public boolean add(E element)
    {
        if (root == null)
        {
            root = new Node(null, element, null);
            ++size;
            return true;
        }

        Node tail = getNodeAt(size - 1);
        tail.setNext(new Node(tail, element, null));

        ++size;
        return true;
    }

    @Override
    public void add(int index, E element)
    {
        if (index < 0 || index > size)
        {
            throw new IndexOutOfBoundsException();
        }

        if (root == null)
        {
            root = new Node(null, element, null);
            ++size;
            return;
        }

        if (index == 0)
        {
            root.setPrev(new Node(null, element, root));
            root = root.getPrev();
            ++size;
            return;
        }

        if (index == size)
        {
            Node current = root;
            while (current.getNext() != null)
            {
                current = current.getNext();
            }

            current.setNext(new Node(current, element, null));
            ++size;
            return;
        }

        Node current = root;
        while (index > 0)
        {
            current = current.getNext();
            --index;
        }

        Node newNode = new Node(current.getPrev(), element, current);
        current.getPrev().setNext(newNode);
        current.setPrev(newNode);
        ++size;
    }

    private void addAtNode(Node position, E element)
    {
        Node newNode = new Node(position.getPrev(), element, position);

        if (position.getPrev() != null)
        {
            position.getPrev().setNext(newNode);
        }

        position.setPrev(newNode);

        if (position == root)
        {
            root = newNode;
        }

        ++size;
    }



    @Override
    public boolean addAll(@NotNull Collection<? extends E> c)
    {
        return addAll(size, c);
    }

    @Override
    public boolean addAll(int index, @NotNull Collection<? extends E> c)
    {
        if (index < 0 || index > size)
        {
            throw new IndexOutOfBoundsException();
        }

        if (c.isEmpty())
        {
            return false;
        }

        int targetSize = size + c.size();

        java.util.Iterator<? extends E> iterator = c.iterator();


        if (root == null) {
            root = new Node(null, iterator.next(), null);
            Node previous;
            previous = root;
            ++size;
            while (iterator.hasNext()) {
                previous.setNext(new Node(previous, iterator.next(), null));
                previous = previous.getNext();
                ++size;
            }

            if (size != targetSize)
            {
                throw new InternalError("Incorrect number of elements were added");
            }

            return true;
        }

        if (index == 0)
        {
            root.setPrev(new Node(null, iterator.next(), root));
            root = root.getPrev();

            Node current = root;
            while (iterator.hasNext())
            {
                Node newNode = new Node(current, iterator.next(), current.getNext());
                if (current.getNext() != null)
                {
                    current.getNext().setPrev(newNode);
                }

                current.setNext(newNode);
                current = current.getNext();
                ++size;
            }
            if (size != targetSize)
            {
                throw new InternalError("Incorrect number of elements were added");
            }

            return true;
        }

        if (index == size)
        {
            Node current = root;
            while (current.getNext() != null)
            {
                current = current.getNext();
            }

            while (iterator.hasNext())
            {
                current.setNext(new Node(current, iterator.next(), null));
                current = current.getNext();
                ++size;
            }

            if (size != targetSize)
            {
                throw new InternalError("Incorrect number of elements were added");
            }

            return true;
        }

        Node current = root;
        while (index > 0)
        {
            if (current.getNext() == null)
            {
                throw new InternalError("index exceeds size, but container doesn't know about that");
            }

            current = current.getNext();
        }

        while (iterator.hasNext())
        {
            Node newNode = new Node(current.getPrev(), iterator.next(), current.getNext());
            current.getPrev().setNext(newNode);
            current.setPrev(newNode);
            ++size;
        }

        if (size != targetSize)
        {
            throw new InternalError("Incorrect number of elements were added");
        }

        return true;
    }

    @Override
    public E set(int index, E element)
    {
        Node position = getNodeAt(index);
        E result = position.getValue();
        position.setValue(element);

        return result;
    }

    @Override
    public boolean retainAll(@NotNull Collection<?> c)
    {
        //noinspection ConstantConditions
        if (c == null) {
            return false;
        }

        boolean hasChanged = false;

        Node current = root;

        while (current != null)
        {
            java.util.Iterator<?> iterator = c.iterator();
            boolean matchFound = false;
            while (iterator.hasNext())
            {
                if (current.getValue().equals(iterator.next()))
                {
                    matchFound = true;
                    break;
                }
            }
            if (matchFound)
            {
                current = current.getNext();
                continue;
            }

            remove(current);
            hasChanged = true;
            current = current.getNext();
        }

        return hasChanged;
    }

    @Override
    public E get(int index)
    {
        Node current = getNodeAt(index);

        return current.getValue();
    }

    private Node getNodeAt(int index)
    {
        if (index < 0 || index >= size)
        {
            throw new IndexOutOfBoundsException();
        }

        Node current = root;

        while (index > 0)
        {
            current = current.getNext();
            --index;
        }

        return current;
    }

    @Override
    public boolean contains(Object o)
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

    @Override
    public boolean containsAll(@NotNull Collection<?> c)
    {
        //noinspection ConstantConditions
        if (c == null)
        {
            throw new NullPointerException();
        }

        for (Object aC : c) {
            if (!contains(aC)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean isEmpty()
    {
        return size == 0;
    }

    @NotNull
    @Override
    public Object[] toArray()
    {
        Object[] result = new Object[size];

        Node current = root;
        int index = 0;
        while (current != null)
        {
            result[index] = current.getValue();
            current = current.getNext();
            ++index;
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    private static <T> Class<? extends T> classOf(T[] array) {
        return (Class<? extends T>) array.getClass().getComponentType();
    }

    @SuppressWarnings("unchecked")
    private static <T> T[] newArray(Class<T> clazz, int size) {
        return (T[]) Array.newInstance(clazz, size);
    }

    @NotNull
    @SuppressWarnings("unchecked")
    @Override
    public <T> T[] toArray(@NotNull T[] a)
    {
        //noinspection ConstantConditions
        if (a == null)
        {
            throw new NullPointerException("Input array must not be null");
        }

        T[] result = a;
        if (a.length < size)
        {
            result = newArray(classOf(a), size);
        }

        Node current = root;
        int index = 0;
        while (current != null)
        {
            result[index] = (T)current.getValue();
            ++index;
            current = current.getNext();
        }

        return result;
    }

    @Override
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
                    current.getPrev().setNext(current.getNext());
                    current.getNext().setPrev(current.getPrev());
                }

                --size;
                return true;
            }

            current = current.getNext();
        }

        return false;
    }

    @Override
    public boolean removeAll(@NotNull Collection<?> c)
    {
        //noinspection ConstantConditions
        if (c == null)
        {
            throw new NullPointerException();
        }

        java.util.Iterator<?> iterator = c.iterator();
        boolean hasChanged = false;
        while (iterator.hasNext())
        {
            if (remove(iterator.next()))
            {
                hasChanged = true;
            }
        }

        return hasChanged;
    }

    @Override
    public E remove(int index)
    {
        Node current = getNodeAt(index);

        return remove(current);
    }

    private E remove(Node node)
    {
        E result = node.getValue();

        if (node == root) //at the beginning of the list
        {
            root = node.getNext();
            if (root != null)
            {
                node.setPrev(null);
            }
        } else
        {
            node.getPrev().setNext(node.getNext());

            if (node.getNext() != null)
            {
                node.getNext().setPrev(node.getPrev());
            }
        }

        --size;
        return result;
    }

    private Comparator getComparator(Object o)
    {
        Comparator comp;

        if (o == null)
        {
            //noinspection ComparatorMethodParameterNotUsed
            comp = (o1, t1) -> {
                if (t1 == null)
                {
                    return 0;
                }
                else {
                    return 1;
                }
            };
        }
        else
        {
            comp = (o12, t1) -> {
                if (o12.equals(t1))
                {
                    return 0;
                }
                else
                {
                    return 1;
                }
            };
        }

        return comp;
    }

    @Override
    public int indexOf(Object o)
    {
        if (size == 0)
        {
            throw new IllegalStateException();
        }

        Comparator comp = getComparator(o);

        java.util.Iterator<E> iterator = iterator();

        int index = 0;
        while (iterator.hasNext())
        {
            //noinspection unchecked
            if (comp.compare(o, iterator.next()) == 0)
            {
                return index;
            }

            ++index;
        }

        return - 1;
    }



    @Override
    public int lastIndexOf(Object o)
    {
        if (size == 0)
        {
            throw new IllegalStateException();
        }

        Comparator comp = getComparator(o);

        int lastIndex = -1;

        java.util.Iterator<E> iterator = iterator();

        int index = 0;
        while (iterator.hasNext())
        {
            //noinspection unchecked
            if (comp.compare(o, iterator.next()) == 0)
            {
                lastIndex = index;
            }
            ++index;
        }

        return lastIndex;
    }

    @Override
    public void clear()
    {
        root = null;
        size = 0;
    }

    @Override
    @NotNull
    public java.util.Iterator<E> iterator()
    {
        return new Iterator(root);
    }

    @NotNull
    @Override
    public java.util.ListIterator<E> listIterator()
    {
        return new Iterator(root);
    }

    @NotNull
    @Override
    public java.util.ListIterator<E> listIterator(int index)
    {
        return new Iterator(getNodeAt(index));
    }

    @Override
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
            result
                    .append(current.getValue().toString())
                    .append(' ');

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
    @SuppressWarnings("unchecked")
    public boolean equals(Object o)
    {
        if (!(o instanceof LinkedList))
        {
            return false;
        }

        LinkedList another = (LinkedList)o;

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

    /**
     *  BROKEN! DO NOT USE!
     * @param from index from which (inclusive) the sublist should start
     * @param to index until which (exclusive) sublist should long
     * @return sublist with range from get(from) to get(to)
     */
    @NotNull
    @Override
    @Deprecated
    public List<E> subList(int from, int to)
    {
        return null;
    }
}
