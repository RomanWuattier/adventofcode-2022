package com.romanwuattier.adventofcode2022.days;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day1Test {

  @Test
  public void test() {
    var o = new Day1().test();
    assertEquals(75622, o[0]);
    assertEquals(213159, o[1]);
  }
}
