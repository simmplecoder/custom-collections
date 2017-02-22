package store_serve_lanes.lane_consoles;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class LaneConsole {
    private Socket socket;
    private Scanner remoteIn;
    private boolean keepRunning;

    /** @noinspection WeakerAccess*/
    public LaneConsole()
    {
        String hostName = "localhost";
        int portNumber = 11987;
        try {
            socket = new Socket(hostName, portNumber);
            remoteIn = new Scanner(socket.getInputStream());
        } catch (IOException e) {
            System.out.println("Connection to host failed. It might be not turned on yet. Aborting...");
            System.exit(-1);
        }
        keepRunning = true;
    }

    private void listen()
    {
        String text = remoteIn.nextLine();
        if (text.toLowerCase().equals("time to sleep"))
        {
            keepRunning = false;
            return;
        }

        System.out.println(text);
    }

    /** @noinspection WeakerAccess*/
    public void start()
    {
        while (keepRunning)
        {
            listen();
        }
        remoteIn.close();
        try {
            socket.close();
        } catch (IOException e) {
            System.out.println("Problems terminating connection with host. " +
                    "Nothing bad, ignoring and proceeding");
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        LaneConsole console = new LaneConsole();
        console.start();
    }
}
