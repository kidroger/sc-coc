--liquibase formatted SQL

--changeset cj:1

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for f_league
-- ----------------------------
DROP TABLE IF EXISTS `f_league`;
CREATE TABLE `f_league`  (
  `f_league_id` bigint(20) NOT NULL,
  `z_created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `f_icon_medium` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `f_icon_small` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `f_icon_tiny` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `z_modified_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `f_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `z_version` bigint(20) NULL DEFAULT 0,
  PRIMARY KEY (`f_league_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_hero
-- ----------------------------
DROP TABLE IF EXISTS `t_hero`;
CREATE TABLE `t_hero`  (
  `f_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `z_created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `f_level` int(11) NOT NULL,
  `f_max_level` int(11) NOT NULL,
  `z_modified_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `f_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `z_version` bigint(20) NULL DEFAULT 0,
  `f_village` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`f_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_player
-- ----------------------------
DROP TABLE IF EXISTS `t_player`;
CREATE TABLE `t_player`  (
  `f_tag` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `f_clan_rank` int(11) NULL DEFAULT NULL,
  `z_created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `f_exp_level` int(11) NULL DEFAULT NULL,
  `f_league` bigint(20) NULL DEFAULT NULL,
  `z_modified_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `f_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `f_previous_clan_rank` int(11) NULL DEFAULT NULL,
  `f_role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `f_donations` int(11) NULL DEFAULT NULL,
  `f_donations_received` int(11) NULL DEFAULT NULL,
  `f_trophies` int(11) NULL DEFAULT NULL,
  `z_version` bigint(20) NULL DEFAULT 0,
  `f_versus_trophies` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`f_tag`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_player_achievement
-- ----------------------------
DROP TABLE IF EXISTS `t_player_achievement`;
CREATE TABLE `t_player_achievement`  (
  `f_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `f_completion_info` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `z_created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `f_info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `z_modified_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `f_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `f_owner` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `f_stars` int(11) NOT NULL,
  `f_target` int(11) NOT NULL,
  `f_value` int(11) NOT NULL,
  `z_version` bigint(20) NULL DEFAULT 0,
  `f_village` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`f_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_player_details
-- ----------------------------
DROP TABLE IF EXISTS `t_player_details`;
CREATE TABLE `t_player_details`  (
  `f_tag` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `f_attack_wins` int(11) NULL DEFAULT NULL,
  `f_best_trophies` int(11) NULL DEFAULT NULL,
  `f_best_versus_trophies` int(11) NULL DEFAULT NULL,
  `f_builder_hall_level` int(11) NULL DEFAULT NULL,
  `f_clan` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `z_created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `f_defense_wins` int(11) NULL DEFAULT NULL,
  `z_modified_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `f_town_hall_level` int(11) NULL DEFAULT NULL,
  `z_version` bigint(20) NULL DEFAULT 0,
  `f_versus_battle_wins` int(11) NULL DEFAULT NULL,
  `f_war_stars` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`f_tag`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_player_hero
-- ----------------------------
DROP TABLE IF EXISTS `t_player_hero`;
CREATE TABLE `t_player_hero`  (
  `f_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `z_created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `f_hero_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `z_modified_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `f_player_tag` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `z_version` bigint(20) NULL DEFAULT 0,
  PRIMARY KEY (`f_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_player_legend_statistic
-- ----------------------------
DROP TABLE IF EXISTS `t_player_legend_statistic`;
CREATE TABLE `t_player_legend_statistic`  (
  `f_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `f_best_season_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `f_best_season_rank` int(11) NULL DEFAULT NULL,
  `f_best_season_trophies` int(11) NULL DEFAULT NULL,
  `z_created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `f_current_season_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `f_current_season_rank` int(11) NULL DEFAULT NULL,
  `f_current_season_trophies` int(11) NULL DEFAULT NULL,
  `f_legend_trophies` int(11) NULL DEFAULT NULL,
  `z_modified_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `f_player_tag` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `f_previous_season_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `f_previous_season_rank` int(11) NULL DEFAULT NULL,
  `f_previous_season_trophies` int(11) NULL DEFAULT NULL,
  `z_version` bigint(20) NULL DEFAULT 0,
  PRIMARY KEY (`f_id`) USING BTREE,
  UNIQUE INDEX `UK_i4j31hb4d2fy3w4lcdkd74iti`(`f_player_tag`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_player_spell
-- ----------------------------
DROP TABLE IF EXISTS `t_player_spell`;
CREATE TABLE `t_player_spell`  (
  `f_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `z_created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `z_modified_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `f_player_tag` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `f_spell_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `z_version` bigint(20) NULL DEFAULT 0,
  PRIMARY KEY (`f_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_player_troop
-- ----------------------------
DROP TABLE IF EXISTS `t_player_troop`;
CREATE TABLE `t_player_troop`  (
  `f_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `z_created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `z_modified_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `f_player_tag` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `f_troop_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `z_version` bigint(20) NULL DEFAULT 0,
  PRIMARY KEY (`f_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_season_rank_history
-- ----------------------------
DROP TABLE IF EXISTS `t_season_rank_history`;
CREATE TABLE `t_season_rank_history`  (
  `f_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `z_created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `z_modified_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `f_player_tag` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `f_rank` int(11) NOT NULL,
  `f_season_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `f_trophies` int(11) NOT NULL,
  `z_version` bigint(20) NULL DEFAULT 0,
  PRIMARY KEY (`f_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_spell
-- ----------------------------
DROP TABLE IF EXISTS `t_spell`;
CREATE TABLE `t_spell`  (
  `f_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `z_created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `f_level` int(11) NOT NULL,
  `f_max_level` int(11) NOT NULL,
  `z_modified_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `f_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `z_version` bigint(20) NULL DEFAULT 0,
  `f_village` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`f_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_troop
-- ----------------------------
DROP TABLE IF EXISTS `t_troop`;
CREATE TABLE `t_troop`  (
  `f_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `z_created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `f_level` int(11) NOT NULL,
  `f_max_level` int(11) NOT NULL,
  `z_modified_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `f_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `z_version` bigint(20) NULL DEFAULT 0,
  `f_village` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`f_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

-- t_player
CREATE INDEX idx_top_donations ON t_player ( f_tag, f_donations, f_donations_received, f_name );
CREATE INDEX idx_top_donations_received ON t_player ( f_tag,f_donations_received, f_donations, f_name );
CREATE INDEX idx_top_trophies ON t_player ( f_trophies, f_tag, f_name);
CREATE INDEX idx_top_versus_trophies ON t_player ( f_versus_trophies, f_tag, f_name );

-- t_player_details
CREATE INDEX idx_town_hall_level ON t_player_details ( f_town_hall_level );
CREATE INDEX idx_builder_hall_level ON t_player_details ( f_builder_hall_level);
CREATE INDEX idx_top_attack_wins ON t_player_details ( f_attack_wins, f_tag, f_clan );
CREATE INDEX idx_top_versus_attack_wins ON t_player_details ( f_versus_battle_wins, f_tag, f_clan );
CREATE INDEX idx_top_defense_wins ON t_player_details ( f_defense_wins, f_tag, f_clan );
CREATE INDEX idx_top_war_stars ON t_player_details ( f_war_stars, f_tag, f_clan );
CREATE INDEX idx_top_best_trophies ON t_player_details ( f_best_trophies, f_tag, f_clan );
CREATE INDEX idx_top_best_versus_trophies ON t_player_details ( f_best_versus_trophies, f_tag, f_clan );