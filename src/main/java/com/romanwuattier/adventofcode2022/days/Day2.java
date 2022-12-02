package com.romanwuattier.adventofcode2022.days;

import com.romanwuattier.adventofcode2022.common.Day;
import com.romanwuattier.adventofcode2022.common.FileOperations;

import java.util.Map;

public class Day2 implements Day {
  public static void main(String[] args) {
    new Day2().wakeup();
  }

  private static final Map<String, Integer> RULES_1 =
      Map.of(
          "A X", 4,
          "A Y", 8,
          "A Z", 3,
          "B X", 1,
          "B Y", 5,
          "B Z", 9,
          "C X", 7,
          "C Y", 2,
          "C Z", 6);

  private static final Map<String, Integer> RULES_2 =
      Map.of(
          "A X", 3,
          "A Y", 4,
          "A Z", 8,
          "B X", 1,
          "B Y", 5,
          "B Z", 9,
          "C X", 2,
          "C Y", 6,
          "C Z", 7);

  @Override
  public Object[] wakeup(FileOperations fo) {
    var rounds = fo.readDay(2);
    var p1 = rounds.stream().mapToInt(RULES_1::get).sum();
    var p2 = rounds.stream().mapToInt(RULES_2::get).sum();
    return new Object[] {p1, p2};
  }
}
