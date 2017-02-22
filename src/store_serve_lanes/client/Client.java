package store_serve_lanes.client;

import custom.collections.LinkedList;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private PrintWriter remoteOut;
    private Scanner in;
    private boolean keepRunning;
    private Socket socket;

    /** @noinspection WeakerAccess*/
    public Client()
    {
        String hostName = "localhost";
        int portNumber = 11987;
        try {
            socket = new Socket(hostName, portNumber);
            remoteOut = new PrintWriter(socket.getOutputStream(), true);
            in = new Scanner(System.in);
        } catch (IOException e) {
            System.out.println("Connection to host failed. It might be not turned on yet. Aborting...");
            System.exit(-1);
        }
        keepRunning = true;
    }

    /** @noinspection WeakerAccess*/
    public void getNewClient()
    {
        //in.nextLine(); //flush the input
        System.out.println("Welcome to North Island store!");
        System.out.print("Please enter your name: ");
        String customerName = in.nextLine();

        if (customerName.toLowerCase().equals("time to sleep"))
        {
            remoteOut.println("time to sleep");
            keepRunning = false;
            return;
        }

        LinkedList<String> items = new LinkedList<>();
        System.out.println("Please type the names of items you wish to pick up. " +
                "Type done when you're ready to go to service lane, " +
                "or type cancel if you don't want to make any purchases");
        String buffer = in.nextLine().toLowerCase();
        while (!buffer.equals("done"))
        {
            if (buffer.equals("cancel"))
            {
                System.out.println("Session has been canceled.");
                return;
            }
            items.add(buffer);
            buffer = in.nextLine().toLowerCase();

        }

        System.out.println("To which lane would you want to join? [1-3]");
        int laneNumber = Integer.parseInt(in.nextLine());
        while (laneNumber < 1 || laneNumber > 3)
        {
            System.out.println("Incorrect lane number. Try again");
            laneNumber = in.nextInt();
        }

        remoteOut.println(customerName);

        for (String productName: items)
        {
            remoteOut.println(productName);
        }
        remoteOut.println("done");
        remoteOut.println(Integer.toString(laneNumber));
    }

    /** @noinspection WeakerAccess*/
    public void start()
    {
        while (keepRunning)
        {
            getNewClient();
        }
//        remoteOut.println("time to sleep");
        remoteOut.close();
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        Client client = new Client();
        client.start();
    }
}
