package StoreServeLanesTests.CustomerTests;

import StoreServeLanes.Customer;

public class CustomerTests {

    public static void main(String[] args)
    {
        Customer customer = new Customer("Jason");

        customer.pickupItem("Chocolate");
        customer.pickupItem("Beer");
        customer.pickupItem("Beef");

        System.out.println(customer.toString());
    }
}
