package com.github.cjmatta.ksql.functions;

import io.confluent.ksql.function.udf.Udf;
import io.confluent.ksql.function.udf.UdfDescription;
import io.confluent.ksql.function.udf.UdfParameter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UdfDescription(
  name = "regexp_substr",
  description = "UDF to extract a substring from a regular expression",
  version = "0.1.0-SNAPSHOT",
  author = "Chris Matta"
)
public class RegexpSubstrUdf {
  @Udf(description = "extract substring from string using regexp")
  public String regexpSubstr(
    @UdfParameter(value = "source string", description = "the source string to match against") String source,
    @UdfParameter(value = "regexp", description = "regular expression pattern") String pattern,
    @UdfParameter(value = "position", description = "position in the string to start the search") int position,
    @UdfParameter(value = "occurrence", description = "which occurrence to match") int occurrence,
    @UdfParameter(value = "[ i | c ]", description = "how to perform matching - i: case insensitive, c: case sensitive") String matchType
    ) {
    int occurrenceCount = 1;

    if (matchType.length() > 1) {
      matchType = matchType.substring(matchType.length() - 1);
    }
    Matcher matcher = null;
    try {
      if (matchType.equals("i")) {
        matcher = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(source);
      } else {
        matcher = Pattern.compile(pattern).matcher(source);
      }
      matcher.find(position);

//      If we want to match the first occurrence return immediately
      if (occurrence == 1) {
        return matcher.group();
//      Else keep looping until we match the correct occurrence
      } else {
        while(matcher.find()) {
          occurrenceCount += 1;
          if (occurrenceCount == occurrence) {
            return matcher.group();
          }
        }
      }
    } catch (IllegalStateException e) {
      return null;
    }
    return null;
  }

  @Udf(description = "extract substring from string using regexp")
  public String regexpSubstr(
    @UdfParameter(value = "source string", description = "the source to match against") String source,
    @UdfParameter(value = "regexp", description = "regular expression") String pattern
  ){
    return regexpSubstr(source, pattern, 0, 1, "c");
  }

  @Udf(description = "extract substring from string using regexp")
  public String regexpSubstr(
    @UdfParameter(value = "source string", description = "the source to match against") String source,
    @UdfParameter(value = "regexp", description = "regular expression") String pattern,
    @UdfParameter(value = "position", description = "position in the string to start the search") int position
  ){
    return regexpSubstr(source, pattern, position, 1, "c");
  }

  @Udf(description = "extract substring from string using regexp")
  public String regexpSubstr(
    @UdfParameter(value = "source string", description = "the source to match against") String source,
    @UdfParameter(value = "regexp", description = "regular expression") String pattern,
    @UdfParameter(value = "position", description = "position in the string to start the search") int position,
    @UdfParameter(value = "occurrence", description = "which occurrence to match") int occurrence
  ){
    return regexpSubstr(source, pattern, position, occurrence, "c");
  }


  @Udf(description = "extract substring from string using regexp")
  public String regexpSubstr(
    @UdfParameter(value = "source string", description = "the source to match against") String source,
    @UdfParameter(value = "regexp", description = "regular expression") String pattern,
    @UdfParameter(value = "[ i | c ]", description = "how to perform matching - i: case insensitive, c: case sensitive") String matchType

  ){
    return regexpSubstr(source, pattern, 0, 1, matchType);
  }




}
