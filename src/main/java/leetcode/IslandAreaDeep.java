package leetcode;

import java.util.LinkedList;
import java.util.Optional;

/**
 * 岛屿的最大面积
 * 给定一个包含了一些 0 和1的非空二维数组grid,
 * 一个岛屿是由四或者八个方向 (水平或垂直) 的1(代表土地) 构成的组合。
 * 你可以假设二维矩阵的四个边缘都被水包围着。找到给定的二维数组中最大的岛屿面积。
 * (如果没有岛屿，则返回面积为0。)
 * [[0,0,1,0,0,0,0,1,0,0,0,0,0],
 * [0,0,0,0,0,0,0,1,1,1,0,0,0],
 * [0,1,1,0,1,0,0,0,0,0,0,0,0],
 * [0,1,0,0,1,1,0,0,1,0,1,0,0],
 * [0,1,0,0,1,1,0,0,1,1,1,0,0],
 * [0,0,0,0,0,0,0,0,0,0,1,0,0],
 * [0,0,0,0,0,0,0,1,1,1,0,0,0],
 * [0,0,0,0,0,0,0,1,1,0,0,0,0]]
 */
public class IslandAreaDeep {
    private static int[][] grid = {
            {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
            {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
            {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}
    };

    public static void main(String[] args) {
        LinkedList<Integer> sortDesc = listAreaOfIsland(grid);
        System.out.println("面积列表：" + sortDesc.toString());
        System.out.println("其中面积最大为：" + getMaxArea(sortDesc));
    }

    /**
     * 根据面积列表取最大得
     *
     * @param sortList
     * @return
     */
    private static int getMaxArea(LinkedList<Integer> sortList) {
        return sortList.peekFirst() != null ? sortList.peekFirst().intValue() : 0;
    }

    /**
     * 根据二维数组求岛屿面积排序
     *
     * @param grid
     * @return
     */
    private static LinkedList<Integer> listAreaOfIsland(int[][] grid) {
//        int max = 0;
        LinkedList<Integer> sortAreaDesc = new LinkedList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    int area = depthSearchArea(grid, i, j);
//                    max = Math.max(area, max);
                    if (sortAreaDesc.size() != 0 || Optional.ofNullable(sortAreaDesc.peekFirst()).orElse(0) < area) {
                        sortAreaDesc.push(area);
                    } else {
                        sortAreaDesc.offerLast(area);
                    }
                }
            }
        }
        return sortAreaDesc;
    }

    /**
     * 递归深度搜索
     *
     * @param grid 搜索二维数组，搜索后避免重复搜索，需要置为0
     * @param i    当前y轴位置
     * @param j    当前x周位置
     * @return
     */
    private static int depthSearchArea(int[][] grid, int i, int j) {
        //位置合法判断，避免程序取值下标越界，当前等于1继续深度搜寻
        if (i >= 0 && i < grid.length && j >= 1 && j < grid[i].length - 1 && grid[i][j] == 1) {
            grid[i][j] = 0;
            //当前节点上下，左右，遇到非法值会判断不进入搜索
//            int num = 1 + depthSearchArea(grid, i + 1, j) + depthSearchArea(grid, i - 1, j) + depthSearchArea(grid, i, j + 1) + depthSearchArea(grid, i, j - 1);
            //当前节点上下，左右，左上，左下，右上，右下遇到非法值会判断不进入搜索
            int area = 1 + depthSearchArea(grid, i + 1, j) + depthSearchArea(grid, i - 1, j) + depthSearchArea(grid, i, j + 1) + depthSearchArea(grid, i, j - 1) +
                    depthSearchArea(grid, i - 1, j - 1) + depthSearchArea(grid, i + 1, j + 1) + depthSearchArea(grid, i - 1, j + 1) + depthSearchArea(grid, i + 1, j - 1);
            return area;
        }
        return 0;
    }
}
