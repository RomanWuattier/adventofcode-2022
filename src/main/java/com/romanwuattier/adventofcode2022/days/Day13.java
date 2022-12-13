package com.romanwuattier.adventofcode2022.days;

import com.google.gson.Gson;
import com.romanwuattier.adventofcode2022.common.Day;
import com.romanwuattier.adventofcode2022.common.FileOperations;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day13 implements Day {
  public static void main(String[] args) {
    new Day13().wakeup();
  }

  @Override
  public Object[] wakeup(FileOperations fo) {
    var gson = new Gson();
    var lines =
        fo.readDay(13).stream()
            .filter(s -> !s.isEmpty())
            .map(l -> gson.fromJson(l, Object.class))
            .toList();

    var groups =
        IntStream.range(0, lines.size())
            .boxed()
            .collect(
                Collectors.groupingBy(
                    i -> i / 2, Collectors.mapping(lines::get, Collectors.toList())));

    var p1 =
        groups.keySet().stream()
            .map(
                k -> {
                  var g = groups.get(k);
                  var left = g.get(0);
                  var right = g.get(1);
                  var b = cmp(left, right);
                  return b != null && b ? k + 1 : -1;
                })
            .filter(i -> i > -1)
            .reduce(Integer::sum)
            .get();

    var dividers = List.of(List.of(List.of(2.)), List.of(List.of(6.)));
    var mutLines =
        Stream.concat(lines.stream(), dividers.stream())
            .sorted(
                (left, right) -> {
                  var b = cmp(left, right);
                  return b == null ? 0 : b ? -1 : 1;
                })
            .toList();

    var p2 =
        IntStream.range(0, mutLines.size())
            .filter(i -> dividers.contains(mutLines.get(i)))
            .reduce((left, right) -> (left + 1) * (right + 1))
            .getAsInt();

    return new Object[] {p1, p2};
  }

  private Boolean cmp(Object left, Object right) {
    if (left instanceof Number l && right instanceof Number r) {
      if ((double) l < (double) r) {
        return true;
      } else if ((double) l > (double) r) {
        return false;
      }
      return null;
    }
    if (left instanceof Number l && right instanceof List<?> r) {
      var o = new ArrayList<>();
      o.add(l);
      return cmp(o, r);
    }
    if (left instanceof List<?> l && right instanceof Number r) {
      var o = new ArrayList<>();
      o.add(r);
      return cmp(l, o);
    }
    var l = (List<Object>) left;
    var r = (List<Object>) right;
    for (int k = 0; k < Math.min(l.size(), r.size()); k++) {
      Boolean res = cmp(l.get(k), r.get(k));
      if (res != null) {
        return res;
      }
    }
    return cmp((double) l.size(), (double) r.size());
  }
}
