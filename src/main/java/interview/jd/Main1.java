package interview.jd;

import java.util.Scanner;
import java.util.Stack;

public class Main1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        for(int i = 0;i < n;++i){
            int num = sc.nextInt();
            sc.nextLine();
            String tmp;
            String[] strs = new String[num];
            for(int j = 0;j < num;++j){
                tmp = sc.nextLine();
                tmp = myTrim(tmp);
//                System.out.println(tmp.length());
                strs[j] = tmp;
            }
            //System.out.println(strs[5].charAt(3) == ' ');
            isRight(strs);
        }

    }

    public static String myTrim(String str){
        StringBuilder sb = new StringBuilder();
        for(char a : str.toCharArray()){
            if((a >= 'a' && a <= 'z') ||(a >= 'A' && a <= 'Z')){
                sb.append(a);
            }
        }
        return sb.toString();
    }


    public static void isRight(String[] strs){
        Stack<String> stack = new Stack<>();
        for(String s : strs){
            if(!stack.isEmpty() && ("end" + stack.peek()).equals(s)){
                stack.pop();
                continue;
            }else {
                stack.push(s);
            }
        }
        System.out.println(stack.size() == 0 ? "Yes" : "No");
    }
}
