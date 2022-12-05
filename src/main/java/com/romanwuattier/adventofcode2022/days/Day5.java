package com.romanwuattier.adventofcode2022.days;

import com.romanwuattier.adventofcode2022.common.Day;
import com.romanwuattier.adventofcode2022.common.FileOperations;

import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day5 implements Day {
  public static void main(String[] args) {
    new Day5().wakeup();
  }

  @Override
  public Object[] wakeup(FileOperations fo) {
    var lines = fo.readDay(5);
    var crates = IntStream.range(0, 9).mapToObj(i -> new LinkedList<String>()).toList();
    var crates2 = IntStream.range(0, 9).mapToObj(i -> new LinkedList<String>()).toList();

    for (int row = 0; row < 8; row++) {
      var l = lines.get(row);
      for (int i = 0; i < 9; i++) {
        var begin = i * 3 + i + 1;
        var end = i * 3 + i + 2;
        if (begin >= l.length() || end >= l.length()) {
          break;
        }
        var c = l.substring(begin, end);
        if (!c.equals(" ")) {
          crates.get(i).add(c);
          crates2.get(i).add(c);
        }
      }
    }

    lines.stream()
        .skip(10)
        .map(l -> l.split(" "))
        .forEach(
            inst -> {
              int n = Integer.parseInt(inst[1]);
              int from = Integer.parseInt(inst[3]) - 1;
              int to = Integer.parseInt(inst[5]) - 1;
              var toMove = new LinkedList<String>();
              for (int i = 0; i < n; i++) {
                toMove.add(crates2.get(from).poll());
              }
              for (int i = 0; i < n; i++) {
                crates.get(to).addFirst(crates.get(from).poll());
                crates2.get(to).addFirst(toMove.removeLast());
              }
            });

    return new Object[] {
      crates.stream().map(LinkedList::peek).collect(Collectors.joining("")),
      crates2.stream().map(LinkedList::peek).collect(Collectors.joining(""))
    };
  }
}
