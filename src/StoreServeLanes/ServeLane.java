package StoreServeLanes;

import custom.collections.LinkedListQueue;
import java.util.Collection;
//import java.util.Timer;
//import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.Semaphore;
//import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicBoolean;

public class ServeLane {
    private LinkedListQueue<Customer> customers;
    //private ScheduledThreadPoolExecutor serveStatus;
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
                try {
                    semaphore.acquire();
                    if (!customers.isEmpty()) {
                        serveCustomer(customers.dequeue());
                    }
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                    Thread.sleep(2000); //sleep for two seconds to avoid thermal throttling
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
//        final Runnable checkAndAssign = new Runnable() {
//            @Override
//            public void run() {
//                while (keepRunning.get())
//                {
//                    try {
//                        semaphore.acquire();
//                        if (!customers.isEmpty()) {
//                            serveCustomer(customers.dequeue());
//                        }
//                        semaphore.release();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//
//                    try {
//                        Thread.sleep(2000); //sleep for two seconds to avoid thermal throttling
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        };
        worker.start();
    }

    private void serveCustomer(Customer customer)
    {
        ScheduledThreadPoolExecutor server = new ScheduledThreadPoolExecutor(1);
        System.out.println("Lane " + laneID + " started serving customer " + customer.getName());
        Collection<String> items = customer.dropItemsToLine();

        for (String item : items)
        {
            System.out.println("Scanning " + item + " ...");
            try {
                Thread.sleep(5000); //sleep for 5 secs
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("done");
    }

    public void terminateLane()
    {
        keepRunning.set(false);
    }

//    private void scanItem(String name)
//    {
//        //to implement
//    }
}
