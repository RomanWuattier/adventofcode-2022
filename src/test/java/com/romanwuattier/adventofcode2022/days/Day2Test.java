package com.romanwuattier.adventofcode2022.days;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day2Test {

  @Test
  public void test() {
    var o = new Day2().test();
    assertEquals(13009, o[0]);
    assertEquals(10398, o[1]);
  }
}
