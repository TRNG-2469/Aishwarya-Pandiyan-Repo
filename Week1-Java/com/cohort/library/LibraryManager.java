package com.cohort.library;

public class LibraryManager {
    public static void main(String[] args) {
        Book[] books = new Book[3];
        books[0] = new EBook("Effective Java", "Joshua Bloch", 4.2);
        books[1] = new EBook("Clean Code", "Robert Martin", 3.8);
        books[2] = new EBook("Java Concurrency in Practice", "Brian Goetz", 5.1);

        for (Book book : books) {
            System.out.println(book);
            if (book instanceof Borrowable borrowable) {
                borrowable.borrowItem();
            }
        }

        System.out.println();

        for (Book book : books) {
            if (book instanceof Borrowable borrowable) {
                borrowable.returnItem();
            }
        }
    }
}