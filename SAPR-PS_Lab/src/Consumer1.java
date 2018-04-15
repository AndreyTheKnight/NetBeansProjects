
import java.util.concurrent.ThreadLocalRandom;

public class Consumer1 extends Actor implements Runnable {
    
    static private int newID = 1;
    private final int id = Consumer1.newID++;
    
    public Consumer1(Mediator mediator) {
        super(mediator);
    }

    @Override
    public void run() {
        for (String message = this.mediator.take();
             ! message.equals("DONE");
             message = this.mediator.take()) {
            System.out.format("Consumer %d: %s%n", this.id, message);
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(5000));
            } catch (InterruptedException e) {}
        }
        this.mediator.put("DONE");
    }
    
}