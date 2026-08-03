-- Task 1: DDL
ALTER TABLE books
ADD COLUMN category VARCHAR(30);

-- Task 2: DML
INSERT INTO members (member_id, member_name, email)
VALUES (5004, 'Maria Lopez', 'maria@email.com');

-- Task 3: DQL
SELECT book_id, title, author, category
FROM books
WHERE author = 'George Orwell';

-- Task 4: TCL
BEGIN;

UPDATE books
SET category = 'Fantasy'
WHERE book_id = 101;

SAVEPOINT insert_check;

UPDATE books
SET category = 'Dystopian'
WHERE book_id = 102;

ROLLBACK TO SAVEPOINT insert_check;

COMMIT;

-- Task 5: DCL
CREATE ROLE guest_reader;
GRANT SELECT ON books TO guest_reader;

SELECT book_id, category FROM books WHERE book_id IN (101, 102);