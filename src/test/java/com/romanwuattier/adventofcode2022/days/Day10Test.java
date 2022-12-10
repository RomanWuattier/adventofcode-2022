package com.romanwuattier.adventofcode2022.days;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day10Test {

  @Test
  void test() {
    var o = new Day10().test();
    assertEquals(14860, o[0]);
    assertEquals(
        """
            
            ###...##..####.####.#..#.#..#.###..#..#.
            #..#.#..#....#.#....#..#.#..#.#..#.#.#..
            #..#.#......#..###..####.#..#.#..#.##...
            ###..#.##..#...#....#..#.#..#.###..#.#..
            #.#..#..#.#....#....#..#.#..#.#.#..#.#..
            #..#..###.####.####.#..#..##..#..#.#..#.""", o[1]);
  }
}