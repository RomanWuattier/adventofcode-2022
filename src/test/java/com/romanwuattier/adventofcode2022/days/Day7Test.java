package com.romanwuattier.adventofcode2022.days;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Day7Test {

  @Test
  void test() {
    var o = new Day7().test();
    assertEquals(1749646, o[0]);
    assertEquals(1498966, o[1]);
  }
}
