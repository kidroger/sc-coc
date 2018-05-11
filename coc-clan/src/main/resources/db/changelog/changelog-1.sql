--liquibase formatted SQL

--changeset cj:1

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_clan
-- ----------------------------
DROP TABLE IF EXISTS `t_clan`;
CREATE TABLE `t_clan`  (
  `f_tag` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `f_badge_large` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `f_badge_medium` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `f_badge_small` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `f_clan_level` int(11) NOT NULL,
  `z_created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `z_modified_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `f_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `z_version` bigint(20) NULL DEFAULT 0,
  PRIMARY KEY (`f_tag`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_clan_details
-- ----------------------------
DROP TABLE IF EXISTS `t_clan_details`;
CREATE TABLE `t_clan_details`  (
  `f_tag` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `f_clan_points` int(11) NULL DEFAULT NULL,
  `f_clan_versus_points` int(11) NULL DEFAULT NULL,
  `z_created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `f_description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `f_location_id` bigint(20) NULL DEFAULT NULL,
  `z_modified_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `f_required_trophies` int(11) NULL DEFAULT NULL,
  `f_total_members` int(11) NULL DEFAULT NULL,
  `f_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `z_version` bigint(20) NULL DEFAULT 0,
  `f_war_frequency` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `f_war_log_public` bit(1) NULL DEFAULT NULL,
  `f_war_losses` int(11) NULL DEFAULT NULL,
  `f_war_ties` int(11) NULL DEFAULT NULL,
  `f_war_win_streak` int(11) NULL DEFAULT NULL,
  `f_war_wins` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`f_tag`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

-- t_clan
CREATE INDEX idx_qry_name_level ON t_clan ( f_name,f_clan_level );
-- t_clan_details
CREATE INDEX idx_qry_warlog_pub ON t_clan_details ( f_war_log_public );
CREATE INDEX idx_top_win_streak ON t_clan_details ( f_war_win_streak );
CREATE INDEX idx_top_war_wins ON t_clan_details ( f_war_wins );
CREATE INDEX idx_top_war_ties ON t_clan_details ( f_war_ties );
CREATE INDEX idx_top_war_losses ON t_clan_details ( f_war_losses );
