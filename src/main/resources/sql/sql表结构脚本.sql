CREATE TABLE `a_stock`
(
    `id`   int NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `code` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  DEFAULT NULL COMMENT '股票代码',
    `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  DEFAULT NULL COMMENT '股票名称',
    `jys`  varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  DEFAULT NULL COMMENT '交易所',
    `gl`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '概念',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_code` (`code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4667 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='a股全量信息';


CREATE TABLE `calendar_data` (
                                 `id` int NOT NULL AUTO_INCREMENT COMMENT '自增id',
                                 `date` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '日期',
                                 `week` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '星期',
                                 `work_day` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '是否工作日（0否，1是）',
                                 PRIMARY KEY (`id`),
                                 UNIQUE KEY `idx_date` (`date`)
) ENGINE=InnoDB AUTO_INCREMENT=732 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='日期表';

CREATE TABLE `price_warning` (
                                 `id` int NOT NULL AUTO_INCREMENT,
                                 `code` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '股票代码',
                                 `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '股票名称',
                                 `time` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '创建时间',
                                 `current` decimal(10,2) DEFAULT NULL COMMENT '当前价格',
                                 `compare_price` decimal(10,2) DEFAULT NULL COMMENT '比较价格',
                                 `price_limit_warning` decimal(10,2) NOT NULL COMMENT '预警价格',
                                 `reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '原因',
                                 `up_or_low` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '0向上预警 1向下预警',
                                 `is_alert` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '0没提示 1提示过',
                                 `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                 `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                                 PRIMARY KEY (`id`),
                                 KEY `idx_isAlert` (`is_alert`),
                                 KEY `idx_code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='价格预警表';

CREATE TABLE `zt_pool` (
                           `id` int NOT NULL AUTO_INCREMENT COMMENT '自增id',
                           `code` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '股票代码',
                           `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '股票名称',
                           `change_ratio` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '涨跌幅',
                           `last_price` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '最新价',
                           `amount` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '成交额',
                           `flow_capital` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '流动市值',
                           `total_capital` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '总市值',
                           `turnover_ratio` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '换手率',
                           `ceiling_amount` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '封板资金',
                           `first_ceiling_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '首次封板时间',
                           `last_ceiling_time` varbinary(255) DEFAULT NULL COMMENT '最后封板时间',
                           `bomb_num` int DEFAULT NULL COMMENT '炸板次数',
                           `lb_num` int DEFAULT NULL COMMENT '连扳次数',
                           `industry` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '所属行业',
                           `time` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '时间',
                           `gl` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '概念',
                           `stock_reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '股票涨停原因',
                           `plate_reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '主题涨停原因',
                           `plate_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '涨停主题',
                           PRIMARY KEY (`id`),
                           KEY `idx_time` (`time`) USING BTREE,
                           KEY `idx_code` (`code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10752 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='涨停板信息';

CREATE TABLE `user_trade_info` (
                                   `id` int NOT NULL AUTO_INCREMENT,
                                   `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '账号',
                                   `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '股票代码',
                                   `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '股票名称',
                                   `date` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '日期',
                                   `code_status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '股票买入状态（1买入，2持有，3卖出，4增仓，5减仓）',
                                   `buy_price` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '买入价格',
                                   `today_close_price` varchar(255) DEFAULT NULL COMMENT '当日收盘价',
                                   `sell_price` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '卖出价格',
                                   `stock_number` varchar(255) DEFAULT NULL COMMENT '买入数量',
                                   `today_profit` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '当日盈亏',
                                   `total_profit` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '总盈亏',
                                   `loss_point` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '止损价格',
                                   `loss_ratio` varchar(255) DEFAULT NULL COMMENT '止损点',
                                   `loss_price` varchar(255) DEFAULT NULL COMMENT '止损金额',
                                   `profit_point` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '建议获利离场价格',
                                   `profit_ratio` varchar(255) DEFAULT NULL COMMENT '建议获利离场点',
                                   `profit_price` varchar(255) DEFAULT NULL COMMENT '建议获利离场金额',
                                   `finish_flag` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '交易完成标识（0未完成，1完成）',
                                   PRIMARY KEY (`id`),
                                   KEY `idx_user_name` (`user_name`),
                                   KEY `idx_code` (`code`),
                                   KEY `idx_code_status` (`code_status`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户交易信息表';


CREATE TABLE `user_summary` (
                                `id` int NOT NULL AUTO_INCREMENT COMMENT '自增id',
                                `user_trade_info_id` int NOT NULL COMMENT '用户交易信息表id',
                                `summary` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '总结',
                                `create_time` datetime DEFAULT NULL,
                                `update_time` datetime DEFAULT NULL,
                                PRIMARY KEY (`id`),
                                UNIQUE KEY `idx_user_trade_info_id` (`user_trade_info_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户交易信息总结表';

