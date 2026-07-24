# String Comparison Log

## Code Differences

Reverse:
My manual version builds a new char array and fills it by indexing backward from length - 1 to 0, then wraps it with new String(result). The AI version calls new StringBuilder(input).reverse().toString(), which hides the index math inside the library. StringBuilder swaps the characters internally without me having to write the loop.

Vowel Counter:
My version uses a manual if chain checking each of the 10 vowel characters directly against charAt(i). The AI version stores "aeiouAEIOU" as a string and calls indexOf(c), and it also uses an enhanced for-loop with toCharArray() instead of manual index tracking. Both are O(n), but mine forces explicit comparisons and index bounds instead of relying on String's internal search.

Palindrome:
My version manually strips spaces and lowercases letters using ASCII math (c + 32 for 'A' to 'Z'), then compares from both ends with two pointers (left and right). The AI version uses replaceAll("\\s+", "") for regex and toLowerCase(), then builds a fully reversed copy with StringBuilder and compares with equals(). The AI approach is more compact but depends on regex and StringBuilder instead of manual character by character logic.

Why the AI code is shorter: it leans on built in library methods (StringBuilder, regex, toCharArray) that internally perform the same loop and index work my manual code does explicitly. Writing it by hand forced me to actually reason about loop bounds (len - 1 - i), off by one risks, and manual char to lowercase conversion, instead of trusting a library method to handle it silently.

## Edge Case Evaluation

null input:
- StringProcessor: reverse returns "", countVowels returns 0, isPalindrome returns false
- AIStringProcessor: same behavior, all three methods have null checks

empty string "":
- StringProcessor: reverse returns "", countVowels returns 0, isPalindrome returns true
- AIStringProcessor: same expected behavior

mixed case ("HeLLo"):
- StringProcessor: handled with manual ASCII shift (c + 32)
- AIStringProcessor: handled with .toLowerCase()

spaces in palindrome check:
- StringProcessor: stripped manually while building the char array
- AIStringProcessor: stripped with regex (\\s+)

Neither version threw an exception on null or empty string. Both had null guards already in place without me having to ask for them.

## Test Output (StringProcessor)