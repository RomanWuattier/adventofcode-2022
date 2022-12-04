package com.romanwuattier.adventofcode2022.days;

import com.romanwuattier.adventofcode2022.common.Day;
import com.romanwuattier.adventofcode2022.common.FileOperations;

import java.util.Arrays;

public class Day4 implements Day {
  public static void main(String[] args) {
    new Day4().wakeup();
  }

  @Override
  public Object[] wakeup(FileOperations fo) {
    var assignments = fo.readDay(4);
    int p1 = 0, p2 = 0;
    for (var a : assignments) {
      var pair = a.split(",");
      var i1 = Arrays.stream(pair[0].split("-")).mapToInt(Integer::parseInt).toArray();
      var i2 = Arrays.stream(pair[1].split("-")).mapToInt(Integer::parseInt).toArray();
      if (i1[0] > i1[1] || i2[0] > i2[1]) {
        throw new IllegalStateException();
      }
      if ((i1[0] >= i2[0] && i1[1] <= i2[1]) || (i2[0] >= i1[0] && i2[1] <= i1[1])) {
        p1++;
      }
      if (i1[0] >= i2[0] && i1[0] <= i2[1]
          || i1[1] >= i2[0] && i1[1] <= i2[1]
          || i2[0] >= i1[0] && i2[0] <= i1[1]) {
        p2++;
      }
    }
    return new Object[] {p1, p2};
  }
}
