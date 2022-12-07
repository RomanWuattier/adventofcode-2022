package com.romanwuattier.adventofcode2022.days;

import com.romanwuattier.adventofcode2022.common.Day;
import com.romanwuattier.adventofcode2022.common.FileOperations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day7 implements Day {
  public static void main(String[] args) {
    new Day7().wakeup();
  }

  @Override
  public Object[] wakeup(FileOperations fo) {
    var lines = fo.readDay(7);
    var cds = new HashMap<String, Integer>();
    var path = new ArrayList<String>();
    for (int i = 0; i < lines.size(); i++) {
      var l = lines.get(i);
      if (l.equals("$ cd ..")) {
        path.remove(path.size() - 1);
      } else if (l.startsWith("$ cd")) {
        path.add(l.split(" ")[2]);
        cds.put(String.join("", path), i);
      }
    }

    var dirSize = new HashMap<String, Integer>();
    var used = dfs(lines, "/", cds, dirSize);
    var p1 = dirSize.values().stream().filter(i -> i < 100000).mapToInt(i -> i).sum();
    var p2 =
        dirSize.values().stream().filter(i -> (70000000 - used) + i >= 30000000).sorted().findFirst();
    return new Object[] {p1, p2.orElseThrow()};
  }

  private int dfs(
      List<String> lines, String path, Map<String, Integer> cds, Map<String, Integer> dirSize) {
    int sum = 0;
    int i = cds.get(path) + 1;
    if (lines.get(i).equals("$ ls")) {
      for (int j = i + 1; j < lines.size() && !lines.get(j).startsWith("$"); j++) {
        var l = lines.get(j).split(" ");
        sum += l[0].equals("dir") ? dfs(lines, path + l[1], cds, dirSize) : Integer.parseInt(l[0]);
      }
      dirSize.put(path, sum);
    }
    return sum;
  }
}
