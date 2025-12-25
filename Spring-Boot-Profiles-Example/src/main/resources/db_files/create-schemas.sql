-- 01-create-schemas.sql
-- Create the three schemas (databases) if they don't exist

CREATE DATABASE IF NOT EXISTS local_schema CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE DATABASE IF NOT EXISTS dev_schema CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE DATABASE IF NOT EXISTS prod_schema CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Optional: Create a common application user with access to all three schemas
-- (Replace 'appuser' and 'apppass' with your values)
CREATE USER IF NOT EXISTS 'starttohkaruser'@'%' IDENTIFIED BY 'tiger';
GRANT ALL PRIVILEGES ON local_schema.* TO 'starttohkaruser'@'%';
GRANT ALL PRIVILEGES ON dev_schema.* TO 'starttohkaruser'@'%';
GRANT ALL PRIVILEGES ON prod_schema.* TO 'starttohkaruser'@'%';

-- Apply changes
FLUSH PRIVILEGES;