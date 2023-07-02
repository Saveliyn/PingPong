

public class Store {
    public int a = 0;
    public int b = 0;

    public synchronized void printA() {
        if (a != b) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else  {
            a++;
            System.out.println("ping ");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            notify();
        }
    }

    public synchronized void printB() {
        if (a == b) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else  {
            b++;
            System.out.println("pong ");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            notify();
        }
    }

}

class MainStore {

    public static void main(String[] args) {
        Store store = new Store();
        while (true)  {
            new Thread(() -> {
                store.printA();
            }).start();


            new Thread(() -> {
                store.printB();
            }).start();
        }


    }
}


