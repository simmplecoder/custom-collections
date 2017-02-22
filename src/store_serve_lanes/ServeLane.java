package store_serve_lanes;

import custom.collections.LinkedListQueue;
import java.util.Collection;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

public class ServeLane {
    private LinkedListQueue<Customer> customers;
    private AtomicBoolean keepRunning;
    private Semaphore semaphore;
    private int laneID;
    private Thread worker;

    private class ServeByOne implements Runnable
    {
        @Override
        public void run()
        {
            while (keepRunning.get())
            {
                Customer customer = null;
                try {
                    semaphore.acquire();
                    if (!customers.isEmpty()) {
                        customer = customers.dequeue();
                    }
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (customer != null)
                {
                    serveCustomer(customer);
                }

                try {
                    Thread.sleep(1000); //sleep for a second to avoid thermal throttling
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public ServeLane(int laneID)
    {
        customers = new LinkedListQueue<>();
        //serveStatus = new ScheduledThreadPoolExecutor(1);
        keepRunning = new AtomicBoolean(true);
        semaphore = new Semaphore(1);
        this.laneID = laneID;
        worker = new Thread(new ServeByOne());
    }

    public void addCustomer(Customer customer)
    {
        try {
            semaphore.acquire();
            customers.enqueue(customer);
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void start()
    {
        worker.start();
    }

    private void serveCustomer(Customer customer)
    {
        System.out.println("Lane " + laneID + " started serving customer " + customer.getName());
        Collection<String> items = customer.dropItemsToLine();

        for (String item : items)
        {
            System.out.println("Scanning " + item + " ...");
            try {
                Thread.sleep(3000); //sleep for 3 secs
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Scanning completed. We hope to see you again, " + customer.getName() + "!");
    }

    public void terminate()
    {
        keepRunning.set(false);
    }
}
