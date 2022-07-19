package interview.MultThread;

import java.util.concurrent.Semaphore;

public class PrintABC2 {

    private static Semaphore semaphoreA = new Semaphore(1);
    private static Semaphore semaphoreB = new Semaphore(0);
    private static Semaphore semaphoreC = new Semaphore(0);

    private void print(Semaphore curr,Semaphore next){
        for(int i = 0;i < 10;++i){
            try {
                curr.acquire();
                System.out.print(Thread.currentThread().getName()+ " ");
                next.release();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        PrintABC2 printer = new PrintABC2();
        new Thread(()->printer.print(printer.semaphoreA, printer.semaphoreB),"A").start();
        new Thread(()->printer.print(printer.semaphoreB, printer.semaphoreC),"B").start();
        new Thread(()->printer.print(printer.semaphoreC, printer.semaphoreA),"C").start();

    }


}
