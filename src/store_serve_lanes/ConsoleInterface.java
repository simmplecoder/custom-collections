package store_serve_lanes;

import custom.collections.LinkedList;

import java.util.Scanner;

public class ConsoleInterface {
    private StoreServeLanes lanes;
    private Scanner scanner;
    private boolean keepRunning;

    public ConsoleInterface()
    {
        System.out.println("Starting user interface ...");
        scanner = new Scanner(System.in);
        System.out.println("Initializing Serve lanes ...");
        lanes = new StoreServeLanes();
        System.out.println("Ready!");
        keepRunning = true;
    }

    private void guideCustomer()
    {
        System.out.println("Welcome to North Island store!");
        System.out.print("Please enter your name: ");
        String customerName = scanner.nextLine();

        if (customerName.toLowerCase().equals("time to sleep"))
        {
            keepRunning = false;
            return;
        }

        LinkedList<String> items = new LinkedList<>();
        System.out.println("Please type the names of items you wish to pick up. " +
                "Type done when you're ready to go to service lane");
        String buffer = scanner.nextLine();

        while (!buffer.equals("done"))
        {
            items.add(buffer);
            buffer = scanner.nextLine();
        }

        System.out.println("To which lane would you want to join? [1-3]");
        int laneNumber = scanner.nextInt();
        while (laneNumber < 1 || laneNumber > 3)
        {
            System.out.println("Incorrect lane number. Try again");
            laneNumber = scanner.nextInt();
        }

        Customer customer = new Customer(customerName);

        for (String productName: items)
        {
            customer.pickupItem(productName);
        }

        lanes.assignCustomerToLane(customer, laneNumber);
    }

    public void exec()
    {
        while (keepRunning)
        {
            guideCustomer();
        }
    }
}
