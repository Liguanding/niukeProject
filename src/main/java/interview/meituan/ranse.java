package interview.meituan;

import java.util.Scanner;

public class ranse {

    public static double myPow(double x, int n) {
        if(x == 0.0f)
            return 0.0d;

        long b = n;
        double res = 1.0;
        if(b < 0){
            x = 1 / x;
            b = -b;
        }
        while(b > 0){
            if((b & 1) == 1){
                res *= x;
            }
            x *= x;
            b >>= 1;
        }

        return res;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
//        System.out.println(myPow(-1,4)*(n-1) + myPow(n-1,4));
        long res = new Double(myPow(-1,4)*(n-1) + myPow(n-1,4)).longValue() % 1000000007;
        System.out.println(res);
    }

}
