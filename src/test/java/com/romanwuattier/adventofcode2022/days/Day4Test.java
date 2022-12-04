package com.romanwuattier.adventofcode2022.days;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day4Test {

  @Test
  void test() {
    var o = new Day4().test();
    assertEquals(503, o[0]);
    assertEquals(827, o[1]);
  }
}