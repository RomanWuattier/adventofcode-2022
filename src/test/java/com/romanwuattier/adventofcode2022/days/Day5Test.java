package com.romanwuattier.adventofcode2022.days;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Day5Test {

  @Test
  public void test() {
    var o = new Day5().test();
    assertEquals("SVFDLGLWV", o[0]);
    assertEquals("DCVTCVPCL", o[1]);
  }
}
