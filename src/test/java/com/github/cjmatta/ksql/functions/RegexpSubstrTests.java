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
 * Example class that demonstrates how to unit test UDFs.
 */
public class RegexpSubstrTests {
  @Test
  void regexpSubstringBasicTest() {
    final String source = "HelloWorld";
    final String pattern = "World$";
    final RegexpSubstrUdf regexSubstr = new RegexpSubstrUdf();
    final String actualResult = regexSubstr.regexpSubstr(source, pattern);
    assertEquals("World", actualResult, source + " should equal World");
  }

  @Test
  void regexpSubstringNullTest() {
    final String source = "HelloWorld";
    final String pattern = "Borld$";
    final RegexpSubstrUdf regexSubstr = new RegexpSubstrUdf();
    final String actualResult = regexSubstr.regexpSubstr(source, pattern);
    assertNull(actualResult);
  }

  @Test
  void regexpSubstringPositionTest() {
    final String source = "HelloWorldWorld!";
    final String pattern = "World.*";
    final RegexpSubstrUdf regexpSubstr = new RegexpSubstrUdf();
    final String actualResult = regexpSubstr.regexpSubstr(source, pattern, 10);
    assertEquals("World!", actualResult, source + " should equal World!");
  }

  @Test
  void regexpSubstringCaseInsensitiveTest() {
    final String source = "HelloWorldWorld!";
    final String pattern = "world.*";
    final RegexpSubstrUdf regexpSubstr = new RegexpSubstrUdf();
    final String actualResult = regexpSubstr.regexpSubstr(source, pattern,"i");
    assertEquals("WorldWorld!", actualResult, source + " should equal WorldWorld!");
  }


}