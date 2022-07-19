package interview.ByteDance;

import java.util.PriorityQueue;

public class Three {

    public static void main(String[] args) {
        new Three().longest(1, 1);
    }


    private void longest(int longer, int seconds) {
        PriorityQueue<Integer> evenQueue = new PriorityQueue<>((a, b) -> {
            return b - a;
        });
        PriorityQueue<Integer> oddQueue = new PriorityQueue<>((a, b) -> {
            return b - a;
        });
        int curTime = 0;
        int res = longer;
        if (longer % 2 == 0) {
            oddQueue.add(longer / 2);
            oddQueue.add(longer / 2);
        }
        for (int i = curTime; i < seconds; ++i) {

        }

    }


}
