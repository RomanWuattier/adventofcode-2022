package com.romanwuattier.adventofcode2022.days;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day8Test {

  @Test
  void test() {
    var o = new Day8().test();
    assertEquals(1803, o[0]);
    assertEquals(268912, o[1]);
  }
}
