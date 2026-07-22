# AI Prompt Log: Palindrome Checker

## 1. Initial Attempt
- **Context**: Palindrome validation algorithm.
- **Prompt**: "Write a Java method to check if a string is a palindrome."
- **Generated Code**:
```java
public static boolean isPalindrome(String s) {
    if (s == null) return false;
    int i = 0, j = s.length() - 1;
    while (i < j) {
        while (i < j && !Character.isLetterOrDigit(s.charAt(i))) i++;
        while (i < j && !Character.isLetterOrDigit(s.charAt(j))) j--;
        if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))) return false;
        i++; j--;
    }
    return true;
}
```

## 2. Critique & Evaluation
- **Security Check**: Crashes on null? No — the method explicitly checks `if (s == null) return false;` before doing anything else.
- **Logic Constraints**: Handles casing and punctuation? Yes — it uses `Character.isLetterOrDigit()` to skip over non-alphanumeric characters while scanning inward from both ends, and `Character.toLowerCase()` to make the comparison case-insensitive. It correctly evaluates `"A man, a plan, a canal: Panama"` as a palindrome.
- **Redundancy/Complexity**: Does it use advanced streams? No, it uses a two-pointer approach with nested while loops for skipping invalid characters, which is efficient (single pass, no extra string allocation) but slightly harder to trace for a beginner compared to a simpler "clean first, then compare" approach.

**Note**: Unlike the scenario the exercise assumes, this initial naive prompt already produced a fairly robust, production-ready method with null handling and normalization built in. This is a useful finding in itself: default AI code quality can vary significantly depending on the model and how it interprets an underspecified prompt, so an initial output should still always be audited rather than assumed to be naive or flawed.

## 3. Refined Attempt
- **Revised Prompt**: "Act as a secure Java compiler. Write a public static method named isPalindrome that takes a String. Constraints: 1. If the input is null, return false. 2. Remove all non-alphanumeric characters and ignore character casing. 3. Do not use advanced streams, use simple character iteration."
- **Refined Code**:
```java
public static boolean isPalindrome(String s) {
    if (s == null) return false;
    StringBuilder cleaned = new StringBuilder();
    for (int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);
        if (Character.isLetterOrDigit(c)) {
            cleaned.append(Character.toLowerCase(c));
        }
    }
    int i = 0, j = cleaned.length() - 1;
    while (i < j) {
        if (cleaned.charAt(i) != cleaned.charAt(j)) return false;
        i++; j--;
    }
    return true;
}
```

## 4. Final Evaluation
Both versions correctly handle null input, casing, and punctuation, so the refined prompt did not fix a functional flaw the way the exercise expected. Instead, its main benefit was clarity and structure: by explicitly separating the "clean the string" step (building a normalized `StringBuilder`) from the "compare characters" step (a simple two-pointer loop over the cleaned string), the refined code is easier to read and reason about line by line. The initial version's inline skip-logic inside the while loops is more efficient (single pass, no extra memory for the cleaned string) but requires more careful tracing to verify correctness. The exercise's takeaway for me was less about fixing bugs and more about how prompt specificity affects code structure, readability, and maintainability, even when both outputs are functionally correct. Being explicit about constraints (null handling, character iteration style, no streams) is still valuable for controlling *how* a solution is built, not just *whether* it works.