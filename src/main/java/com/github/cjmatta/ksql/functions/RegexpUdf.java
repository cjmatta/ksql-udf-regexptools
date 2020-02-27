package com.github.cjmatta.ksql.functions;

import com.sun.org.apache.xpath.internal.operations.Bool;
import io.confluent.ksql.function.udf.Udf;
import io.confluent.ksql.function.udf.UdfDescription;
import io.confluent.ksql.function.udf.UdfParameter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UdfDescription(
  name = "regexp",
  description = "UDF to return boolean if there's a match",
  version = "0.1.0-SNAPSHOT",
  author = "Chris Matta"
)
public class RegexpUdf {
  @Udf(description =  "return boolean for regexp")
  public Boolean regexp (
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

    if (matchType.equals("i")) {
      matcher = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(source);
    } else {
      matcher = Pattern.compile(pattern).matcher(source);
    }
    if (occurrence == 1) {
      return matcher.find(position);
    } else {
      matcher.find(position);
      while(matcher.find()) {
        occurrenceCount += 1;
        if (occurrenceCount == occurrence) {
          return true;
        }
      }
    }
    return false;
  }

  @Udf(description =  "return boolean for regexp")
  public Boolean regexp (
    @UdfParameter(value = "source string", description = "the source string to match against") String source,
    @UdfParameter(value = "regexp", description = "regular expression pattern") String pattern
  ) {
    return this.regexp(source, pattern, 0, 1, "c");
  }

  @Udf(description =  "return boolean for regexp")
  public Boolean regexp (
    @UdfParameter(value = "source string", description = "the source string to match against") String source,
    @UdfParameter(value = "regexp", description = "regular expression pattern") String pattern,
    @UdfParameter(value = "position", description = "position in the string to start the search") int position
  ) {
    return this.regexp(source, pattern, position, 1, "c");
  }

  @Udf(description =  "return boolean for regexp")
  public Boolean regexp (
    @UdfParameter(value = "source string", description = "the source string to match against") String source,
    @UdfParameter(value = "regexp", description = "regular expression pattern") String pattern,
    @UdfParameter(value = "position", description = "position in the string to start the search") int position,
    @UdfParameter(value = "occurrence", description = "which occurrence to match") int occurrence
  ) {
    return this.regexp(source, pattern, position, occurrence, "c");
  }

  @Udf(description =  "return boolean for regexp")
  public Boolean regexp (
    @UdfParameter(value = "source string", description = "the source string to match against") String source,
    @UdfParameter(value = "regexp", description = "regular expression pattern") String pattern,
    @UdfParameter(value = "[ i | c ]", description = "how to perform matching - i: case insensitive, c: case sensitive") String matchType
  ) {
    return this.regexp(source, pattern, 0, 1, matchType);
  }
}
