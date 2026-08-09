AWS Storage Service Selection

Part A: The Scenarios

Scenario 1: E-Commerce Transactional Engine (OLTP)

Service Choice: Amazon RDS

Amazon RDS is the best choice because the application has a fixed relational schema and requires strong ACID transaction consistency. Relational databases are designed to maintain relationships between structured tables such as Customers, Orders, and Payments. RDS supports SQL joins, which makes it easy to combine related data across these tables. This makes RDS well suited for transactional OLTP workloads where data integrity and consistency are important.

Scenario 2: Smart-Home IoT Sensor Streams

Service Choice: Amazon DynamoDB

Amazon DynamoDB is the best choice because it is designed for high-throughput, low-latency NoSQL workloads and can scale to handle very large numbers of device writes. Its flexible key-value/document data model allows different IoT devices to store different attributes without requiring one rigid table schema. Device IDs can be used as keys for fast lookups with very low response times. DynamoDB can also scale capacity based on workload, which can be more practical and cost-efficient than maintaining a relational database for this type of rapidly changing IoT workload.

Scenario 3: Quarterly Sales Analytics Reports

Service Choice: Amazon Redshift

Amazon Redshift is the best choice because this workload involves analytical queries across billions of historical records rather than individual operational transactions. Redshift uses column-oriented storage, which is efficient when analytical queries need to scan selected columns across large datasets. It also uses parallel processing to divide complex query workloads across computing resources. Unlike an OLTP database optimized for frequent small transactions, Redshift is designed for OLAP workloads such as aggregations, regional sales analysis, and rolling monthly totals, resulting in better query performance on very large analytical datasets.

Part B: Architectural Reflection

1. Risk of Using RDS for the IoT Scenario

Using RDS for the IoT sensor workload could create both operational and financial problems. Millions of devices sending data every 10 seconds would generate an extremely large number of writes, potentially creating database bottlenecks and requiring increasingly powerful database instances, storage, and scaling resources. A rigid relational schema would also make it more difficult to support devices that send different attributes or whose payload structures change over time. The organization could therefore pay more for database capacity and administration while still receiving worse scalability than a service such as DynamoDB that is designed for high-throughput key-value workloads.

2. Why Amazon S3 Is Object Storage

Amazon S3 is considered object storage because it stores data as objects inside buckets rather than as rows, columns, and relationships in database tables. Each object contains the stored data, metadata, and a unique key used to identify it. S3 does not provide the same transactional relational database functionality, such as primary-key relationships and traditional SQL joins, that services such as RDS provide.

I would choose S3 when the application needs durable and scalable storage for files or large unstructured objects rather than frequent transactional database operations. Examples include application exports, backups, images, videos, log files, documents, static website assets, and data-lake files. S3 is especially useful when large amounts of data need to be stored cost-effectively and accessed by applications or other AWS analytics services.