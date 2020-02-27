/*
 * Copyright 2019 Confluent Inc.
 *
 * Licensed under the Confluent Community License (the "License"); you may not use
 * this file except in compliance with the License.  You may obtain a copy of the
 * License at
 *
 * http://www.confluent.io/confluent-community-license
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OF ANY KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.github.cjmatta.ksql.functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Test all permutations of the RegexpSubstr class
 */
public class RegexpSubstrTests {
  @Test
  void regexpSubstringBasicTest() {
    final String source = "Cat Cut Cot";
    final String pattern = "C.t";
    final RegexpSubstrUdf regexSubstr = new RegexpSubstrUdf();
    final String actualResult = regexSubstr.regexpSubstr(source, pattern);
    assertEquals("Cat", actualResult);
  }

  @Test
  void regexpSubstringNullTest() {
    final String source = "Cat Cut Cot";
    final String pattern = "C.x";
    final RegexpSubstrUdf regexSubstr = new RegexpSubstrUdf();
    final String actualResult = regexSubstr.regexpSubstr(source, pattern);
    assertNull(actualResult);
  }

  @Test
  void regexpSubstringPositionTest() {
    final String source = "Cat Cut Cot";
    final String pattern = "C.t";
    final RegexpSubstrUdf regexpSubstr = new RegexpSubstrUdf();
    final String actualResult = regexpSubstr.regexpSubstr(source, pattern, 2);
    assertEquals("Cut", actualResult);
  }

  @Test
  void regexpSubstringOccurrenceTest() {
    final String source = "Cat Cut Cot";
    final String pattern = "C.t";
    final RegexpSubstrUdf regexpSubstr = new RegexpSubstrUdf();
    final String actualResult = regexpSubstr.regexpSubstr(source, pattern, 2, 2);
    assertEquals("Cot", actualResult);
  }

  @Test
  void regexpSubstringCaseInsensitiveTest() {
    final String source = "Cat Cut Cot";
    final String pattern = "c.t";
    final RegexpSubstrUdf regexpSubstr = new RegexpSubstrUdf();
    final String actualResult = regexpSubstr.regexpSubstr(source, pattern,"i");
    assertEquals("Cat", actualResult);
  }


}