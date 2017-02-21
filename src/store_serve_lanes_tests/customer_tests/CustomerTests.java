package store_serve_lanes_tests.customer_tests;

import store_serve_lanes.Customer;

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
