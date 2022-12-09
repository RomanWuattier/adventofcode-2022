package com.romanwuattier.adventofcode2022.days;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day9Test {

  @Test
  void test() {
    var o = new Day9().test();
    assertEquals(6030, o[0]);
    assertEquals(2545, o[1]);
  }
}
