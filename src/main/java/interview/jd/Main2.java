package interview.jd;


import java.util.*;

public class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        for(int i = 0;i < n;++i){
            int len = sc.nextInt();
            int[] arr = new int[len];
            sc.nextLine();
            for(int j = 0;j < len;++j){
                arr[j] = sc.nextInt();
            }
            sc.nextLine();
            System.out.println(getMidium(arr));
        }
    }

//    static class DualHeap{
//        private PriorityQueue<Integer> small;
//        private PriorityQueue<Integer> big;
//
//        private int k;
//        private int smallSize;
//        private int bigSize;
//
//    }

    public static int getMidium(int[] arr){
        int n = arr.length;
        List<Integer> even = new ArrayList<>();
        List<Integer> odd = new ArrayList<>();
        boolean[] visited = new boolean[n];
        Arrays.fill(visited,false);

        for(int i = 0;i < n;++i){
            if(i % 2 == 0){
                even.add(arr[i]);
            }else{
                odd.add(arr[i]);
            }
        }
        int mid1 = getM(even);
        int mid2 = getM(odd);
        int res = Math.max(mid1,mid2);
        for(int i = 1;i < n;i = i+2){
            even.add(arr[i]);
            int tmp = getM(even);
            if(tmp >= mid1){
                mid1 = tmp;
                res = Math.max(mid1,res);
            }else{
                even.remove(even.size()-1);
            }
        }

        for(int i = 0;i < n;i = i+2){
            odd.add(arr[i]);
            int tmp = getM(odd);
            if(tmp >= mid1){
                mid2 = tmp;
                res = Math.max(mid2,res);
            }else{
                odd.remove(odd.size()-1);
            }
        }
        return res;
    }

    public static int getMidium1(int[] arr){
        int n = arr.length;
        if(n == 1 || n == 2){
            return arr[0];
        }

        List<Integer> list = new ArrayList<>();
        boolean[] visited = new boolean[n];
        Arrays.fill(visited,false);

        if(arr[0] < arr[1]){
            visited[0] = false;
        }else{
            list.add(arr[0]);
            visited[0] = true;
        }

        for(int i = 1;i < n;++i){
//            if(visited[i-1] == false){
//                list.add(arr[i]);
//                visited[i] = true;
//                continue;
//            }else {
//                if(arr[i] > arr[i-1]){
//                    list.add(arr[i]);
//                    visited[i] = true;
//                }
//            }
            int a = arr[i-1],b = arr[i];
            if(b > a){
                list.add(b);
                visited[i] = true;
            }else{
                if(visited[i-1] == false){
                    list.add(a);
                    visited[i] = true;
                }
            }
        }
        return getM(list);
    }


    public static int getM(List<Integer> list){
        Collections.sort(list);
        return list.get(list.size()/2);
    }


}
