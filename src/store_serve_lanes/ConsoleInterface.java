package store_serve_lanes;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ConsoleInterface {
    private StoreServeLanes lanes;
    private boolean keepRunning;
    private ServerSocket socket;
    private Scanner remoteIn;

    public ConsoleInterface()
    {
        System.out.println("Starting user interface ...");
        try {
            socket = new ServerSocket(11987);
            System.out.println("Waiting for client machine to connect ...");
            Socket inSocket = socket.accept();
            remoteIn = new Scanner(inSocket.getInputStream());
            System.out.println("Connection successful");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Creating a socket failed. Aborting program ...");
            System.exit(-1);
        }

        System.out.println("Initializing Serve lanes ...");
        lanes = new StoreServeLanes();
        System.out.println("Ready!");
        keepRunning = true;
    }

    private void receiveCustomer()
    {
        try {
            String customerName = remoteIn.nextLine();
            if (customerName.toLowerCase().equals("time to sleep")) {
                keepRunning = false;
                return;
            }

            Customer customer = new Customer(customerName);
            String buffer = remoteIn.nextLine();
            while (!buffer.toLowerCase().equals("done")) {
                customer.pickupItem(buffer);
                buffer = remoteIn.nextLine();
            }

            int laneNumber = Integer.parseInt(remoteIn.nextLine());
            lanes.assignCustomerToLane(customer, laneNumber);
        } catch (NoSuchElementException e)
        {
            System.out.println("Client machine has suddenly disconnected. Please investigate. Aborting the program");
            System.exit(-1);
        }
    }

    public void start()
    {
        while (keepRunning)
        {
            receiveCustomer();
        }
        remoteIn.close();
        try {
            socket.close();
        } catch (IOException e) {
            System.out.println("Experiencing issues with closing the socket. Crashing ...");
            e.printStackTrace();
            System.exit(-1);
        }
        lanes.terminateAllLines();
    }
}
