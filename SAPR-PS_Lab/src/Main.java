
public class Main {

    public static void main(String[] args) {
        
        Mediator mediator1 = new Mediator1();
        (new Thread(new Producer1(mediator1))).start();
        (new Thread(new Consumer1(mediator1))).start();
        (new Thread(new Consumer1(mediator1))).start();
        
    }
    
}
