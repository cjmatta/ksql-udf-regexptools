# KSQL Pattern Matching String UDFs

## Motivation
KSQL has some basic [scalar functions](https://docs.confluent.io/current/ksql/docs/developer-guide/syntax-reference.html#scalar-functions) to handle string manipulation like LCASE, SUBSTR, SLICE, SPLIT etc.. but doesn’t currently have an implementation for capturing the power of regular expressions. 

## Other implementations
* Oracle: [REGEXP_SUBSTR](https://docs.oracle.com/cd/B12037_01/server.101/b10759/functions116.htm), [REGEXP_INSTR](https://docs.oracle.com/cd/B12037_01/server.101/b10759/functions114.htm), and [REGEXP_REPLACE](https://docs.oracle.com/cd/B12037_01/server.101/b10759/functions115.htm)
* MySQL: [has a number of regular expression functions](https://dev.mysql.com/doc/refman/8.0/en/regexp.html)
*  Postgres: [has a number of regular expression functions](https://dev.mysql.com/doc/refman/8.0/en/regexp.html)


These UDFs could support the following implementations (heavily borrowed from MySQL):

`REGEXP`
Return BOOLEAN if the pattern matches
```
REGEXP(
	string - string to match,
	pattern - regular expression pattern,
	[position - position in the string to start the search,
	occurrence - which occurrence to match, default 1st,
	match_type - how to perform matching:
		c: case sensitive,
		i: case insensitive
	]
)

```

`REGEXP_SUBSTR`
Return the substring matching the regular expression
```
REGEXP_SUBSTR(
	string - string to match,
	pattern - regular expression pattern,
	[position - the position in the string to start the search (default 0), 
	occurrence - which occurrence to match (defualt 1st),
	match_type - how to perform matching:
		c: case sensitive,
		i: case insensitive,
	]
)
```

### Future enhancements
`REGEXP_INSTR`
Returns starting index of substring matching the index

`REGEXP_REPLACE`
Replace substrings matching regular expression
