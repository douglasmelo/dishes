CREATE TABLE `order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uuid` varchar(100) NOT NULL,
  `customer_id` bigint(20) NOT NULL,
  `delivery_address` varchar(255) NOT NULL,
  `contact` varchar(100) NOT NULL,
  `store_id` bigint(20) NOT NULL,
  `total` DECIMAL(10, 2) NOT NULL,
  `status` varchar(100) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_order_uuid` (`uuid`),
  KEY `FK_order_customer_id` (`customer_id`),
  INDEX `order_uuid` (`uuid`),
  INDEX `order_store_id` (`store_id`)
) DEFAULT CHARSET=utf8;


CREATE TABLE `order_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) NOT NULL,
  `product_id` bigint(20) NOT NULL,
  `price` DECIMAL(10, 2) NOT NULL,
  `quantity` int(11) NOT NULL,
  `total` DECIMAL(10, 2) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_items_order_id` (`order_id`),
  CONSTRAINT `FK_items_order_id` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`)
) DEFAULT CHARSET=utf8;


CREATE TABLE `customer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(100) NOT NULL,
  `name` varchar(200) NOT NULL,
  `address` varchar(200) NOT NULL,
  `password` varchar(100) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_customer_email` (`email`)
) DEFAULT CHARSET=utf8;

CREATE TABLE `customer_login_event` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `customer_id` bigint(20) NOT NULL,
  `token` varchar(255) NOT NULL,
  `log_out_date` datetime DEFAULT NULL,
  `logged` TINYINT(1) NOT NULL DEFAULT 0,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_customer_login_event_customer_id` (`customer_id`),
  CONSTRAINT `FK_customer_login_event_customer_id` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
) DEFAULT CHARSET=utf8;

CREATE TABLE `product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `store_id` bigint(20) NOT NULL,
  `name` varchar(100) NOT NULL,
  `description` varchar(200) NOT NULL,
  `price` DECIMAL(10, 2) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_product_name` (`name`)
) DEFAULT CHARSET=utf8;


