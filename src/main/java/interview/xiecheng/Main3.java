package interview.xiecheng;

import java.util.Scanner;

public class Main3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int[][] dis = new int[26][26];

        for(int i = 0;i < 26;++i){
            for(int j = 0;j < 26;++j){
                if(i == j){
                    dis[i][j] = 0;
                }else {
                    dis[i][j] = getDistance((char)('a'+i),(char)('a'+j));
                    dis[j][i] =  dis[i][j];
                }
            }
        }
        int res = Integer.MAX_VALUE;
        for(int i = 0;i < 26;++i){
//            char c = (char)('a' + i);
            int cnt = 0;
            for(char c1 : str.toCharArray()){
                cnt += dis[c1 - 'a'][i];
            }
            res = Math.min(res,cnt);
        }
        System.out.println(res);

    }

    public static int getDistance(char c1,char c2){
        int id1 = c1 - 'a' + 1;
        int id2 = c2 - 'a' + 1;

        if(id1 == id2){
            return 0;
        }else if(id1 > id2){
            return Math.min(id1 - id2,id2 + 26 - id1);
        }else{
            return Math.min(id2 - id1,id1 + 26 - id2);
        }

    }

}

