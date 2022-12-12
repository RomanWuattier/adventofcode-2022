package com.romanwuattier.adventofcode2022.days;

import com.romanwuattier.adventofcode2022.common.Day;
import com.romanwuattier.adventofcode2022.common.FileOperations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.function.Function;

public class Day12 implements Day {
  public static void main(String[] args) {
    new Day12().wakeup();
  }

  @Override
  public Object[] wakeup(FileOperations fo) {
    var lines = fo.readDay(12);
    var grid = new char[lines.size()][lines.get(0).length()];
    final int[] start = new int[2];
    final int[] end = new int[2];
    List<int[]> starts = new ArrayList<>();
    for (int r = 0; r < grid.length; r++) {
      var row = lines.get(r);
      for (int c = 0; c < row.length(); c++) {
        grid[r][c] = row.charAt(c);
        if (grid[r][c] == 'S') {
          start[0] = r;
          start[1] = c;
          grid[r][c] = 'a';
        } else if (grid[r][c] == 'E') {
          end[0] = r;
          end[1] = c;
          grid[r][c] = 'z';
        }
        if (grid[r][c] == 'a') {
          starts.add(new int[] {r, c});
        }
      }
    }
    return new Object[] {
      bfs(grid, start, end),
      starts.stream().mapToInt(p -> bfs(grid, p, end)).filter(i -> i != -1).min().getAsInt()
    };
  }

  private int bfs(char[][] g, int[] start, int[] end) {
    var dirs = new int[][] {new int[] {1, 0}, new int[] {-1, 0}, new int[] {0, 1}, new int[] {0, -1}};
    Function<int[], String> kg = dp -> dp[0] + ":" + dp[1];
    Set<String> seen = new HashSet<>();
    seen.add(start[0] + ":" + start[1]);
    Queue<int[]> q = new LinkedList<>();
    q.offer(start);
    int path = 0;
    while (!q.isEmpty()) {
      for (int s = q.size(); s > 0; s--) {
        var xy = q.remove();
        if (Arrays.equals(xy, end)) {
          return path;
        }
        Arrays.stream(dirs)
            .map(d -> new int[] {xy[0] + d[0], xy[1] + d[1]})
            .filter(dp -> dp[0] >= 0 && dp[0] < g.length && dp[1] >= 0 && dp[1] < g[0].length)
            .filter(dp -> !seen.contains(kg.apply(dp)))
            .filter(dp -> g[dp[0]][dp[1]] - g[xy[0]][xy[1]] <= 1)
            .forEach(
                dp -> {
                  seen.add(kg.apply(dp));
                  q.offer(dp);
                });
      }
      path++;
    }
    return -1;
  }
}
