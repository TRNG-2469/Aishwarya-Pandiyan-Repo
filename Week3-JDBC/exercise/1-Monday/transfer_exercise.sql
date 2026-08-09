-- transfer_exercise.sql
-- Exercise: SQL Transactions and Atomicity
-- Target Engine: PostgreSQL

-- Part A: Setup and Seed Data

DROP TABLE IF EXISTS bank_accounts CASCADE;

CREATE TABLE bank_accounts (
                               account_id INT PRIMARY KEY,
                               owner_name VARCHAR(100) NOT NULL,
                               balance DECIMAL(12, 2) NOT NULL,
                               CONSTRAINT chk_positive_balance CHECK (balance >= 0.00)
);

INSERT INTO bank_accounts (account_id, owner_name, balance) VALUES
                                                                (101, 'David Miller', 500.00),
                                                                (102, 'Emily Davis', 150.00);


-- Task 1: Basic Successful Transfer
-- Transfer $100.00 from David Miller (101) to Emily Davis (102)

-- Verify balances before the transfer.
SELECT account_id, owner_name, balance
FROM bank_accounts
WHERE account_id IN (101, 102)
ORDER BY account_id;

BEGIN;

-- Deduct $100.00 from David's account.
UPDATE bank_accounts
SET balance = balance - 100.00
WHERE account_id = 101;

-- Add $100.00 to Emily's account.
UPDATE bank_accounts
SET balance = balance + 100.00
WHERE account_id = 102;

-- Save both updates as one completed transaction.
COMMIT;

-- Verify balances after the successful transfer.
-- Expected:
-- David Miller = $400.00
-- Emily Davis  = $250.00
SELECT account_id, owner_name, balance
FROM bank_accounts
WHERE account_id IN (101, 102)
ORDER BY account_id;

-- Task 2: Failed Transfer Due to Insufficient Funds
-- Attempt to transfer $600.00 from David (101) to Emily (102)

BEGIN;

-- This update attempts to reduce David's balance from
-- $400.00 to -$200.00.
-- PostgreSQL rejects it because chk_positive_balance
-- requires balance >= 0.00.
UPDATE bank_accounts
SET balance = balance - 600.00
WHERE account_id = 101;

-- Because the previous statement fails, PostgreSQL marks
-- the current transaction as aborted.
-- The following credit should NOT be applied successfully
-- while the transaction is in the failed state.
UPDATE bank_accounts
SET balance = balance + 600.00
WHERE account_id = 102;

-- Discard the entire failed transaction.
ROLLBACK;

-- Verify that both balances are unchanged from Task 1.
-- Expected:
-- David Miller = $400.00
-- Emily Davis  = $250.00
SELECT account_id, owner_name, balance
FROM bank_accounts
WHERE account_id IN (101, 102)
ORDER BY account_id;

-- Task 3: Savepoint Checkpoints
-- Create David's Vault, attempt a transfer, then undo only
-- the transfer while keeping the newly created vault account.

BEGIN;

-- Step 1: Create the new vault account.
INSERT INTO bank_accounts (account_id, owner_name, balance)
VALUES (103, 'David Vault', 0.00);

-- Step 2: Create a checkpoint after the vault exists.
SAVEPOINT vault_created;

-- Step 3: Attempt to transfer $300.00 from David's main
-- account into the vault.
UPDATE bank_accounts
SET balance = balance - 300.00
WHERE account_id = 101;

UPDATE bank_accounts
SET balance = balance + 300.00
WHERE account_id = 103;

-- Step 4: David decides not to complete the transfer.
-- Roll back only the work performed after the savepoint.
ROLLBACK TO SAVEPOINT vault_created;

-- Step 5: Commit the transaction.
-- The vault creation remains, but the $300 transfer is undone.
COMMIT;

-- Step 6: Verify the final balances.
-- Expected:
-- David Miller (101) = $400.00
-- David Vault  (103) = $0.00
SELECT account_id, owner_name, balance
FROM bank_accounts
WHERE account_id IN (101, 103)
ORDER BY account_id;