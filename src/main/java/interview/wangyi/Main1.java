package interview.wangyi;

import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
        printO(3);
    }

    public static void printO(int n){
        for(int i = 0;i < n;++i){
            for(int j = 0;j < n-i;++j){
                System.out.print(".");
            }
            for(int k = 0;k < 4 * n -(2 * (n-i)) ;++k){
                System.out.print("*");
            }
            for(int j = 0;j < n-i;++j){
                System.out.print(".");
            }
            System.out.println();
        }

        for(int i = 0;i < 2 * n;++i){
            for(int j = 0;j < n;++j){
                System.out.print("*");
            }
            for(int j = 0;j < 2 * n;++j){
                System.out.print(".");
            }
            for(int j = 0;j < n;++j){
                System.out.print("*");
            }
            System.out.println();
        }

        for(int i = 0;i < n;++i){
            for(int j = 0;j <= i;++j){
                System.out.print(".");
            }
            for(int k = 0;k < 4 * n - (2 * (i + 1)) ;++k){
                System.out.print("*");
            }
            for(int j = 0;j <= i;++j){
                System.out.print(".");
            }
            System.out.println();
        }
    }
}
