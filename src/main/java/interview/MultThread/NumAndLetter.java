package interview.MultThread;

public class NumAndLetter {


    private static char c = 'A';
    private static int i = 0;
    static final Object lock = new Object();

    public static void main(String[] args) {
        new Thread(()->print(),"numThread").start();
        new Thread(()->print(),"letterThread").start();
    }

    private static void print(){
        synchronized (lock){
            for(int i = 0;i < 26;++i){
                if(Thread.currentThread().getName() == "numThread"){
                    System.out.println(i+1);
                    lock.notifyAll();
                    try {
                        lock.wait();
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }else if(Thread.currentThread().getName() == "letterThread"){
                    System.out.println((char)('A' + i));
                    lock.notifyAll();
                    try {
                        lock.wait();
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
            //lock.notifyAll();
        }
    }

}
