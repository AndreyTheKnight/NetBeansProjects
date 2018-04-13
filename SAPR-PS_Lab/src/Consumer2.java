
import java.util.Random;

public class Consumer2 extends Actor implements Runnable {
    
    public Consumer2(Mediator mediator) {
        super(mediator);
    }

    @Override
    public void run() {
        Random random = new Random();
        for (String message = this.mediator.take();
             ! message.equals("DONE");
             message = this.mediator.take()) {
            System.out.format("Consumer2: %s%n", message);
            try {
                Thread.sleep(random.nextInt(5000));
            } catch (InterruptedException e) {}
        }
        this.mediator.put("DONE");
    }
}