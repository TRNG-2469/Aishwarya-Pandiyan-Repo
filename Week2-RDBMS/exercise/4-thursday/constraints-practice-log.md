# CHECK, DEFAULT, and CASCADE Verification Log

## Task 2: CHECK and DEFAULT constraints

### Valid insert
Book published 2005 inserted successfully.
Member inserted without specifying membership_status — column defaulted to 'ACTIVE'.

### CHECK violation (books.published_year)
Statement: INSERT INTO books (...) VALUES (104, 'Ancient Text', 'Unknown', 1200);
Result: FAILED
ERROR: new row for relation "books" violates check constraint "books_published_year_check"
DETAIL: Failing row contains (104, Ancient Text, Unknown, null, 1200).

### CHECK violation (members.membership_status)
Statement: INSERT INTO members (...) VALUES (5006, 'Sarah Lee', 'sarah@email.com', 'BLOCKED');
Result: FAILED
ERROR: new row for relation "members" violates check constraint "members_membership_status_check"
DETAIL: Failing row contains (5006, Sarah Lee, sarah@email.com, BLOCKED).

## Task 3: ON DELETE behaviors

### CASCADE test
Statement: DELETE FROM books WHERE book_id = 101;
Result: SUCCESS — book deleted, and the loan referencing book_id 101 was automatically removed.

### RESTRICT test
Statement: DELETE FROM members WHERE member_id = 5002;
Result: FAILED
ERROR: update or delete on table "members" violates foreign key constraint "loans_member_id_fkey" on table "loans"
DETAIL: Key (member_id)=(5002) is still referenced from table "loans".