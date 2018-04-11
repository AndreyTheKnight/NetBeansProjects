
public class Mediator1 implements Mediator {
    
    private String message;
    private boolean empty = true;

    @Override
    public synchronized String take() {
        while (this.empty) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        this.empty = true;
        notifyAll();
        return this.message;
    }

    @Override
    public synchronized void put(String message) {
        while (!this.empty) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        this.empty = false;
        this.message = message;
        notifyAll();
    }
    
}
