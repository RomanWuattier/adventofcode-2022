package com.romanwuattier.adventofcode2022.days;

import com.romanwuattier.adventofcode2022.common.Day;
import com.romanwuattier.adventofcode2022.common.FileOperations;

import java.util.LinkedList;

public class Day6 implements Day {
  public static void main(String[] args) {
    new Day6().wakeup();
  }

  @Override
  public Object[] wakeup(FileOperations fo) {
    var chars = fo.readDay(6).get(0).toCharArray();
    return new Object[] {findMarker(chars, 4), findMarker(chars, 14)};
  }

  private int findMarker(char[] chars, int s) {
    var seen = new LinkedList<Character>();
    for (int i = 0; i < chars.length; i++) {
      if (seen.size() >= s) {
        seen.removeFirst();
      }
      seen.addLast(chars[i]);
      if (seen.size() == s && seen.stream().distinct().toArray().length == s) {
        return i + 1;
      }
    }
    throw new IllegalStateException();
  }
}
