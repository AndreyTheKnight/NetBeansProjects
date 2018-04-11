
import java.util.ArrayList;

class Mediator {

    private Object  messageLock;
    private int     message;

    public synchronized void storeMessage(int message) {
        while (slotFull) {
            try {
                wait();
            }
            catch (InterruptedException e ) {
                Thread.currentThread().interrupt();
            }
        }
        slotFull = true;
        this.message = message;
        notifyAll();
    }

    public synchronized int retrieveMessage() {
        while (!slotFull) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        slotFull = false;
        notifyAll();
        return this.message;
    }
}

class Producer implements Runnable {
    
    private final Mediator  med;
    private final int       id;
    private static int      num = 1;

    public Producer(Mediator m) {
        this.med = m;
        this.id = Producer.num++;
    }

    @Override
    public void run() {
        while (!Mediator.shuttingDown) {
            this.med.storeMessage((int)(Math.random()*100));
            System.out.print("p" + this.id + "-" + Producer.num + "  ");
        }
    }
}

class Consumer implements Runnable {
    
    private final Mediator  med;
    private final int       id;
    private static int      num = 1;

    public Consumer(Mediator m) {
        this.med = m;
        this.id = Consumer.num++;
    }

    @Override
    public void run() {
        while (true) {
            System.out.print("c" + id + "-" + med.retrieveMessage() + "  ");
        }
    }
}

public class MediatorDemo {
    public static final long TIME_TO_RUN = 5*1000;
    public static void main( String[] args ) {
        ArrayList<Thread> objList = new ArrayList<>();
        Mediator md = new Mediator();
        objList.add(new Thread(new Producer(md)));
        objList.add(new Thread(new Producer(md)));
        objList.add(new Thread(new Consumer(md)));
        objList.add(new Thread(new Consumer(md)));
        objList.add(new Thread(new Consumer(md)));
        objList.add(new Thread(new Consumer(md)));
        objList.forEach((p) -> {
            p.start();
        });
        try {
            Thread.sleep(TIME_TO_RUN);
        } catch (InterruptedException e) {}
    }
}