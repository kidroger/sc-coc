--liquibase formatted SQL

--changeset cj:1

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_war_log
-- ----------------------------
DROP TABLE IF EXISTS `t_war_log`;
CREATE TABLE `t_war_log`  (
  `f_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `f_away_team` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `z_created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `f_end_time` datetime(0) NULL DEFAULT NULL,
  `f_home_team` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `z_modified_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `f_owner` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `f_result` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `f_team_size` int(11) NULL DEFAULT NULL,
  `z_version` bigint(20) NULL DEFAULT 0,
  PRIMARY KEY (`f_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_war_team
-- ----------------------------
DROP TABLE IF EXISTS `t_war_team`;
CREATE TABLE `t_war_team`  (
  `f_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `f_attacks` int(11) NULL DEFAULT NULL,
  `f_badge_large` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `f_badge_medium` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `f_badge_small` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `f_clan` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `f_clan_level` int(11) NULL DEFAULT NULL,
  `z_created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `f_destruction_percentage` double NULL DEFAULT NULL,
  `f_exp_earned` int(11) NULL DEFAULT NULL,
  `z_modified_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `f_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `f_opponent_team` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `f_stars` int(11) NULL DEFAULT NULL,
  `z_version` bigint(20) NULL DEFAULT 0,
  `f_war_time` datetime(0) NULL,
  PRIMARY KEY (`f_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

-- t_war_log
CREATE INDEX idx_qry_home_team ON t_war_log ( f_home_team );
CREATE INDEX idx_qry_away_team ON t_war_log ( f_away_team );
CREATE INDEX idx_qry_teams ON t_war_log ( f_owner,f_end_time,f_home_team, f_away_team );

-- t_war_team
CREATE INDEX idx_qry_opponent ON t_war_team ( f_war_time,f_opponent_team );


--changeset cj:2
-- t_war_log
ALTER TABLE `t_war_log`
CHANGE COLUMN `f_id` `f_id` VARCHAR(64) NOT NULL ,
CHANGE COLUMN `f_away_team` `f_away_team` VARCHAR(64) NOT NULL ,
CHANGE COLUMN `f_home_team` `f_home_team` VARCHAR(64) NOT NULL ,
CHANGE COLUMN `f_owner` `f_owner` VARCHAR(24) NOT NULL ,
CHANGE COLUMN `f_result` `f_result` VARCHAR(24) NULL DEFAULT NULL ;

ALTER TABLE `t_war_log`
ROW_FORMAT = COMPRESSED ;

-- t_war_team
ALTER TABLE `t_war_team`
CHANGE COLUMN `f_id` `f_id` VARCHAR(64) NOT NULL  ,
CHANGE COLUMN `f_name` `f_name` VARCHAR(64) NOT NULL ,
CHANGE COLUMN `f_clan` `f_clan` VARCHAR(24) NOT NULL ,
CHANGE COLUMN `f_opponent_team` `f_opponent_team` VARCHAR(24) NOT NULL ;

ALTER TABLE `t_war_team`
ROW_FORMAT = COMPRESSED ;