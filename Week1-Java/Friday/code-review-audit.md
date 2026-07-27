# Code Review Audit: MathUtils.java

## AI Critique Points
Reviewed by AI acting as Principal Java Architect. Key findings:

1. **Readability**: Variable and method names are clear and consistent. No comments present, though the logic is simple enough to be self-explanatory.
2. **Performance**: `isPrime` correctly loops only to √n instead of n. `findMax(int[])` and `findMax(double[])` are near-duplicate logic, a minor style concern rather than a performance issue.
3. **Exception Safety**:
    - `factorial(n)` has no guard against negative input; `factorial(-5)` silently returns `1` instead of signaling an error.
    - `findMax` methods print a warning and return `0` on null/empty input. This is unsafe because `0` is indistinguishable from a legitimate result.
    - Utility methods performing `System.out.println` introduces unwanted I/O side effects in a stateless helper class.
4. **Encapsulation**: Not applicable — this is a static utility class by design, so no private state was expected.

## Implementation Decisions

**Accepted:**
- Added an `IllegalArgumentException` guard in `factorial()` for negative input, since silently returning 1 masks a caller error.
- Replaced the sentinel-value pattern (`return 0` + println) in both `findMax` overloads with thrown `IllegalArgumentException`, so invalid input can't be confused with a valid result.

**Rejected:**
- AI suggested extracting a shared generic `findMax` helper to remove duplication between the int/double overloads. Rejected for now to keep scope limited to exception safety fixes; noted as a future refactor.

## Refactored Code Diff
See updated `MathUtils.java`:
- `factorial`: added `if (n < 0) throw new IllegalArgumentException("n must be non-negative");`
- `findMax(int[])` and `findMax(double[])`: replaced println + return 0 with `throw new IllegalArgumentException("array must not be null or empty");`