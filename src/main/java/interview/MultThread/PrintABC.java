package interview.MultThread;

public class PrintABC {

    private static Object obj = new Object();
    private int count = 0;

    private void printABC(int targetNum){
        for(int i = 0;i < 10;++i){
            synchronized (obj){
                while(count % 3 != targetNum){
                    try {
                        obj.wait();
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                count++;
                System.out.print(Thread.currentThread().getName() + " ");
                obj.notifyAll();
            }
        }
    }

    public static void main(String[] args) {
        PrintABC printABC = new PrintABC();
        new Thread(()->printABC.printABC(0),"A").start();
        new Thread(()->printABC.printABC(1),"B").start();
        new Thread(()->printABC.printABC(2),"C").start();
    }



}
