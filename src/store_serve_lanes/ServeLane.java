package store_serve_lanes;

import custom.collections.LinkedListQueue;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Collection;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

public class ServeLane {
    private LinkedListQueue<Customer> customers;
    private AtomicBoolean keepRunning;
    private Semaphore semaphore;
    private Thread worker;
    private Socket socket;
    private PrintWriter remoteOut;

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

    public ServeLane(Socket s) throws IOException {
        socket = s;
        try {
            remoteOut = new PrintWriter(s.getOutputStream(), true);
        } catch (IOException e) {
            System.out.println("Creating output stream from socket failed. Rethrowing ...");
            e.printStackTrace();
            throw e;
        }
        customers = new LinkedListQueue<>();
        //serveStatus = new ScheduledThreadPoolExecutor(1);
        keepRunning = new AtomicBoolean(true);
        semaphore = new Semaphore(1);
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
        remoteOut.println("Serving customer " + customer.getName());
        Collection<String> items = customer.dropItemsToLine();

        for (String item : items)
        {
            remoteOut.println("Scanning " + item + " ...");
            try {
                Thread.sleep(3000); //sleep for 3 secs
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        remoteOut.println("Scanning completed. We hope to see you again, " + customer.getName() + "!");
    }

    public void terminate()
    {
        keepRunning.set(false);

        try {
            worker.join();
        } catch (InterruptedException e) {
            //nothing too bad, proceed
            e.printStackTrace();
        }
        remoteOut.println("time to sleep"); //serve lane console is responsible for cleaning up
        remoteOut.close();
    }
}
