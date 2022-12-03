package com.romanwuattier.adventofcode2022.days;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day3Test {

  @Test
  void test() {
    var o = new Day3().test();
    assertEquals(7597, o[0]);
    assertEquals(2607, o[1]);
  }
}