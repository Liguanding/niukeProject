package interview.pdd.practise;

public class Singleton {
    //单例模式  双重校验实现
    private volatile static Singleton uniqueInstance;

    Singleton() {
    }

    public static Singleton getInstance() {
        if (uniqueInstance == null) {
            synchronized (Singleton.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new Singleton();
                }
            }
        }
        return uniqueInstance;
    }
}
