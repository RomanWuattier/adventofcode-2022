package com.romanwuattier.adventofcode2022.days;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day13Test {

  @Test
  void test() {
      var o = new Day13().test();
      assertEquals(5588, o[0]);
      assertEquals(23958, o[1]);
  }
}
