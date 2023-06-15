-- migrate:up
CREATE TABLE if not exists `user`
(
    `id`                        bigint PRIMARY KEY AUTO_INCREMENT,
    `created_at`                datetime(6) NOT NULL,
    `updated_at`                datetime(6) DEFAULT NULL,
    `firstname`                 varchar(50) NOT NULL,
    `lastname`                  varchar(50) DEFAULT NULL,
    `email`                     varchar(100) NOT NULL,
    `phone`                   varchar(13)  DEFAULT NULL,
    `user_token`              varchar(50) NOT NULL DEFAULT ''
    );
CREATE TABLE if not exists `user_address` (
                                `id` int unsigned NOT NULL AUTO_INCREMENT,
                                `user_id` bigint NOT NULL,
                                `apartment_name` varchar(50)  NOT NULL DEFAULT '',
                                `street` varchar(50)  NOT NULL DEFAULT '',
                                `state` varchar(50)  NOT NULL DEFAULT '',
                                `pincode` varchar(30)  NOT NULL DEFAULT '',
                                `country` varchar(50)  NOT NULL DEFAULT '',
                                `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                PRIMARY KEY (`id`),
    KEY `user_address_fk_user_id` (`user_id`),
    CONSTRAINT `user_address_fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE if not exists `product` (
                           `id` bigint unsigned NOT NULL AUTO_INCREMENT,
                           `product_name` varchar(50)  NOT NULL DEFAULT '',
                           `inventory` int NOT NULL,
                           `address` varchar(150)  NOT NULL DEFAULT '',
                           `pincode` varchar(30)  NOT NULL DEFAULT '',
                           `country` varchar(50)  NOT NULL DEFAULT '',
                           `state` varchar(50)  NOT NULL DEFAULT '',
                           `price` float(10,2) DEFAULT NULL,
                           `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                           `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                           `product_code` varchar(20)  NOT NULL DEFAULT '',
                            PRIMARY KEY (`id`)
);

CREATE TABLE if not exists `pincode_service` (
                                   `id` int unsigned NOT NULL AUTO_INCREMENT,
                                   `source_pincode` varchar(30) NOT NULL DEFAULT '',
                                   `destination_pincode` varchar(30)  NOT NULL DEFAULT '',
                                   `online_service` tinyint(1) NOT NULL,
                                   `cod` tinyint(1) NOT NULL,
                                   `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                   `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                   PRIMARY KEY (`id`)
);

CREATE TABLE if not exists `orders` (
                          `id` int unsigned NOT NULL AUTO_INCREMENT,
                          `order_id` varchar(50)  NOT NULL DEFAULT '',
                          `product_id` bigint unsigned NOT NULL,
                          `user_id` bigint unsigned NOT NULL,
                          `quantity` int NOT NULL,
                          `payment_mode` varchar(30)  NOT NULL DEFAULT '',
                          `order_status` varchar(30)  NOT NULL DEFAULT '',
                          `amount` float(10,2) NOT NULL,
  `is_paid` tinyint(1) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `order_id` (`order_id`),
  KEY `orders_fk_product_id` (`product_id`),
  KEY `orders_fk_user_id` (`user_id`),
  CONSTRAINT `orders_fk_product_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `orders_fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE if not exists `payments` (
                            `id` int unsigned NOT NULL AUTO_INCREMENT,
                            `order_id` varchar(50) NOT NULL DEFAULT '',
                            `transaction_id` varchar(30) NOT NULL DEFAULT '',
                            `response` json NOT NULL,
                            `payment_gateway` varchar(30)  NOT NULL DEFAULT '',
                            `status` varchar(30)  NOT NULL DEFAULT '',
                            `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                            `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                            PRIMARY KEY (`id`),
                            KEY `order_id` (`order_id`),
                            CONSTRAINT `payments_fk_order_id` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`) ON DELETE RESTRICT ON UPDATE CASCADE
);



-- migrate:down

