package com.romanwuattier.adventofcode2022.days;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Day6Test {

  @Test
  public void test() {
    var o = new Day6().test();
    assertEquals(1702, o[0]);
    assertEquals(3559, o[1]);
  }
}