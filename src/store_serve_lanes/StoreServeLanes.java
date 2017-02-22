package store_serve_lanes;

import java.io.IOException;
import java.net.Socket;

public class StoreServeLanes {
    private ServeLane[] lanes;

    public StoreServeLanes(Socket sockets[])
    {
        if (sockets.length != 3)
        {
            throw new IllegalArgumentException("Only three sockets are needed");
        }

        lanes = new ServeLane[3];
        for (int i = 0; i < 3; ++i)
        {
            try {
                lanes[i] = new ServeLane(sockets[i]);
            } catch (IOException e) {
                e.printStackTrace();
                cleanup();
            }
            lanes[i].start();
        }
    }

    public void assignCustomerToLane(Customer customer, int laneNumber)
    {
        if (laneNumber < 1 || laneNumber > 3)
        {
            throw new IllegalArgumentException("There are only 3 lanes number 1 2 3");
        }

        lanes[laneNumber - 1].addCustomer(customer);
    }

    public void terminateAllLines()
    {
        for (ServeLane lane: lanes)
        {
            lane.terminate();
        }
    }

    private void cleanup()
    {
        for (int i = 0; i < 3; ++i)
        {
            if (lanes[i] != null)
            {
                lanes[i].terminate();
            }
        }
    }
}
