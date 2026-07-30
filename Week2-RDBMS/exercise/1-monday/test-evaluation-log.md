# String Calculator - AI Test Evaluation Log

## Prompt Used
Act as a Senior Quality Assurance Engineer. Review the following Java utility requirements: A StringCalculator class has three methods: add(String), multiply(String), and divide(String). Each method parses a comma-separated string of integer numbers. Generate a JUnit 5 test class covering at least 5 complex edge cases for these methods (e.g. empty strings, null inputs, negative values, trailing commas, spaces, or division boundaries). Return ONLY the JUnit 5 Java code block. Do not write any conversational text or explanations.

## Evaluation

testAddEmptyString - tests that an empty string returns 0. Invalid originally, my code was throwing an exception instead of returning 0. Remediated by changing add() to return 0 for empty input.

testAddNullInput - tests null input safety. Invalid, wrong exception type. AI assumed NullPointerException, but the actual code throws IllegalArgumentException everywhere. Test corrected to match.

testAddNegativeValues - tests negative number handling. Valid, no change needed.

testAddTrailingComma - tests trailing comma safety, like "1,2,". Invalid originally. Turned out Java's split() silently drops trailing empty tokens by default, so this wasn't actually throwing. Remediated by switching to split(",", -1) so trailing commas now throw as intended.

testAddSpaces - tests whitespace tolerance. Valid, no change needed.

testAddNonNumeric - tests non-numeric token rejection. Invalid, wrong exception type. AI assumed NumberFormatException, but the actual code wraps it in IllegalArgumentException.

testAddConsecutiveCommas - tests an empty token mid-string, like "1,,2". Valid logic, but exception type was wrong in the assertion. Corrected to match implementation.

testDivideByZero - tests the division boundary. Invalid, wrong exception type. AI assumed ArithmeticException, but the actual code catches that internally and throws IllegalArgumentException instead.

testDivideSequentialOrder - tests order-of-operations correctness, like "100,2,10". Valid, no change needed.

testDivideSpaces - tests whitespace plus sequential division, like " 8 , 2 , 1 ". Invalid due to a logic error, not an exception mismatch. AI miscalculated: 8÷2÷1 = 4, not 2. Expected value corrected.

## Remediation Summary
Two new edge cases added to StringCalculator.java that weren't previously handled:
1. Empty string input now returns 0 for add(), instead of throwing an exception.
2. Trailing and consecutive commas are now explicitly rejected via split(",", -1), instead of being silently parsed due to Java's default split() behavior dropping trailing empty tokens.

All 10 corrected JUnit 5 tests compile and pass successfully.