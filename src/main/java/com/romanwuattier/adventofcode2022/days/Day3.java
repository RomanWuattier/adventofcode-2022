package com.romanwuattier.adventofcode2022.days;

import com.romanwuattier.adventofcode2022.common.Day;
import com.romanwuattier.adventofcode2022.common.FileOperations;

import java.util.Collection;
import java.util.HashSet;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day3 implements Day {
  public static void main(String[] args) {
    new Day3().wakeup();
  }

  @Override
  public Object[] wakeup(FileOperations fo) {
    Function<Integer, Integer> priority = ascii -> ascii >= 97 ? ascii - 96 : ascii - 38;
    Function<Collection<Integer>, Integer> sumVal = l -> l.stream().mapToInt(priority::apply).sum();

    var lines = fo.readDay(3);
    int p1 = 0;
    for (var l : lines) {
      var fh = l.substring(0, l.length() / 2).chars().boxed().collect(Collectors.toSet());
      var sh = l.substring(l.length() / 2).chars().boxed().collect(Collectors.toSet());
      fh.retainAll(sh);
      p1 += sumVal.apply(fh);
    }

    int p2 = 0, acc = 0;
    var retainSet = new HashSet<Integer>();
    for (var l : lines) {
      var currentSet = l.chars().boxed().collect(Collectors.toSet());
      if (retainSet.isEmpty()) {
        retainSet.addAll(currentSet);
      } else {
        retainSet.retainAll(currentSet);
      }
      if (++acc == 3) {
        p2 += sumVal.apply(retainSet);
        retainSet.clear();
        acc = 0;
      }
    }
    return new Object[] {p1, p2};
  }
}
