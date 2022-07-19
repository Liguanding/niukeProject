package interview.xiecheng;
import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        sc.nextLine();
        for(int i = 0;i < n;++i){
            arr[i] = sc.nextInt();
        }
        mergeSameNum(n,arr);
    }

    public static void mergeSameNum(int n,int[] arr){
//        Map<Integer,Integer> map = new HashMap<>();
//        for(int i = 0;i < n;++i){
//            map.put(arr[i],map.getOrDefault(arr[i],0) + 1);
//        }
        int i = 0;
        while (i < n){
            int cnt = 1;
            while (i < n-1 && arr[i+1] == arr[i]){
                cnt++;
                i++;
            }
            if(i < n-1){
                if(cnt > 1)
                    System.out.print(arr[i] + "(" + cnt + ") ");
                else
                    System.out.print(arr[i] + " ");
            }else{
                if(cnt > 1)
                    System.out.println(arr[i] + "(" + cnt + ")");
                else
                    System.out.println(arr[i]);
            }
            ++i;
        }

    }

}
