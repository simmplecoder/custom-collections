package store_serve_lanes;

public class StoreServeLanes {
    private ServeLane[] lanes;

    public StoreServeLanes()
    {
        lanes = new ServeLane[3];
        for (int i = 0; i < 3; ++i)
        {
            lanes[i] = new ServeLane(i + 1);
            lanes[i].start();
        }
    }

    public void assignCustomerToLane(Customer customer, int laneNumber)
    {
        if (laneNumber < 1 || laneNumber > 3)
        {
            throw new IllegalArgumentException("There are only 3 lanes number 1 2 3");
        }

        lanes[laneNumber].addCustomer(customer);
    }

    public void terminateAllLines()
    {
        for (ServeLane lane: lanes)
        {
            lane.terminate();
        }
    }
}
