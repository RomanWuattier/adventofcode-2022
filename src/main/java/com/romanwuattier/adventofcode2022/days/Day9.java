package com.romanwuattier.adventofcode2022.days;

import com.romanwuattier.adventofcode2022.common.Day;
import com.romanwuattier.adventofcode2022.common.FileOperations;

import java.util.HashSet;
import java.util.List;
import java.util.stream.IntStream;

public class Day9 implements Day {
  public static void main(String[] args) {
    new Day9().wakeup();
  }

  @Override
  public Object[] wakeup(FileOperations fo) {
    var lines = fo.readDay(9);
    return new Object[] {calculate(lines, 2), calculate(lines, 10)};
  }

  private int calculate(List<String> moves, int length) {
    var visited = new HashSet<String>();
    var ropes = IntStream.range(0, length).mapToObj(__ -> new int[2]).toList();
    for (var move : moves) {
      var m = move.split(" ");
      char d = m[0].charAt(0);
      int n = Integer.parseInt(m[1]);
      var head = ropes.get(0);
      var tail = ropes.get(ropes.size() - 1);
      for (int i = 0; i < n; i++) {
        switch (d) {
          case 'U' -> head[0]++;
          case 'D' -> head[0]--;
          case 'R' -> head[1]++;
          case 'L' -> head[1]--;
          default -> throw new IllegalStateException();
        }
        for (int rope = 1; rope < length; rope++) {
          var h = ropes.get(rope - 1);
          var t = ropes.get(rope);
          if (Math.abs(t[0] - h[0]) > 1 || Math.abs(t[1] - h[1]) > 1) {
            t[0] += Integer.compare(h[0], t[0]);
            t[1] += Integer.compare(h[1], t[1]);
            visited.add(tail[0] + "," + tail[1]);
          }
        }
      }
    }
    return visited.size();
  }
}
