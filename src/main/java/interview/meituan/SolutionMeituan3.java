package interview.meituan;

import java.util.Arrays;

public class SolutionMeituan3 {

    //求一个数组中所有奇数长度的中位数的和

    /**
     * 给一个整数数列，求所有奇数长度子区间的中位数之和
     * 输入：[1,3,6,7]
     * 长度为1的子区间：1 3 6 7
     * 长度为3的子区间：[1,3,6]，[3,6,7]
     * 所以ans = 1 + 3 + 6 + 7 + 3 + 6
     *
     * @param args
     */
    public static void main(String[] args) {
        SolutionMeituan3 solutionMeituan3 = new SolutionMeituan3();

        int[] nums = new int[]{0,4,3,6,7,8,9};
        System.out.println(solutionMeituan3.deal(nums,6));
    }


    public int deal(int[] nums,int n){
        int[][] dp = new int[n+1][7]; // dp[i][j] 从0到i mod 7 = j 的最大数的和
        for(int[] tmp : dp){
            Arrays.fill(tmp,Integer.MIN_VALUE);
        }
        dp[0][0] = 0;
        for(int i = 1;i <= n;++i){
            for(int j = 0;j <= 6;++j){
                dp[i][j] = Math.max(dp[i][j],dp[i-1][j]);
                dp[i][j] = Math.max(dp[i][j],dp[i-1][((j - nums[i]) % 7 + 7) % 7] + nums[i]);
            }
        }
        return dp[n][0];
    }



}
