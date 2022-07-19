package interview.DiDi;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int n = sc.nextInt();
        sc.nextLine();
//        Map<String,String> map = new HashMap<>();
        List<String[]> list = new ArrayList<>();
        for(int i = 0;i < n;++i){
            String[] strs = sc.nextLine().split(" ");
            list.add(strs);
//            map.put(strs[0],strs[1]);
        }
        transfer(str,list);
//        String tmp = "abcbcfgabc";
//        while(tmp.indexOf("abc") != -1)
//            tmp = tmp.replaceAll("abc", "AAa");
//        System.out.println(tmp);

    }

    public static void transfer(String str,List<String[]> list){
        for (String[] strs : list) {
            String to = strs[1];
            str = str.replaceAll(strs[0],strs[1]);
//            while(str.indexOf(strs[0]) != -1)
//                str = str.replaceAll(strs[0],strs[1]);
        }
        System.out.println(str);
    }
}
