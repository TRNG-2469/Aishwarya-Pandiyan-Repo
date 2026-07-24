# AI Assisted Debugging Log

## Bug 1: Null Roster Crash

Stack trace:

    Student: Alice
    Exception in thread "main" java.lang.NullPointerException: Cannot invoke "String.startsWith(String)" because "studentNames[i]" is null
        at com.cohort.RosterApp.main(RosterApp.java:10)

Root cause: the array has 5 slots but only indexes 0 and 1 are assigned. Indexes 2, 3, and 4 default to null, so calling .startsWith() on them throws a NullPointerException.

Fix: added a null check before calling startsWith(), so the loop skips unassigned slots instead of crashing. This validates the data before using it rather than catching the exception after the fact.

Confirmed output after fix:

    Student: Alice
    Process finished with exit code 0

## Bug 2: Incompatible Vehicle Downcast

Stack trace:

    Exception in thread "main" java.lang.ClassCastException: class com.cohort.Airplane2 cannot be cast to class com.cohort.Car2 (com.cohort.Airplane2 and com.cohort.Car2 are in unnamed module of loader 'app')
        at com.cohort.AirportApp.main(AirportApp.java:11)

Root cause: myVehicle actually holds an Airplane2 object at runtime, not a Car2. Casting to Car2 fails because Airplane2 and Car2 are sibling classes, not a parent-child relationship.

Fix: added an instanceof check before casting, so the cast only happens when it is actually safe. This validates the object's real type before the operation instead of catching the ClassCastException afterward.

Confirmed output after fix:

    Cannot downcast: myVehicle is not a Car2.
    Process finished with exit code 0

## Bug 3: Arithmetic Division Trap

Stack trace:

    Processing report...
    Exception in thread "main" java.lang.ArithmeticException: / by zero
        at com.cohort.FinancialApp.computeScore(FinancialApp.java:5)
        at com.cohort.FinancialApp.main(FinancialApp.java:10)

Root cause: divisor is passed in as 0, and integer division by zero throws an ArithmeticException in Java.

Fix: added a check for divisor == 0 before performing the division, returning a safe default value and printing a message instead of crashing. Also cast totalScore to double before dividing, since the original code was doing integer division even when the divisor was not zero, which would have silently truncated results.

Confirmed output after fix:

    Processing report...
    Invalid divisor: cannot divide by zero. Returning 0.0.
    Calculated Value: 0.0
    Process finished with exit code 0

## Summary

All three fixes validate input before performing the risky operation (null check, instanceof check, zero check), rather than wrapping the operation in a try-catch block. This matches the root cause vs band-aid goal of the exercise: the program never reaches the exception throwing state in the first place. All three programs now run and terminate with exit code 0.