package com.romanwuattier.adventofcode2022.days;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day14Test {

  @Test
  void test() {
      var o = new Day14().test();
      assertEquals(625, o[0]);
      assertEquals(25193, o[1]);
  }
}
