package StoreServeLanesTests.ServeLaneTests;

import StoreServeLanes.Customer;
import StoreServeLanes.ServeLane;

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
            Thread.sleep(25000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        lane.terminateLane();
        System.out.println("Test finished");
    }
}
