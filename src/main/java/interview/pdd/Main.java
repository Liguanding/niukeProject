package interview.pdd;

import java.util.*;

public class Main {

    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        while(sc.hasNext()){
//            String s = sc.nextLine();
//            Map<String,Integer> map = new TreeMap<>();
//            execute(s,map,0,s.length()-1,1);
//            for(Map.Entry<String,Integer> entry:map.entrySet()){
//                System.out.println(entry.getKey()+ " " + entry.getValue());
//            }
//        }
        int[] arr = new int[]{10, 5, 9, 9, 5, 10};
        boolean flag = charge(arr);
        System.out.println(flag);

    }

    public static void execute(String s, Map<String, Integer> map, int l, int r, int count) {
        int n = r + 1, i = l;
        while (i < n) {
            StringBuilder sb = new StringBuilder().append(s.charAt(i));
            if (s.charAt(i) >= 'A' && s.charAt(i) <= 'Z') {
                if (i + 1 < n && (s.charAt(i + 1) >= '1' && s.charAt(i + 1) <= '9')) {
                    //字母后直接是数字
                    String tmp = sb.toString();
                    map.put(tmp, map.getOrDefault(tmp, 0) + count * (s.charAt(i + 1) - '0'));
                    i++;
                } else if (i + 1 < n && (s.charAt(i + 1) >= 'a' && s.charAt(i + 1) <= 'z')) {
                    //两个字母为一个元素
                    sb.append(s.charAt(i + 1));
                    ++i;
                    if (i + 1 < n && (s.charAt(i + 1) >= '0' && s.charAt(i + 1) <= '9')) {
                        //两个字母元素 后有数字
                        String tmp = sb.toString();
                        map.put(tmp, map.getOrDefault(tmp, 0) + count * (s.charAt(i + 1) - '0'));
                        ++i;
                    } else {
                        //两个字母元素 后无数字
                        String tmp = sb.toString();
                        map.put(tmp, map.getOrDefault(tmp, 0) + count);
                    }

                } else {
                    //一个字母的元素，后面无数字情况
                    String tmp = sb.toString();
                    map.put(tmp, map.getOrDefault(tmp, 0) + count);
                }

            } else if (s.charAt(i) == '[' || s.charAt(i) == '(') {
                char c = s.charAt(i) == '[' ? ']' : ')';
                int newl = i + 1, k = 1;
                while (s.charAt(i + 1) != c)
                    ++i;
                if (i + 2 < n && (s.charAt(i + 2) >= '0' && s.charAt(i + 2) <= '9')) {
                    k = s.charAt(i + 2) - '0';
                }
                execute(s, map, newl, i, k * count);
            }
            ++i;
        }
    }

    public static boolean charge(int[] arr) {
        int n = arr.length;
        boolean flag = false;
        if (n % 2 == 0) {
            //n是偶数的情况
            int l = n / 2 - 1, r = n / 2;
            //l是中心的情况:一定是右边被删除了某个;
            int i = l - 1, j = l + 1;
            while (i >= 0 && j < n && arr[i] == arr[j]) {
                i--;
                j++;
            }
            //一定是删除右边的 所以j++;
            j++;
            while (i >= 0 && j < n && arr[i] == arr[j]) {
                i--;
                j++;
            }
            if (i == -1 && j == n) {
                return true;
            }

            //r 是中心的情况
            //l是中心的情况:一定是右边被删除了某个;
            i = r - 1;
            j = r + 1;
            while (i >= 0 && j < n && arr[i] == arr[j]) {
                i--;
                j++;
            }
            //一定是删除右边的 所以j++;
            i--;
            while (i >= 0 && j < n && arr[i] == arr[j]) {
                i--;
                j++;
            }
            if (i == -1 && j == n) {
                return true;
            }


        } else {
            //如果是奇数的情况
            int l = n / 2 - 1, m = n / 2, r = n / 2 + 1;
            //只有三种情况,[l,r][l,m][m,r];
            if (arr[l] == arr[r]) {
                //把中间m去了,判断是否回文即可
                int i = l - 1, j = r + 1;
                while (i >= 0 && j < n && arr[i] == arr[j]) {
                    i--;
                    j++;
                }
                if (i == -1 && j == n) {
                    return true;
                }
            } else if (arr[l] == arr[m]) {
                //删除右边的某个
                int i = l - 1, j = m + 1;
                while (i >= 0 && j < n && arr[i] == arr[j]) {
                    i--;
                    j++;
                }
                //一定是删除右边的 所以j++;
                j++;
                while (i >= 0 && j < n && arr[i] == arr[j]) {
                    i--;
                    j++;
                }
                if (i == -1 && j == n) {
                    return true;
                }
            } else if (arr[m] == arr[r]) {
                //一定是左边的被删除了某个
                int i = m - 1, j = r + 1;
                while (i >= 0 && j < n && arr[i] == arr[j]) {
                    i--;
                    j++;
                }
                //一定是删除左边的 所以i++;
                i--;
                while (i >= 0 && j < n && arr[i] == arr[j]) {
                    i--;
                    j++;
                }
                if (i == -1 && j == n) {
                    return true;
                }
            }
        }

        return false;
    }


}