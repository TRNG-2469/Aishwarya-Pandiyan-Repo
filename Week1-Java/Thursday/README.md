# Library-Pair-Aishwarya-Surya

Collaborative pair programming exercise building a fully encapsulated,
polymorphic library system in Java.

Repository: https://github.com/apaaandi/Library-Pair-Aishwarya-Surya

## Objective

Collaborate in pairs to build a library inventory coordinator utilizing
SCM branch merges, driver-navigator roles, and structured AI prompt
engineering logs.

## Project Structure

src/
└── com/cohort/library/
├── Borrowable.java
├── Book.java
├── EBook.java
└── LibraryManager.java
prompt-log-library.md

## Classes

**Borrowable**
Interface defining borrowItem() and returnItem().

**Book**
Base class with private title and author fields, a protected isBorrowed
flag, standard getters and setters, and a custom toString() method.

**EBook**
Subclass of Book that implements Borrowable. Adds a fileSizeMB field and
a downloadBook() method that simulates a file download before a borrow
completes. Overrides toString(), borrowItem(), and returnItem().

**LibraryManager**
Runner class containing a Book array of EBook instances. Demonstrates
polymorphic borrow and return behavior using instanceof pattern
matching.

## How to Run

Open the project in IntelliJ, then run LibraryManager.main() to see the
polymorphic borrow and return cycle for all books in the array.

## Collaboration

This exercise followed a driver-navigator pairing model with roles
rotated every 15 minutes. AI prompts used during development, along with
navigator audits of the generated code, are documented in
prompt-log-library.md.

Contributors:
- Aishwarya
- Surya
