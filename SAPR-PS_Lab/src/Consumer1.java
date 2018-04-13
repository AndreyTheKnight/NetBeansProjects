
import java.util.concurrent.ThreadLocalRandom;

public class Consumer1 extends Actor implements Runnable {
    
    public Consumer1(Mediator mediator) {
        super(mediator);
    }

    @Override
    public void run() {
        for (String message = this.mediator.take();
             ! message.equals("DONE");
             message = this.mediator.take()) {
            System.out.format("Consumer1: %s%n", message);
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(5000));
            } catch (InterruptedException e) {}
        }
        this.mediator.put("DONE");
    }
}