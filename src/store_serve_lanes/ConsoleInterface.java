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

    public ConsoleInterface() {
        System.out.println("System startup");
        System.out.println("Starting a server ...");
        try {
            socket = new ServerSocket(11987);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Creating a socket failed. Aborting program ...");
            System.exit(-1);
        }
        System.out.println("Done");

        Socket serveLaneConsoleSockets[] = new Socket[3];
        for (int i = 0; i < 3; ++i) {
            System.out.println("Waiting for serve lane consoles to connect. " + i + " connected.");
            try {
                serveLaneConsoleSockets[i] = socket.accept();
            } catch (IOException e) {
                System.out.println("Couldn't connect to serve lane consoles. Cannot recover, crashing ...");
                e.printStackTrace();
                System.exit(-1);
            }
        }
        System.out.println("All serve lane consoles connected.");
        System.out.println("Initializing Serve lanes ...");
        lanes = new StoreServeLanes(serveLaneConsoleSockets);
        System.out.println("Done");

        System.out.println("Starting user interface ...");
        System.out.println("Waiting for client machine to connect ...");
        try {
            Socket inSocket = socket.accept(); //the client is responsible for closing the connection
            remoteIn = new Scanner(inSocket.getInputStream());
        } catch (IOException e) {
            System.out.println("Couldn't connect to client machine. Aborting ...");
            e.printStackTrace();
            System.exit(-1);
        }

        System.out.println("Connection successful");

        System.out.println("Ready!");
        keepRunning = true;
    }

    private void receiveCustomer() {
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
        } catch (NoSuchElementException e) {
            System.out.println("Client machine has suddenly disconnected. Please investigate. Aborting the program");
            System.exit(-1);
        }
    }

    public void start() {
        while (keepRunning) {
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
