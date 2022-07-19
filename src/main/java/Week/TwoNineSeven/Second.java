package Week.TwoNineSeven;

import java.util.*;
import java.util.stream.Collectors;

public class Second {

    public static void main(String[] args) {
        Second second = new Second();
        int[][] grid = new int[][]{{5,3},{4,0},{2,1}};
        int[][] moveCost = new int[][]{{9,8},{1,5},{10,12},{18,6},{2,4},{14,3}};
        int ans = second.minPathCost(grid,moveCost);
        System.out.println(ans);
    }

    public int minPathCost(int[][] grid, int[][] moveCost) {
        int min = Integer.MAX_VALUE;
        for(int i = 0;i < grid[0].length;++i){
            min = Math.min(min,dfs(0,grid[0][i],grid,moveCost,grid[0][i]));
        }
        return min;
    }

    public int dfs(int layer,int from,int[][] grid,int[][] moveCost,int res){
        if(layer >= grid.length-1){
            return res;
        }
        int n = grid[0].length;
        int min = Integer.MAX_VALUE;
        for(int i = 0;i < n;++i){
            int to = grid[layer+1][i];
                min = Math.min(min,dfs(layer+1,to,grid,moveCost,to + res+moveCost[from][i]));
        }
        return min;
    }

}
