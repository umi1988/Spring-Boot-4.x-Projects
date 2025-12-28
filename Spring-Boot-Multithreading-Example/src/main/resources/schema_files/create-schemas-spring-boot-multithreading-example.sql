-- 01-create-schemas.sql
-- Create the three schemas (databases) if they don't exist


CREATE DATABASE IF NOT EXISTS sb_multithreading_schema CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Optional: Create a common application user with access to all three schemas
-- (Replace 'appuser' and 'apppass' with your values)
CREATE USER IF NOT EXISTS 'starttohkar_sb_multithreading'@'%' IDENTIFIED BY 'tiger';
GRANT ALL PRIVILEGES ON sb_multithreading_schema.* TO 'starttohkar_sb_multithreading'@'%';

-- Apply changes
FLUSH PRIVILEGES;