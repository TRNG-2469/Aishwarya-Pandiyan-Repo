-- create the tables
DROP TABLE IF EXISTS order_items;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS customers;

CREATE TABLE customers (
    customer_id   INT PRIMARY KEY,
    customer_name VARCHAR(100) NOT NULL
);

CREATE TABLE orders (
    order_id      INT PRIMARY KEY,
    customer_id   INT NOT NULL REFERENCES customers(customer_id),
    order_amount  DECIMAL(10,2) NOT NULL,
    order_date    DATE NOT NULL
);

CREATE TABLE products (
    product_id    INT PRIMARY KEY,
    product_name  VARCHAR(100) NOT NULL,
    price         DECIMAL(10,2) NOT NULL
);

CREATE TABLE order_items (
    order_item_id INT PRIMARY KEY,
    order_id      INT NOT NULL REFERENCES orders(order_id),
    product_id    INT NOT NULL REFERENCES products(product_id),
    quantity      INT NOT NULL
);

-- seed data
INSERT INTO customers VALUES
(1, 'Alice Chen'),
(2, 'Bob Marley'),
(3, 'Carol White'),
(4, 'David Kim');

INSERT INTO products VALUES
(101, 'Laptop', 899.99),
(102, 'Mouse', 19.99),
(103, 'USB Hub', 29.99),
(104, 'Monitor', 249.99),
(105, 'Keyboard', 49.99);

INSERT INTO orders VALUES
(1, 1, 150.00, '2026-06-01'),
(2, 1, 200.00, '2026-06-05'),
(3, 2, 20.00,  '2026-06-10'),
(4, 3, 35.00,  '2026-06-15'),
(5, 3, 500.00, '2026-06-20'),
(6, 4, 45.00,  '2026-06-25');

INSERT INTO order_items VALUES
(1, 1, 101, 1),
(2, 1, 102, 2),
(3, 2, 104, 1),
(4, 3, 102, 1),
(5, 4, 103, 1),
(6, 5, 101, 1),
(7, 5, 105, 2),
(8, 6, 103, 3);

-- Query 1: total orders + avg order amount
SELECT
    COUNT(*) AS total_orders,
    ROUND(AVG(order_amount), 2) AS average_order_amount
FROM orders;

-- Query 2: order count per customer
SELECT
    c.customer_id,
    c.customer_name,
    COUNT(o.order_id) AS total_orders
FROM customers c
JOIN orders o ON o.customer_id = c.customer_id
GROUP BY c.customer_id, c.customer_name
ORDER BY total_orders DESC;

-- Query 3: customers who spent more than $150
SELECT
    c.customer_id,
    c.customer_name,
    SUM(o.order_amount) AS total_spent
FROM customers c
JOIN orders o ON o.customer_id = c.customer_id
GROUP BY c.customer_id, c.customer_name
HAVING SUM(o.order_amount) > 150.00
ORDER BY total_spent DESC;

-- Query 4: min/max/avg price + product count
SELECT
    MIN(price) AS lowest_price,
    MAX(price) AS highest_price,
    ROUND(AVG(price), 2) AS average_price,
    COUNT(DISTINCT product_id) AS total_unique_products
FROM products;

-- Query 5: top 3 products by quantity sold
SELECT
    p.product_id,
    p.product_name,
    SUM(oi.quantity) AS total_quantity_sold
FROM products p
JOIN order_items oi ON oi.product_id = p.product_id
GROUP BY p.product_id, p.product_name
ORDER BY total_quantity_sold DESC
LIMIT 3;