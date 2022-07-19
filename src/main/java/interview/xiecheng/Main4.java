package interview.xiecheng;

import java.util.Scanner;

public class Main4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = 4;
        int[] arr = new int[]{25,30,125,64};
//        int i = cntOfZero(n, arr);
//        int n = sc.nextInt();
//        int[] arr = new int[n];
//        sc.nextLine();
//        for(int i = 0;i < n;++i){
//            arr[i] = sc.nextInt();
//        }
//        int[] yinzi = getYinzi(125);
//        for (int i : yinzi) {
//            System.out.println(i);
//        }

//        System.out.println(cntOfZero(n,arr));

    }

    public static int cntOfZero(int n,int[] arr){
        int res = 0;
        int size = (1 << n);
        int[] dp = new int[size];
        dp[1] = getNumOfZero(arr[0]);
        for(int i = 2;i < size;++i){

            for(int j = 1;j < n;++j){
                //如果前一个选择了，就不能再选
                int tmp = (0xFFFF ^ (1 << j-1));
                int tmp1 = (0xFFFF ^ (1 << j));
                //前一个不选，选这一个,以及这个不选，选前一个
                //应该是乘积的0
                dp[i] = Math.max(dp[i & tmp1],dp[i & tmp] + getNumOfZero(arr[j]));

              /*  if(j >= 1 && (i & (1 << j-1))== 1){
                    //如果前一个选择了，就不能再选
                    int tmp = (0xFFFF ^ (1 << j-1));
                    int tmp1 = (0xFFFF ^ (1 << j));
                    //前一个不选，选这一个,以及这个不选，选前一个
                    dp[i] = Math.max(dp[i & tmp1],dp[i & tmp] + getNumOfZero(arr[j]));
                    continue;
                }*/




            }

            res = Math.max(res,dp[i]);
        }
        return res;
    }

    public static int getNumOfZero(int n){
        int res = 0;
        while (n % 10 == 0){
            res++;
            n /= 10;
        }
        return res;
    }

    public static int[] getYinzi(int n){
        int[] res = new int[3];
        //res[0] 代表因子为10的个数，
        //res[1] 代表因子为5的个数，
        //res[2] 代表因子为2的个数，
        while(n % 10 == 0){
            res[0]++;
            n /= 10;
        }
        while(n % 5 == 0){
            res[1]++;
            n /= 5;
        }
        while(n % 2 == 0){
            res[2]++;
            n /= 2;
        }
        return res;
    }

}
