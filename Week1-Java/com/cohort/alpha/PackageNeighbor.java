package com.cohort.alpha;

public class PackageNeighbor {
    public static void main(String[] args) {
        AccessHolder holder = new AccessHolder();

        holder.publicMethod();     // Works - public is accessible everywhere
        holder.protectedMethod();  // Works - protected is accessible within the same package
        holder.defaultMethod();    // Works - default (package-private) is accessible within the same package

        // holder.privateMethod(); // Fails to compile:
        // error: privateMethod() has private access in AccessHolder
        // Private members are only accessible from within the declaring class itself,
        // not from other classes even in the same package.
    }
}
