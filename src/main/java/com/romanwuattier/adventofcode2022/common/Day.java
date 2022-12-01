package com.romanwuattier.adventofcode2022.common;

public interface Day extends FileOperations {
  Object[] wakeup(FileOperations fo);

  default void wakeup() {
    var out = wakeup(this);
    System.out.printf("""
      Part 1: %s
      Part 2: %s
      """, out[0], out[1]);
  }

  /** test helper */
  default Object[] test() {
    return wakeup(this);
  }
}
