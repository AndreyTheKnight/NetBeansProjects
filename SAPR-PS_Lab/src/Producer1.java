
import java.util.Random;

public class Producer1 extends Actor implements Runnable {
    
    public Producer1(Mediator mediator) {
        super(mediator);
    }
    
    @Override
    public void run() {
        String importantInfo[] = {
            "Mares eat oats",
            "Some string 1",
            "Does eat oats",
            "Some string 2",
            "Little lambs eat ivy",
            "Some string 3",
            "A kid will eat ivy too",
            "Some string 4"
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
