package store_serve_lanes;

import custom.collections.LinkedList;

import java.util.Collection;

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
