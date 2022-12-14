package com.romanwuattier.adventofcode2022.days;

import com.romanwuattier.adventofcode2022.common.Day;
import com.romanwuattier.adventofcode2022.common.FileOperations;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiFunction;

public class Day14 implements Day {
  public static void main(String[] args) {
    new Day14().wakeup();
  }

  @Override
  public Object[] wakeup(FileOperations fo) {
    BiFunction<Integer, Integer, String> kg = (a, b) -> a + ":" + b;
    var lines =
        fo.readDay(14).stream()
            .map(
                l ->
                    Arrays.stream(l.split(" -> "))
                        .map(xy -> xy.split(","))
                        .map(a -> new int[] {Integer.parseInt(a[0]), Integer.parseInt(a[1])})
                        .toList())
            .toList();

    var obstacles = new HashSet<String>();
    var maxY = Integer.MIN_VALUE;
    for (var line : lines) {
      for (int i = 0; i < line.size() - 1; i++) {
        var xy1 = line.get(i);
        var xy2 = line.get(i + 1);
        var xOrdered = Arrays.stream(new int[] {xy1[0], xy2[0]}).sorted().toArray();
        var yOrdered = Arrays.stream(new int[] {xy1[1], xy2[1]}).sorted().toArray();
        for (int x = xOrdered[0]; x <= xOrdered[1]; x++) {
          for (int y = yOrdered[0]; y <= yOrdered[1]; y++) {
            obstacles.add(kg.apply(x, y));
            maxY = Math.max(maxY, y);
          }
        }
      }
    }

    var mutObstacle = new HashSet<>(obstacles);
    int[] p1 = new int[1];
    while (dfs(new int[] {500, 0}, maxY, p1, mutObstacle, kg, false));
    mutObstacle = new HashSet<>(obstacles);
    int[] p2 = new int[1];
    while (dfs(new int[] {500, 0}, maxY, p2, mutObstacle, kg, true));
    return new Object[] {p1[0], p2[0]};
  }

  private boolean dfs(
      int[] xy,
      int maxY,
      int[] counter,
      Set<String> obstacles,
      BiFunction<Integer, Integer, String> kg,
      boolean p2) {
    var x = xy[0];
    var y = xy[1];
    if ((!p2 && y > maxY) || (p2 && obstacles.contains(kg.apply(x, y)))) {
      return false;
    }

    if (!p2 || y < maxY + 1) {
      var dirs = new int[][] {{0, 1}, {-1, 1}, {1, 1}};
      for (var d : dirs) {
        var dx = x + d[0];
        var dy = y + d[1];
        if (!obstacles.contains(kg.apply(dx, dy))) {
          return dfs(new int[] {dx, dy}, maxY, counter, obstacles, kg, p2);
        }
      }
    }
    obstacles.add(kg.apply(x, y));
    counter[0]++;
    return true;
  }
}
