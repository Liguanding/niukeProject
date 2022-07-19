package interview;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Test {
    class Solution {
        public int shipWithinDays(int[] weights, int days) {
            int sum = Arrays.stream(weights).sum();
            int avg = sum % days == 0 ? (sum / days) : (sum / days + 1);

            int left = avg,right = sum;
            while(left <= right){
                int mid = (left + right) / 2;
                if(check(weights,days,mid)){
                    right = mid - 1;
                }else{
                    left = mid + 1;
                }
            }
            return left;
        }

        public boolean check(int[] weights,int days,int perDay){

            int res = 0,idx = 0;
            int curDay = 0;
            for(int i = 0;i < days;++i){
                while(idx < weights.length && curDay + weights[idx] <= perDay){
                    curDay += weights[idx];
                    idx++;
                }
                curDay = 0;
            }
            return idx >= weights.length;
        }


    }


    public static void main(String[] args) {
        Test.Solution solution = new Test().new Solution();
        int[] weights = new int[]{1,2,3,4,5,6,7,8,9,10};
        int days = 5;
//        System.out.println(solution.check(weights,days,11));
//        System.out.println(solution.check(weights,days,15));
        System.out.println(solution.shipWithinDays(weights,5));

    }


}
