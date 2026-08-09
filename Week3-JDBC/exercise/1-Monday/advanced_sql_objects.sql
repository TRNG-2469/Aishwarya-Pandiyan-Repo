-- advanced_sql_objects.sql
-- Exercise: Advanced SQL Objects (Views, Triggers, and Stored Procedures)
-- Target Engine: PostgreSQL

-- =========================================================
-- Part A: Database Schema Setup
-- =========================================================

DROP TABLE IF EXISTS order_items CASCADE;
DROP TABLE IF EXISTS orders CASCADE;
DROP TABLE IF EXISTS customers CASCADE;
DROP TABLE IF EXISTS customer_audit_log CASCADE;

CREATE TABLE customers (
                           customer_id SERIAL PRIMARY KEY,
                           full_name VARCHAR(100) NOT NULL,
                           email VARCHAR(100) NOT NULL,
                           updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE orders (
                        order_id INT PRIMARY KEY,
                        customer_id INT REFERENCES customers(customer_id) ON DELETE CASCADE,
                        order_date DATE NOT NULL,
                        status VARCHAR(20) NOT NULL, -- 'PENDING', 'COMPLETED', 'ARCHIVED'
                        total_amount DECIMAL(10, 2) DEFAULT 0.00
);

CREATE TABLE customer_audit_log (
                                    log_id SERIAL PRIMARY KEY,
                                    customer_id INT,
                                    old_name VARCHAR(100),
                                    new_name VARCHAR(100),
                                    changed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Seed initial customer data
INSERT INTO customers (full_name, email) VALUES
                                             ('John Doe', 'john.doe@example.com'),
                                             ('Jane Smith', 'jane.smith@example.com');

-- Seed initial order data
INSERT INTO orders (order_id, customer_id, order_date, status, total_amount) VALUES
                                                                                 (5001, 1, '2026-07-01', 'PENDING', 250.00),
                                                                                 (5002, 1, '2026-07-10', 'COMPLETED', 120.00),
                                                                                 (5003, 2, '2026-07-12', 'PENDING', 45.00),
                                                                                 (5004, 2, '2026-06-15', 'COMPLETED', 300.00);


-- =========================================================
-- Task 1: Create a View for Active Orders
-- =========================================================

-- This view hides the join logic from users and exposes only
-- pending orders together with basic customer information.
CREATE OR REPLACE VIEW v_active_orders AS
SELECT
    o.order_id,
    o.order_date,
    o.total_amount,
    c.full_name,
    c.email
FROM orders AS o
         JOIN customers AS c
              ON o.customer_id = c.customer_id
WHERE o.status = 'PENDING';

-- Test the view.
SELECT * FROM v_active_orders;


-- =========================================================
-- Task 2: Create a Customer Audit Trigger
-- =========================================================

-- This trigger function records the old and new customer name
-- only when full_name actually changes.
CREATE OR REPLACE FUNCTION fn_log_customer_name_change()
RETURNS TRIGGER
LANGUAGE plpgsql
AS $$
BEGIN
    IF OLD.full_name IS DISTINCT FROM NEW.full_name THEN
        INSERT INTO customer_audit_log (
            customer_id,
            old_name,
            new_name
        )
        VALUES (
            OLD.customer_id,
            OLD.full_name,
            NEW.full_name
        );
END IF;

RETURN NEW;
END;
$$;

-- Bind the trigger function to customers.
DROP TRIGGER IF EXISTS trg_customer_name_change ON customers;

CREATE TRIGGER trg_customer_name_change
    AFTER UPDATE ON customers
    FOR EACH ROW
    EXECUTE FUNCTION fn_log_customer_name_change();

-- Test the trigger by changing John Doe's name.
UPDATE customers
SET
    full_name = 'Johnathan Doe',
    updated_at = CURRENT_TIMESTAMP
WHERE customer_id = 1;

-- Verify that the audit record was created.
SELECT * FROM customer_audit_log;


-- =========================================================
-- Task 3: Create a Stored Procedure to Archive Old Orders
-- =========================================================

-- This procedure archives completed orders whose order_date
-- occurred before the supplied cutoff date.
--
-- PostgreSQL permits transaction control such as COMMIT inside
-- a procedure when CALL is executed as a top-level statement
-- and is not already inside an explicit transaction block.
CREATE OR REPLACE PROCEDURE pr_archive_old_orders(cutoff_date DATE)
LANGUAGE plpgsql
AS $$
BEGIN
UPDATE orders
SET status = 'ARCHIVED'
WHERE status = 'COMPLETED'
  AND order_date < cutoff_date;

COMMIT;
END;
$$;

-- Test the procedure.
-- Run this CALL as a top-level statement, not between BEGIN and COMMIT.
CALL pr_archive_old_orders('2026-07-05');

-- Verify the results:
-- Order 5004 should now be ARCHIVED.
-- Order 5002 should remain COMPLETED.
SELECT
    order_id,
    order_date,
    status,
    total_amount
FROM orders
WHERE order_id IN (5002, 5004)
ORDER BY order_id;