package interview.Bank;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Neiji {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String strA = sc.nextLine();
        String strB = sc.nextLine();
        System.out.println(solute(strA,strB));
    }

    public static int solute(String str1,String str2){
        PriorityQueue<String> queue = new PriorityQueue<>();
        Map<String,Integer> hash1 = new HashMap<>();
        Map<String,Integer> hash2 = new HashMap<>();

        String[] strs1 = str1.split(" ");
        String[] strs2 = str2.split(" ");
        for(String s : strs1){
            hash1.put(s,hash1.getOrDefault(s,0) + 1);
            if(!queue.contains(s)){
                queue.add(s);
            }
        }
        for(String s : strs2){
            hash2.put(s,hash2.getOrDefault(s,0) + 1);
            if(!queue.contains(s)){
                queue.add(s);
            }
        }

        int res = 0;
        while(!queue.isEmpty()){
            String s = queue.poll();
            res += hash1.getOrDefault(s,0) * hash2.getOrDefault(s,0);
        }
        return res;
    }
}
