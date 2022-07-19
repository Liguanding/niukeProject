package interview.ByteDance;

import java.util.*;

public class Solution {
    int n;
    boolean[] visited;

    public static void main(String[] args) {
        Solution solution = new Solution();
        Scanner sc = new Scanner(System.in);
        solution.n = sc.nextInt();
        solution.visited = new boolean[solution.n + 1]; // n+1
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 1; i <= solution.n; ++i) {
            List<Integer> list = new ArrayList<>();
            int num = sc.nextInt();
            for (int j = 0; j < num; ++j) {
                list.add(sc.nextInt());
            }
            map.put(i, list);
        }
        int res = Integer.MAX_VALUE;
        for (int i = 1; i <= solution.n; ++i) {
            res = Math.min(solution.bfs(i, map), res);
        }

        System.out.println(res);
    }

    private int bfs(int idx, Map<Integer, List<Integer>> map) {

        int vis = 0;
        Deque<Integer> queue = new LinkedList<>();
        queue.addLast(idx);
        visited[idx] = true;
        vis++; // 记录已经接受到消息的个数
        int time = 0;
        while (!queue.isEmpty()) {
            int i = queue.pollFirst();
            for (int x : map.get(i)) {
                if (!visited[x]) {
                    queue.addLast(x);
                    visited[x] = true;
                    vis++;
                }
            }
            time++;
            if (vis == n) {
                return time;
            }
        }
        return time;
    }
}
