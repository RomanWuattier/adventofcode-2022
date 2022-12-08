package com.romanwuattier.adventofcode2022.days;

import com.romanwuattier.adventofcode2022.common.Day;
import com.romanwuattier.adventofcode2022.common.FileOperations;

import java.util.Arrays;

public class Day8 implements Day {
  public static void main(String[] args) {
    new Day8().wakeup();
  }

  @Override
  public Object[] wakeup(FileOperations fo) {
    int[][] grid =
        readDay(8).stream()
            .map(l -> Arrays.stream(l.split("")).mapToInt(Integer::parseInt).toArray())
            .toArray(int[][]::new);

    int p1 = 0, p2 = 1;
    for (int r = 0; r < grid.length; r++) {
      for (int c = 0; c < grid[0].length; c++) {
        var up = traverse(grid, r, c, 0, 1);
        var down = traverse(grid, r, c, 0, -1);
        var left = traverse(grid, r, c, -1, 0);
        var right = traverse(grid, r, c, 1, 0);
        p1 += up.p1 || down.p1 || left.p1 || right.p1 ? 1 : 0;
        p2 = Math.max(p2, up.p2 * down.p2 * left.p2 * right.p2);
      }
    }
    return new Object[] {p1, p2};
  }

  private Pair traverse(int[][] grid, int r, int c, int dx, int dy) {
    int v = grid[r][c];
    int y = r + dy;
    int x = c + dx;
    int sum = 0;
    while (y >= 0 && y < grid.length && x >= 0 && x < grid[0].length) {
      sum++;
      if (grid[y][x] >= v) {
        break;
      }
      y += dy;
      x += dx;
    }
    return new Pair(y < 0 || y >= grid.length || x < 0 || x >= grid[0].length, sum);
  }

  private record Pair(boolean p1, int p2) {}
}
