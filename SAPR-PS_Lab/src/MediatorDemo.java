
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

class Mediator {

    public static boolean   shuttingDown = false;
    private boolean         slotFull = false;
    private int             message;

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
    public static void main( String[] args ) {
        List<Thread> objList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Нажмите ENTER для выхода");
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
        String exit = scanner.nextLine();
        while (!Mediator.shuttingDown) {
            if (exit.equals("")) {
                Mediator.shuttingDown = true;
            }
        }
    }
}