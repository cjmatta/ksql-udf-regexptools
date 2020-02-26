package com.github.cjmatta.ksql.functions;

import com.sun.org.apache.xpath.internal.operations.Bool;
import io.confluent.ksql.function.udf.Udf;
import io.confluent.ksql.function.udf.UdfDescription;
import io.confluent.ksql.function.udf.UdfParameter;

@UdfDescription(
  name = "regexp",
  description = "UDF to return boolean if there's a match",
  version = "0.1.0-SNAPSHOT",
  author = "Chris Matta"
)
public class RexexpUdf {
  @UdfDescription(description =  "return boolean for regexp")
  public Boolean regexp(
    @UdfParameter(value = "source string", description = "the source string to match against") String source,
    @UdfParameter(value = "regexp", description = "regular expression pattern") String pattern,
    @UdfParameter(value = "position", description = "position in the string to start the search") int position,
    @UdfParameter(value = "occurrence", description = "which occurrence to match") int occurrence,
    @UdfParameter(value = "[ i | c ]", description = "how to perform matching - i: case insensitive, c: case sensitive") String matchType
  ) {

  }
}
