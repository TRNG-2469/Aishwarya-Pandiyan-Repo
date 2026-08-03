-- seed data (adjust or remove if your loans/members already have this data)
INSERT INTO loans (member_id, book_id, loan_date) VALUES
(5001, 102, '2026-06-15'),
(5004, 103, '2026-07-10'),
(5005, 102, '2026-07-15');

INSERT INTO members (member_id, member_name, email)
VALUES (5006, 'Alex Chen', 'alex@email.com');


-- Query 1: All Active Loans (INNER JOIN)
SELECT
    m.member_name,
    b.title AS book_title,
    l.loan_date
FROM loans l
INNER JOIN members m ON m.member_id = l.member_id
INNER JOIN books b ON b.book_id = l.book_id;


-- Query 2: Identifying Inactive Members (LEFT JOIN)
SELECT
    m.member_name,
    l.loan_date
FROM members m
LEFT JOIN loans l ON l.member_id = m.member_id;


-- Query 3: Members with Zero Borrowings
SELECT
    m.member_name,
    l.loan_date
FROM members m
LEFT JOIN loans l ON l.member_id = m.member_id
WHERE l.member_id IS NULL;


-- Query 4: Detailed Book Checkout Catalog (Multi-Table JOIN)
SELECT
    m.member_name,
    b.title AS book_title,
    b.author,
    l.loan_date
FROM members m
INNER JOIN loans l ON l.member_id = m.member_id
INNER JOIN books b ON b.book_id = l.book_id;


-- Query 5: Overdue Borrowing Log
SELECT
    m.member_name,
    m.email,
    b.title AS book_title,
    l.loan_date
FROM loans l
INNER JOIN members m ON m.member_id = l.member_id
INNER JOIN books b ON b.book_id = l.book_id
WHERE l.loan_date < '2026-07-01';