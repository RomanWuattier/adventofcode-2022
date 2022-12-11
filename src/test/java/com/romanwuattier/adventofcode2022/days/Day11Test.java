package com.romanwuattier.adventofcode2022.days;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day11Test {

  @Test
  void test() {
    var o = new Day11().test();
    assertEquals(88208L, o[0]);
    assertEquals(21115867968L, o[1]);
  }
}
