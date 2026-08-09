Scenario 1: Shopping Cart & Session State Manager

Service Recommendation: Amazon DynamoDB

DynamoDB is the best choice because shopping cart and session data is simple and does not require complex relationships or joins. The system needs to handle frequent reads and writes from millions of users while maintaining very low latency. DynamoDB provides fast key-value access and can scale as traffic increases. Its flexible NoSQL structure also works well for temporary data such as carts, sessions, and click activity. This makes it a better fit for this workload than a traditional relational database.

Scenario 2: Regulatory Audit and Ledger System

Service Recommendation: Amazon RDS PostgreSQL

RDS PostgreSQL is the best choice because the system handles structured and highly relational financial data. PostgreSQL supports ACID transactions, which are important when multiple account balances must be updated together. It also supports constraints and joins across customer, billing, and transaction tables. These features help maintain accurate and consistent financial records. Since this is a transactional system with strong data integrity requirements, PostgreSQL is the most appropriate option.

Scenario 3: Recommendation Engine Training Warehouse

Service Recommendation: Amazon Redshift

Amazon Redshift is the best choice because this system analyzes more than 100 TB of historical data. Redshift uses columnar storage, which is efficient for large analytical queries and aggregations. It also uses parallel processing to improve query performance across large datasets. The workload mainly consists of periodic reports and machine learning analysis rather than frequent real-time transactions. This makes Redshift well suited for the system's large-scale analytical workload.