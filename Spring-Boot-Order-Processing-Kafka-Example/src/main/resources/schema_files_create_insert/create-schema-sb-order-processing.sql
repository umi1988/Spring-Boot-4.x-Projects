--

CREATE DATABASE IF NOT EXISTS sb_order_processing_schema CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Optional: Create a common application user with access to all three schemas
-- (Replace 'appuser' and 'apppass' with your values)
CREATE USER IF NOT EXISTS 'sb_order_processing_schema'@'%' IDENTIFIED BY 'tiger';
GRANT ALL PRIVILEGES ON sb_multithreading_schema.* TO 'sb_order_processing_schema'@'%';

-- Apply changes
FLUSH PRIVILEGES;