package com.romanwuattier.adventofcode2022.days;

import com.romanwuattier.adventofcode2022.common.Day;
import com.romanwuattier.adventofcode2022.common.FileOperations;

import java.util.Set;

public class Day10 implements Day {
  public static void main(String[] args) {
    new Day10().wakeup();
  }

  @Override
  public Object[] wakeup(FileOperations fo) {
    var lines = fo.readDay(10);
    int cycle = 0;
    int X = 1;
    int p1 = 0;
    int spriteMiddle = 1;
    char[] crt = new char[40 * 6];
    for (var line : lines) {
      var inst = line.split(" ");
      var cmd = inst[0];
      var time = "addx".equals(cmd) ? 2 : 1;
      while (time-- > 0) {
        cycle++;
        if (Set.of(20, 60, 100, 140, 180, 220).contains(cycle)) {
          p1 += cycle * X;
        }
        if (Set.of(spriteMiddle, spriteMiddle - 1, spriteMiddle + 1).contains((cycle - 1) % 40)) {
          crt[cycle - 1] = '#';
        } else {
          crt[cycle - 1] = '.';
        }
      }
      if ("addx".equals(cmd)) {
        X += Integer.parseInt(inst[1]);
        spriteMiddle += Integer.parseInt(inst[1]);
      }
    }

    var p2 = new StringBuilder();
    for (int i = 0; i < crt.length; i++) {
      if (i % 40 == 0) {
        p2.append("\n");
      }
      p2.append(crt[i]);
    }
    return new Object[] {p1, p2.toString()};
  }
}
