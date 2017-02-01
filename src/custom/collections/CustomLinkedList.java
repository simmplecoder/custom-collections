package custom.collections;

import custom.linked_list_tests.DummyType;

import java.util.Collection;
import java.util.NoSuchElementException;

public class CustomLinkedList<E> implements Collection<E>{
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

    private class Iterator implements java.util.Iterator<E>
    {
        private Node next;
        boolean nextCalled = false;

        Iterator(Node root)
        {
            next = root;
            nextCalled = true;
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
            next = next.getNext();
            nextCalled = true;

            return result;
        }

        @Override
        public void remove()
        {
            if (!nextCalled)
            {
                throw new IllegalStateException();
            }

            CustomLinkedList.this.remove(next);
            nextCalled = false;
        }
    }

    private Node root;
    private int size = 0;

    @Override
    public boolean add(E object)
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

    @Override
    public boolean addAll(Collection<? extends E> c)
    {
        if (c == null) {
            throw new NullPointerException("Container must not be null");
        }

        java.util.Iterator<? extends E> iterator = c.iterator();

        while (iterator.hasNext())
        {
            add(iterator.next());
        }

        return true;
    }



    @Override
    public boolean retainAll(Collection<?> c)
    {
        return false;
    }

    public E get(int index)
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
    public boolean containsAll(Collection<?> c)
    {
        if (c == null)
        {
            throw new NullPointerException();
        }

        java.util.Iterator<?> iterator = c.iterator();

        while (iterator.hasNext())
        {
            if (!contains(iterator.next()))
            {
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

    @Override
    public <T> T[] toArray(T[] a)
    {
//        T[] result = a;
//
//        if (a.length < size)
//        {
//            result = new T[size];
//        }
//
//        Node current = root;
//        int index = 0;
//        while (current != null)
//        {
//            if (!(result[index] instanceof E))
//            {
//
//            }
//        }
        return a;
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
    public boolean removeAll(Collection<?> c)
    {
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

    public E remove(int index)
    {
        if (index >= size || size < 0)
        {
            throw new IndexOutOfBoundsException();
        }

        Node current = root;
        while (index > 0)
        {
            current = current.getNext();
            --index;
        }

        E result = current.getValue();
        if (size == 1)
        {
            root = null;
        }
        else if (index == 0)
        {
            root = root.getNext();
        } else
        {
            current.getPrev().setNext(current.getNext());

            if (current.getNext() != null)
            {
                current.getNext().setPrev(current.getPrev());
            }
        }

        --size;
        return result;
    }

    private void remove(Node node)
    {
        if (node == null)
        {
            throw new NullPointerException();
        }

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
    }

    public void clear()
    {
        root = null;
        size = 0;
    }

    @Override
    public java.util.Iterator<E> iterator()
    {
        return new Iterator(root);
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
