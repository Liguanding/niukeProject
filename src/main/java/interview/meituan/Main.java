package interview.meituan;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            int n = Integer.valueOf(sc.nextLine());
            List<List<Integer>> list = new ArrayList<>();
            for (int i = 0; i < n; ++i) {
                String str = sc.nextLine();
                String[] strs = str.split(" ");
                List<Integer> tmp = new ArrayList<>();
                for (String s : strs) {
                    tmp.add(Integer.valueOf(s));
                }
                list.add(tmp);
            }
            for (int i = 0; i < n; ++i) {
                System.out.println(startVideo(list, i, 0));
            }
        }
//        int n = 4;
//        int[] arr = new int[]{-2,-6,15,4,5};
//        int[] arr = new int[]{2,3,1,4};
//        System.out.println(getAll(n,arr));
    }

    //直接暴力试试
    public static int getAll(int n, int[] arr) {
        int res = 0;
        for (int i = 0; i < n; ++i) {
            int j = i;
            while (j < n) {
                int L = j - i + 1;
                if (L % 2 == 1) {
                    int[] tmp = new int[L];
                    for (int k = i; k <= j; ++k) {
                        tmp[k - i] = arr[k];
                    }
                    int midTmp = findMid(tmp);
                    res += midTmp;
                }
                j++;
            }

        }
        return res;
    }

    public static int findMid(int[] arr) {
        Arrays.sort(arr);
        int n = arr.length;
        //n一定是奇数
        return arr[n / 2];
    }


    public static int getAllMid(int n, int[] arr) {

        int res = 0;
        for (int i = 0; i < n; ++i) {
            PriorityQueue<Integer> qMax = new PriorityQueue<>((a, b) -> {
                return b - a;
            });

        }


        return res;
    }

    //    public static int dfs(List<List<Integer>> list,int idx,int cur) {
//
//    }
    public static int startVideo(List<List<Integer>> list, int idx, int cur) {
        if (list.get(idx).get(1) == 0) {
            return cur + list.get(idx).get(0);
        }
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < list.get(idx).get(1); ++i) {
            res = Math.max(res, list.get(idx).get(0) + startVideo(list, list.get(idx).get(i + 2), cur));
        }
        return res;

    }


    public static int pickCard(int n, int[] arr) {
        int res = 0;
        int max = Arrays.stream(arr).max().getAsInt();

//        和是7的倍数
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, n); // 可以多无限个0;
        for (int num : arr) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (int num : arr) {
            int tmp = num % 7 == 0 ? num : (num / 7 + 1) * 7; //找到离num最小的上界7的倍数
            if (map.containsKey(tmp - num)) {
                map.put(tmp, map.getOrDefault(tmp, 0) + 1);
                res = Math.max(res, tmp);
            }
        }
        return res;
    }




/*
    //暴力
    public static int getMinDis(int[] arr){
        int res = Integer.MAX_VALUE;
        int n = arr.length;
        int a = arr[0],b = arr[n-1];
        for(int i = 0;i < n;++i){
            int disDiff = Math.abs(Math.abs(arr[i] - a) - Math.abs(arr[i] - b));

            res = Math.min(res,disDiff);
        }
        return  res;
    }
*/


    public static int countStr(String str) {
        int res = 0;
//        acbcca
        Map<Character, Integer> map = new HashMap<>();
        for (char c : str.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int cntOfa = map.getOrDefault('a', 0);
        int cntOfb = map.getOrDefault('b', 0);
        int cntOfc = map.getOrDefault('c', 0);
        if (cntOfa < 2 || cntOfb < 1 || cntOfc < 3) {
            return 0;
        }
        cntOfa -= 2;
        cntOfb -= 1;
        cntOfc -= 3;
        res++; // 第一套
        while (cntOfa >= 1 && cntOfb >= 1 && cntOfc >= 3) {
            cntOfa -= 1;
            cntOfb -= 1;
            cntOfc -= 3;
            res++;
        }
        return res;
    }
}
