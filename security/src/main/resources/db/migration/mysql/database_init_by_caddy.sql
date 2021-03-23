USE `mysql`;

CREATE DATABASE IF NOT EXISTS `spring_security` DEFAULT  CHARACTER SET utf8mb4 COLLATE  utf8mb4_general_ci;

CREATE USER IF NOT EXISTS 'caddy'@'%' IDENTIFIED BY 'caddy@415';
GRANT ALL PRIVILEGES ON `security`.* TO 'caddy'@'%';
-- 当使用flyway时需要查询此表的权限
GRANT SELECT ON `performance_schema`.`user_variables_by_thread` TO 'caddy'@'%';
FLUSH PRIVILEGES;

USE `spring_security`;
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;
