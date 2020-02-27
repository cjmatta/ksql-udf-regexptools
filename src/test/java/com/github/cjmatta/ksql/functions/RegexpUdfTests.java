package com.github.cjmatta.ksql.functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RegexpUdfTests {
  @Test
  public void regexpTest() {
    final String source = "Cat Cut Cot";
    final String pattern = "C.t";
    final RegexpUdf regexp = new RegexpUdf();
    final Boolean actualResult = regexp.regexp(source, pattern);
    assertTrue(actualResult);
  }

  @Test
  public void regexpFalseTest() {
    final String source = "Cat Cut Cot";
    final String pattern = "C.x";
    final RegexpUdf regexp = new RegexpUdf();
    final Boolean actualResult = regexp.regexp(source, pattern);
    assertFalse(actualResult);
  }

  @Test
  public void regexpPositionTest() {
    final String source = "Cat Cut Cot";
    final String pattern = "C.t";
    final RegexpUdf regexp = new RegexpUdf();
    final Boolean actualResult = regexp.regexp(source, pattern, 2);
    assertTrue(actualResult);
  }


  @Test
  public void regexpOccurrenceTest() {
    final String source = "Cat Cut Cot";
    final String pattern = "C.t";
    final RegexpUdf regexp = new RegexpUdf();
    final Boolean actualResult = regexp.regexp(source, pattern, 2, 2);
    assertTrue(actualResult);
  }
}
