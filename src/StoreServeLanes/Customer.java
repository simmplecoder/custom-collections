package StoreServeLanes;

import custom.collections.LinkedList;
import custom.collections.LinkedListQueue;

import java.util.Collection;
import java.util.Iterator;

public class Customer {
    private String name;
    private LinkedList<String> items;

    public Customer(String name)
    {
        this.name = name;
        items = new LinkedList<>();
    }

    public void pickupItem(String name)
    {
        items.add(name);
    }

    public Collection<String> dropItemsToLine()
    {
        Collection<String> copy = items;
        items = null;
        return copy;
    }

    public String getName()
    {
        return name;
    }

    @Override
    public String toString()
    {
        if (items == null)
        {
            throw new IllegalStateException("Customer has been served");
        }

        return "Customer " + name + " holds following items: " + items.toString();
    }
}
