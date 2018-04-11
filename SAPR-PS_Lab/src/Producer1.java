
import java.util.Random;

public class Producer1 extends Actor implements Runnable {
    
    public Producer1(Mediator mediator) {
        super(mediator);
    }
    
    @Override
    public void run() {
        String importantInfo[] = {
            "Mares eat oats",
            "Does eat oats",
            "Little lambs eat ivy",
            "A kid will eat ivy too"
        };
        Random random = new Random();

        for (String importantInfo1 : importantInfo) {
            this.mediator.put(importantInfo1);
            try {
                Thread.sleep(random.nextInt(5000));
            } catch (InterruptedException e) {}
        }
        this.mediator.put("DONE");
    }
    
}
