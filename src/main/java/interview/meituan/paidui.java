package interview.meituan;

import java.util.Arrays;
import java.util.Scanner;

public class paidui {

    static class Solider implements Comparable<Solider>{
        String name;
        int height;
        Solider(String name,int height){
            this.name = name;
            this.height = height;
        }

        @Override
        public int compareTo(Solider o) {
            if(o.height != this.height){
                return this.height - o.height;
            }else{
                return this.name.compareTo(o.name);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        Solider[] soliders = new Solider[n];
        Arrays.fill(soliders,null);
        for(int i = 0;i < n;++i){
            soliders[i] = new Solider("",sc.nextInt());
        }
        sc.nextLine();
        String nameContent = sc.nextLine();
        String[] names = nameContent.split(" ");

        for(int i = 0;i < n;++i){
            soliders[i].name = names[i];
        }

        Arrays.sort(soliders);
        for(Solider solider : soliders){
            //多个空格要处理
            System.out.print(solider.name + " ");
        }
    }
}
