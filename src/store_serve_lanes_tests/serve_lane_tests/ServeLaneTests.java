package store_serve_lanes_tests.serve_lane_tests;

import store_serve_lanes.Customer;
import store_serve_lanes.ServeLane;

public class ServeLaneTests {
    public static void main(String[] args)
    {
        Customer jason = new Customer("Jason");
        jason.pickupItem("chocolate");
        jason.pickupItem("beer");
        jason.pickupItem("Beef");

        Customer zack = new Customer("Zack");
        zack.pickupItem("milk");
        zack.pickupItem("eggs");

        ServeLane lane = new ServeLane(0);
        lane.start();
        lane.addCustomer(jason);
        lane.addCustomer(zack);

        try {
            Thread.sleep(15000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Customer peter = new Customer("Peter");
        peter.pickupItem("sauce");
        peter.pickupItem("fish");
        peter.pickupItem("bread");
        lane.addCustomer(peter);

        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lane.terminate();
        System.out.println("Test finished");
    }
}
