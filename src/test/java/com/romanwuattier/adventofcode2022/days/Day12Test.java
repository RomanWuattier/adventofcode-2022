package com.romanwuattier.adventofcode2022.days;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day12Test {

  @Test
  void test() {
      var o = new Day12().test();
      assertEquals(380, o[0]);
      assertEquals(375, o[1]);
  }
}
