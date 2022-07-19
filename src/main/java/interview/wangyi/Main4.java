package interview.wangyi;

import java.util.Scanner;

public class Main4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        sc.nextLine();
        for(int i = 0;i < n;++i){
            arr[i] = sc.nextInt();
        }
        solution(n,arr);

    }

    public static void solution(int n,int[] arr){
        int[][] dp = new int[n][n]; //dp[i][j] 代表i到j的o的个数
        long res = 0l;
        int[][] pre = new int[n][2]; // pre[i][0] 存放0 到 i的乘积非0， pre[i][1] 存放0到i的乘积的0的个数
        //初始化
        int numOrZero = countZero(arr[0]);
        pre[0][0] = arr[0];
        pre[0][1] = numOrZero;
        while(numOrZero > 0){
            pre[0][0] /= 10;
            numOrZero--;
        }
//        System.out.println(pre[0][0] + "  " + pre[0][1]);
        for(int i = 1;i < n;++i){
            int tmp = pre[i-1][0] * arr[i];
            numOrZero = countZero(tmp);
            pre[i][0] = tmp;
            pre[i][1] = pre[i-1][1] + numOrZero;
            while(numOrZero > 0){
                pre[i][0] /= 10;
                numOrZero--;
            }
        }
//        for(int i = 0;i < pre.length;++i){
//            System.out.println(pre[i][0] + " " + pre[i][1]);
//        }
        for(int i = 0;i < n;++i){
            dp[0][i] = pre[i][1];
            res += dp[0][i];
        }
//        print(dp);
        for(int i = 1;i < n;++i){
            for(int j = i;j < n;++j){
                if(i == j){
                    dp[i][j] = countZero(arr[i]);
                }else{
                    dp[i][j] = pre[j][1] - pre[i][1];
                }
                res += dp[i][j];
            }
        }
//        for(int i = 0;i < n;++i){
//            for(int j = i;j < n;++j){
//                res += dp[i][j];
//            }
//        }
        System.out.println(res);

    }

    public static void print(int[][] arr){
        for(int i = 0;i < arr.length;++i){
            for(int j = 0;j < arr[0].length;++j)
                System.out.print(arr[i][j] + " ");
            System.out.println();
        }
    }



//    public static void solution1(int n,int[] arr){
//        long res = 0;
//        for(int i = 0;i < n;++i){
//            for(int j = i;j < n;++j){
//                long tmp = 1;
//                for(int k = i;k <= j;++k)
//                    tmp *= arr[k];
//                String str = Long.toString(tmp);
//                res += countZero(str);
//            }
//        }
//        System.out.println(res);
//    }

    public static int countZero(int num){
        int res = 0;
        while(num > 0 && num % 10 == 0){
            num /= 10;
            res ++;
        }
        return res;
    }

//
//    public static int countZero(String str){
//        int cnt = 0;
//        for(int i = str.length()-1;i >= 0;--i){
//            if(str.charAt(i) == '0')
//                cnt++;
//            else
//                break;
//        }
//        return cnt;
//    }

}
