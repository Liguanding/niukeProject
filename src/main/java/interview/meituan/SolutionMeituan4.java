package interview.meituan;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class SolutionMeituan4 {
    public static void main(String[] args) {
        SolutionMeituan4 solutionMeituan4 = new SolutionMeituan4();
        int[] nums = new int[]{4,3,6,7,8,9};
        double res = 0.0;
        res += Arrays.stream(nums).sum();
        for(int k = 3;k < nums.length;k += 2){
            double[] tmp = solutionMeituan4.medianSlidingWindow(nums,k);
            res += Arrays.stream(tmp).sum();
        }
        System.out.println(res);

    }

    public double[] medianSlidingWindow (int[] nums, int k){
        DualHeap dh = new DualHeap(k);
        for (int i = 0; i < k; ++i) {
            dh.insert(nums[i]);
        }

        double[] res = new double[nums.length - k + 1];
        res[0] = dh.getMedian();
        for (int i = k; i < nums.length; ++i) {
            dh.insert(nums[i]);
            dh.erase(nums[i - k]);
            res[i - k + 1] = dh.getMedian();
        }
        return res;
    }


    class DualHeap{
            private PriorityQueue<Integer> small;
            private PriorityQueue<Integer> large;
            private Map<Integer,Integer> delayed; // 延时删除的hash表

            private int k; //窗口大小,用来判断奇数偶数
            private int smallSize,largeSize;

            public DualHeap(int k){
                this.small = new PriorityQueue<>((o1,o2)->{return o2.compareTo(o1);});
                this.large = new PriorityQueue<>((o1,o2)->{return o1.compareTo(o2);});
                this.delayed = new HashMap<>();
                this.k = k;
                this.smallSize = 0;
                this.largeSize = 0;
            }

            public double getMedian(){
                return (k & 1) == 1 ? small.peek() : ((double)small.peek() + large.peek()) / 2;
            }

            public void insert(int num){
                if(small.isEmpty() || num <= small.peek()){
                    small.offer(num);
                    ++smallSize;
                }else{
                    large.offer(num);
                    ++largeSize;
                }
                makeBalance();
            }

            public void erase(int num){
                delayed.put(num,delayed.getOrDefault(num,0) + 1);
                if(num <= small.peek()){
                    --smallSize;
                    if(num == small.peek()){
                        prune(small);
                    }
                }else{
                    --largeSize;
                    if(num == large.peek()){
                        prune(large);
                    }
                }
                makeBalance();
            }

            public void prune(PriorityQueue<Integer> heap){
                while(!heap.isEmpty()){
                    int num = heap.peek();
                    if(delayed.containsKey(num)){
                        delayed.put(num,delayed.get(num) - 1);
                        if(delayed.get(num) == 0)
                            delayed.remove(num);

                        heap.poll();
                    }else{
                        break;
                    }
                }
            }


            public void makeBalance(){
                if(smallSize > largeSize + 1){
                    large.offer(small.poll());
                    --smallSize;
                    ++largeSize;
                    prune(small);
                }else if(smallSize < largeSize){
                    small.offer(large.poll());
                    ++smallSize;
                    --largeSize;
                    prune(large);
                }
            }
    }


}
