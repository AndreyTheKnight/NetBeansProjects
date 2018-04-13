
import java.util.concurrent.ThreadLocalRandom;

public class Consumer2 extends Actor implements Runnable {
    
    public Consumer2(Mediator mediator) {
        super(mediator);
    }

    @Override
    public void run() {
        for (String message = this.mediator.take();
             ! message.equals("DONE");
             message = this.mediator.take()) {
            System.out.format("Consumer2: %s%n", message);
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(5000));
            } catch (InterruptedException e) {}
        }
        this.mediator.put("DONE");
    }
}