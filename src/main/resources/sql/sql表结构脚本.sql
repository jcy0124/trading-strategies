CREATE TABLE `a_stock`
(
    `id`   int NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `code` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  DEFAULT NULL COMMENT '股票代码',
    `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  DEFAULT NULL COMMENT '股票名称',
    `jys`  varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  DEFAULT NULL COMMENT '交易所',
    `gl`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '概念',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47016 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='a股全量信息';

CREATE TABLE `calendar_data`
(
    `id`       int                                                          NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `date`     varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '日期',
    `week`     varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '星期',
    `work_day` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL COMMENT '是否工作日（0否，1是）',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=732 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='日期表';

CREATE TABLE `zt_pool`
(
    `id`                 int                                                         NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `code`               varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '股票代码',
    `name`               varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  DEFAULT NULL COMMENT '股票名称',
    `change_ratio`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '涨跌幅',
    `last_price`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '最新价',
    `amount`             varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '成交额',
    `flow_capital`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '流动市值',
    `total_capital`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '总市值',
    `turnover_ratio`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '换手率',
    `ceiling_amount`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '封板资金',
    `first_ceiling_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '首次封板时间',
    `last_ceiling_time`  varbinary(255) DEFAULT NULL COMMENT '最后封板时间',
    `bomb_num`           int                                                           DEFAULT NULL COMMENT '炸板次数',
    `lb_num`             int                                                           DEFAULT NULL COMMENT '连扳次数',
    `industry`           varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '所属行业',
    `time`               varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  DEFAULT NULL COMMENT '时间',
    `gl`                 varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '概念',
    `stock_reason`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '股票涨停原因',
    `plate_reason`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '主题涨停原因',
    `plate_name`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '涨停主题',
    PRIMARY KEY (`id`),
    KEY                  `ide_time` (`time`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10180 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='涨停板信息';



























