package com.romanwuattier.adventofcode2022.days;

import com.romanwuattier.adventofcode2022.common.Day;
import com.romanwuattier.adventofcode2022.common.FileOperations;

import java.util.HashMap;

public class Day1 implements Day {
  public static void main(String[] args) {
    new Day1().wakeup();
  }

  @Override
  public Object[] wakeup(FileOperations fo) {
    var sum = new HashMap<Integer, Integer>();
    int k = 0;
    for (var l : fo.readDay(1)) {
      if (l.isEmpty()) {
        k++;
      } else {
        sum.put(k, sum.getOrDefault(k, 0) + Integer.parseInt(l));
      }
    }
    var desc = sum.values().stream().sorted((a, b) -> b - a).mapToInt(i -> i).toArray();
    return new Object[] {desc[0], desc[0] + desc[1] + desc[2]};
  }
}
