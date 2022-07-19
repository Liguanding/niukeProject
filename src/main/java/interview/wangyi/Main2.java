package interview.wangyi;

import java.util.*;

public class Main2 {
    static List<Integer> path;
    static List<List<Integer>> res;
    static int cnt;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long k = sc.nextInt();
        long x = sc.nextInt();
        path = new ArrayList<>();
        res = new ArrayList<>();
        solute(n,k,x);
//        cnt = 0;
//        cnt = solution(n,k,x);
//        if(cnt == 0)
//            System.out.println(-1);
//        else{
//            List<Integer> list = res.get(0);
//            for(int i = 0;i < n;++i){
//                if(i != n-1){
//                    System.out.print(list.get(i) + " ");
//                }else {
//                    System.out.println(list.get(i));
//                }
//            }
//        }
    }
    public static void solute(long n,long k,long x){
        long maxSum = (2 * k + 1 - n)*n / 2;
        if(maxSum < x){
            System.out.println(-1);
            return;
        }
        long y = (2 * x/n + n - 1) / 2;
        long diff = x - fun(y,n);
        if(diff == 0){
            for(long i = y - n + 1;i < y;++i){
                System.out.print(i + " ");
            }
            System.out.println(y);
        }else if(diff < n){
            long t = 0l;
            long r = 0l;
            if(y + diff <= k){
                r = y + diff;
                t = y;
                for(long i = y - n + 1;i < y;++i){
                    System.out.print(i + " ");
                }
                System.out.println(r);
            }else{
                r = k;
                t = k - diff;
                for(long i = y - n + 1;i < y;++i){
                    if(i == t){
                        System.out.print(r + " ");
                        continue;
                    }
                    System.out.print(i + " ");
                }
                System.out.println(r);
            }
        }

    }
    public static long fun(long x,long y){
        return ((2 * x + 1 - y) * y ) / 2;
    }


    public static int solution(int n,long k,long x){
        dfs(1,k,x,n);
        return cnt;
    }

    public static void dfs(int idx,long k,long x,int n){
        if(path.size() == n && x == 0){
            res.add(new ArrayList<>(path));
            cnt++;
            return;
        }else{
            for(int i = idx;i <= k;++i){
                path.add(i);
                x -= i;
                dfs(i+1,k,x,n);
                x += i;
                path.remove(path.size() - 1);
            }
        }
    }
}
