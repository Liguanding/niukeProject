package interview.xiecheng;

import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int a = sc.nextInt();
        int b = sc.nextInt();
        maxValue(n,m,a,b);
    }

    public static void maxValue(int n,int m,int a,int b){
        int[][] dp = new int[n+1][m+1];
        int[][] dp1 = new int[4][4];
        //dp1代表i-1,j-2,dp2代表i-2,j-1,dp3代表i,j
        if(n < 2 && m < 2){
            System.out.println(0);
            return;
        }
        dp1[2][1] = a;
        dp1[1][2] = b;

        for(int i = 1;i <= n;++i){
            for(int j = 1;j <= m;++j){
                if(i >= 2 && j >= 2)
                    dp1[3][3] = Math.max(dp[i-1][j-2] + b,dp[i-2][j-1] + a);
                else if(i >= 2 && j < 2){
                    dp[i][j] = dp[i-2][j-1] + a;
                }else if(j >= 2 && i < 2){
                    dp[i][j] = dp[i-1][j-2] + b;
                }
            }
        }

//        for(int i = 1;i <= n;++i){
//            for(int j = 1;j <= m;++j){
//                if(i >= 2 && j >= 2)
//                    dp[i][j] = Math.max(dp[i-1][j-2] + b,dp[i-2][j-1] + a);
//                else if(i >= 2 && j < 2){
//                    dp[i][j] = dp[i-2][j-1] + a;
//                }else if(j >= 2 && i < 2){
//                    dp[i][j] = dp[i-1][j-2] + b;
//                }
//            }
//        }
        System.out.println(dp[n][m]);
    }

}
