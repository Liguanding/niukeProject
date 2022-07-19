package interview.MultThread;

public class PrintByThread {
    private static Object obj = new Object();
    private static volatile int count = 0;
    private static int threadIndex = 0;

    public static void main(String[] args) {
        int n = 10;
        for(int i = 0;i < 10;++i){
            new Thread(new MyThread(i,n)).start();
        }
    }

    static class MyThread implements Runnable{

        int i;
        int n;

        MyThread(int i,int n){
            this.i = i;
            this.n = n;
        }

        @Override
        public void run() {
            while(count < 100){
                if(threadIndex % n == i){
                    synchronized (obj){
                        threadIndex++;
                        System.out.println(Thread.currentThread().getName() + " : " + count);
                        count++;
                    }
                }
            }
        }
    }



}
