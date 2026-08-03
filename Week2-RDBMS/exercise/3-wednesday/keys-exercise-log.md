# Constraint Violation Test Log

## Test 1: Foreign Key Violation
Statement:
INSERT INTO loans (member_id, book_id, loan_date)
VALUES (9999, 101, '2026-07-12');

Result: FAILED
ERROR: insert or update on table "loans" violates foreign key constraint "loans_member_id_fkey"
DETAIL: Key (member_id)=(9999) is not present in table "members".

Conclusion: Postgres correctly blocked the orphan loan record because
member_id 9999 has no matching row in members.

## Test 2: Unique Constraint Violation
Statement:
INSERT INTO members VALUES (5003, 'Alex Jones', 'john@email.com');

Result: FAILED
ERROR: duplicate key value violates unique constraint "members_email_key"
DETAIL: Key (email)=(john@email.com) already exists.

Conclusion: Postgres correctly blocked the duplicate email because
the UNIQUE constraint on members.email was enforced.