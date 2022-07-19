package interview.DiDi;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int i = 0;i < T;++i){
            int n = sc.nextInt();
            int[] arr = new int[n];
            for(int j = 0;j < n;++j){
                arr[j] = sc.nextInt();
            }
            System.out.println(check(n,arr) ? "Yes" : "No");
        }
    }

    public static boolean check(int n,int[] arr){
        if(n == 1){
            return true;
        }
        Deque<Integer> upList = new ArrayDeque<>();
        Deque<Integer> downList = new ArrayDeque<>();

        //第一种情况,arr[0]是递增的第一个
        upList.offerLast(arr[0]);
        int idx = 1;
        while(idx < n){
            if(arr[idx] > upList.peekLast()){
                upList.offerLast(arr[idx]);
            }else{
                int oldPeek = upList.pollLast();
                if(!upList.isEmpty() && arr[idx] > upList.peekLast()){
                    upList.offerLast(arr[idx]);
                    if(!downList.isEmpty() && oldPeek >= downList.peekLast())
                        break;
                    downList.offerLast(oldPeek);
                }else{
                    upList.offerLast(oldPeek);
                    if(!downList.isEmpty() && arr[idx] >= downList.peekLast())
                        break;
                    downList.offerLast(arr[idx]);
                }
            }
            ++idx;
        }
        if(upList.size() + downList.size() == n)
            return true;
        //第一种情况,arr[0]是递减的第一个
        upList.clear();
        downList.clear();
        downList.offerLast(arr[0]);
        idx = 1;
        while(idx < n){
            if(arr[idx] < downList.peekLast()){
                downList.offerLast(arr[idx]);
            }else{
                int oldPeek = downList.pollLast();
                if(!downList.isEmpty() && arr[idx] < downList.peekLast()){
                    downList.offerLast(arr[idx]);
                    if(!upList.isEmpty() && oldPeek <= upList.peekLast())
                        return false;
                    upList.offerLast(oldPeek);
                }else{
                    downList.offerLast(oldPeek);
                    if(!upList.isEmpty() && arr[idx] <= upList.peekLast())
                        return false;
                    upList.offerLast(arr[idx]);
                }
            }
            ++idx;
        }
        if(upList.size() + downList.size() == n)
            return true;
        return false;
    }

    public static boolean checkDown(Deque<Integer> list){
        int n = list.size();
        if(n == 0)
            return true;
        int cur = list.pollFirst();
        while (!list.isEmpty()){
            int tmp = list.pollFirst();
            if(tmp >= cur){
                return false;
            }
            cur = tmp;
        }
        return true;
    }

    public static boolean checkUp(Deque<Integer> list){
        int n = list.size();
        if(n == 0)
            return true;
        int cur = list.pollFirst();
        while (list.isEmpty()){
            int tmp = list.pollFirst();
            if(list.pollFirst() <= cur){
                return false;
            }
            cur = tmp;
        }
        return true;
    }
}
