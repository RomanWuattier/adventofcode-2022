package com.romanwuattier.adventofcode2022.days;

import com.romanwuattier.adventofcode2022.common.Day;
import com.romanwuattier.adventofcode2022.common.FileOperations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class Day11 implements Day {
  public static void main(String[] args) {
    new Day11().wakeup();
  }

  @Override
  public Object[] wakeup(FileOperations fo) {
    return new Object[] {play(true), play(false)};
  }

  private long play(boolean isPart1) {
    var m0 = new Monkey(0, List.of(71L, 86L), old -> old * 13, w -> w % 19 == 0 ? 6L : 7L, 19);
    var m1 =
        new Monkey(
            1, List.of(66L, 50L, 90L, 53L, 88L, 85L), old -> old + 3, w -> w % 2 == 0 ? 5L : 4L, 2);
    var m2 =
        new Monkey(
            2,
            List.of(97L, 54L, 89L, 62L, 84L, 80L, 63L),
            old -> old + 6,
            w -> w % 13 == 0 ? 4L : 1L,
            13);
    var m3 =
        new Monkey(3, List.of(82L, 97L, 56L, 92L), old -> old + 2, w -> w % 5 == 0 ? 6L : 0L, 5);
    var m4 =
        new Monkey(
            4, List.of(50L, 99L, 67L, 61L, 86L), old -> old * old, w -> w % 7 == 0 ? 5L : 3L, 7);
    var m5 =
        new Monkey(
            5,
            List.of(61L, 66L, 72L, 55L, 64L, 53L, 72L, 63L),
            old -> old + 4,
            w -> w % 11 == 0 ? 3L : 0L,
            11);
    var m6 = new Monkey(6, List.of(59L, 79L, 63L), old -> old * 7, w -> w % 17 == 0 ? 2L : 7L, 17);
    var m7 = new Monkey(7, List.of(55L), old -> old + 7, w -> w % 3 == 0 ? 2L : 1L, 3);
    var monkeys = Map.of(0L, m0, 1L, m1, 2L, m2, 3L, m3, 4L, m4, 5L, m5, 6L, m6, 7L, m7);
    long commonDivisor =
        monkeys.values().stream().mapToLong(m -> m.divisor).reduce((a, b) -> a * b).getAsLong();
    int round = 0;
    int times = isPart1 ? 20 : 10000;
    while (round < monkeys.size() * times) {
      var m = monkeys.get((long) round % monkeys.size());
      var s = m.startingItem.size();
      for (var item : m.startingItem) {
        long worry = isPart1 ? m.op.apply(item) / 3 : m.op.apply(item) % commonDivisor;
        long next = m.test.apply(worry);
        monkeys.get(next).startingItem.add(worry);
        m.inspect++;
      }
      if (s > 0) {
        m.startingItem.subList(0, s).clear();
      }
      round++;
    }
    return monkeys.values().stream()
        .map(m -> m.inspect)
        .sorted((a, b) -> Long.compare(b, a))
        .limit(2)
        .mapToLong(l -> l)
        .reduce((a, b) -> a * b)
        .getAsLong();
  }

  private static class Monkey {
    final int id;
    final List<Long> startingItem;
    final Function<Long, Long> op;
    final Function<Long, Long> test;
    final int divisor;
    long inspect = 0;

    public Monkey(
        int id,
        List<Long> startingItem,
        Function<Long, Long> op,
        Function<Long, Long> test,
        int divisor) {
      this.id = id;
      this.startingItem = new ArrayList<>(startingItem);
      this.op = op;
      this.test = test;
      this.divisor = divisor;
    }
  }
}
