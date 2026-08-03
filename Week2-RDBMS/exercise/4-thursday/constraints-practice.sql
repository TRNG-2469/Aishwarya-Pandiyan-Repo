-- Task 1: refactor schema with CHECK, DEFAULT, CASCADE
ALTER TABLE books
ADD COLUMN published_year INT
    CHECK (published_year BETWEEN 1450 AND EXTRACT(YEAR FROM CURRENT_DATE));

ALTER TABLE members
ADD COLUMN membership_status VARCHAR(20) DEFAULT 'ACTIVE'
    CHECK (membership_status IN ('ACTIVE', 'SUSPENDED', 'EXPIRED'));

ALTER TABLE loans DROP CONSTRAINT loans_book_id_fkey;
ALTER TABLE loans DROP CONSTRAINT loans_member_id_fkey;

ALTER TABLE loans
ADD CONSTRAINT loans_book_id_fkey
    FOREIGN KEY (book_id) REFERENCES books(book_id) ON DELETE CASCADE;

ALTER TABLE loans
ADD CONSTRAINT loans_member_id_fkey
    FOREIGN KEY (member_id) REFERENCES members(member_id) ON DELETE RESTRICT;


-- Task 2: verify CHECK and DEFAULT constraints

-- valid insert: book published 2005
INSERT INTO books (book_id, title, author, published_year)
VALUES (103, 'Harry Potter', 'J.K. Rowling', 2005);

-- valid insert: member with no status set — should default to ACTIVE
INSERT INTO members (member_id, member_name, email)
VALUES (5005, 'Mike Ross', 'mike@email.com');

-- check it defaulted correctly
SELECT * FROM members WHERE member_id = 5005;

-- invalid CHECK: book published in 1200, should be rejected
INSERT INTO books (book_id, title, author, published_year)
VALUES (104, 'Ancient Text', 'Unknown', 1200);

-- invalid status: should be rejected
INSERT INTO members (member_id, member_name, email, membership_status)
VALUES (5006, 'Sarah Lee', 'sarah@email.com', 'BLOCKED');


-- Task 3: verify ON DELETE behaviors

-- Test CASCADE: delete a book that has an active loan
-- Book 101 has a loan tied to it (member 5001)
DELETE FROM books WHERE book_id = 101;

-- confirm the loan referencing book 101 was also deleted
SELECT * FROM loans WHERE book_id = 101;

-- ensure member 5002 has an active loan tied to book 102
INSERT INTO members (member_id, member_name, email)
VALUES (5002, 'Jane Smith', 'jane@email.com');

INSERT INTO loans (member_id, book_id, loan_date)
VALUES (5002, 102, '2026-07-02');

-- Test RESTRICT: try to delete a member who has an active loan
-- Member 5002 has a loan tied to book 102
DELETE FROM members WHERE member_id = 5002;