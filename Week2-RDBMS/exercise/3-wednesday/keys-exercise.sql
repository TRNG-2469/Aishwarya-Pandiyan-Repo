DROP TABLE IF EXISTS loans;
DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS members;

-- Task 1: DDL
CREATE TABLE books (
    book_id INT PRIMARY KEY,
    title   VARCHAR(150) NOT NULL,
    author  VARCHAR(100) NOT NULL
);

CREATE TABLE members (
    member_id   INT PRIMARY KEY,
    member_name VARCHAR(100) NOT NULL,
    email       VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE loans (
    member_id INT NOT NULL,
    book_id   INT NOT NULL,
    loan_date DATE NOT NULL,
    PRIMARY KEY (member_id, book_id, loan_date),
    FOREIGN KEY (member_id) REFERENCES members(member_id),
    FOREIGN KEY (book_id) REFERENCES books(book_id)
);

-- Task 2: seed valid parent records
INSERT INTO books (book_id, title, author) VALUES
(101, 'The Hobbit', 'J.R.R. Tolkien'),
(102, '1984', 'George Orwell');

INSERT INTO members (member_id, member_name, email) VALUES
(5001, 'John Doe', 'john@email.com'),
(5002, 'Jane Smith', 'jane@email.com');


-- Test 1: Foreign Key violation
INSERT INTO loans (member_id, book_id, loan_date)
VALUES (9999, 101, '2026-07-12');

-- Test 2: Unique constraint violation
INSERT INTO members VALUES (5003, 'Alex Jones', 'john@email.com');
