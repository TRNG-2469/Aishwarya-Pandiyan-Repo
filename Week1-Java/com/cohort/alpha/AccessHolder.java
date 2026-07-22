package com.cohort.alpha;

public class AccessHolder {
    public void publicMethod() {
        System.out.println("Public accessed.");
    }

    protected void protectedMethod() {
        System.out.println("Protected accessed.");
    }

    void defaultMethod() {
        System.out.println("Default (package-private) accessed.");
    }

    private void privateMethod() {
        System.out.println("Private accessed.");
    }

    public void testInternalAccess() {
        publicMethod();
        protectedMethod();
        defaultMethod();
        privateMethod(); // Works - private members are always accessible within their own class
    }
}