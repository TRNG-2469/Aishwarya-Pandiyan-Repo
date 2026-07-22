package com.cohort.beta;

import com.cohort.alpha.AccessHolder;

public class PackageOutsider {
    public static void main(String[] args) {
        AccessHolder holder = new AccessHolder();

        holder.publicMethod(); // Works - public is accessible from any package

        // holder.protectedMethod(); // Fails to compile:
        // error: protectedMethod() has protected access in AccessHolder
        // Protected members are accessible in the same package or by subclasses
        // (via inheritance) in other packages, but PackageOutsider is neither
        // in the same package nor a subclass of AccessHolder.

        // holder.defaultMethod(); // Fails to compile:
        // error: defaultMethod() is not public in AccessHolder; cannot be accessed
        // from outside package
        // Default (package-private) access restricts visibility to classes
        // within the same package only.

        // holder.privateMethod(); // Fails to compile:
        // error: privateMethod() has private access in AccessHolder
        // Private members are never accessible outside their declaring class,
        // regardless of package.
    }
}