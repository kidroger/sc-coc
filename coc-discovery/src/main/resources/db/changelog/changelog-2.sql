--liquibase formatted SQL
--changeset cj:2

-- t_clan_tracking
ALTER TABLE t_clan_tracking DROP INDEX idx_top_last_hit_score;
CREATE INDEX idx_top_last_hit_score ON t_clan_tracking ( f_last_hit_time, f_score, z_version,f_clan_tag, f_name );
-- t_player_tracking
ALTER TABLE t_player_tracking DROP INDEX idx_top_last_hit_score;
CREATE INDEX idx_top_last_hit_score ON t_player_tracking ( f_last_hit_time, f_score, z_version,f_player_tag, f_name );

--changeset cj:3

-- t_clan_tracking
ALTER TABLE `t_clan_tracking`
CHANGE COLUMN `f_clan_tag` `f_clan_tag` VARCHAR(24) NOT NULL ,
CHANGE COLUMN `f_name` `f_name` VARCHAR(64) NOT NULL ;

UPDATE `t_clan_tracking`
SET
    `f_last_hit_time` = '1970-01-01 00:00:00'
WHERE
    `f_last_hit_time` IS NULL;

ALTER TABLE `t_clan_tracking`
CHANGE COLUMN `f_last_hit_time` `f_last_hit_time` DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00' ;

-- t_player_tracking
ALTER TABLE `t_player_tracking`
CHANGE COLUMN `f_player_tag` `f_player_tag` VARCHAR(24) NOT NULL ,
CHANGE COLUMN `f_name` `f_name` VARCHAR(64) NOT NULL ;

UPDATE `t_player_tracking`
SET
    `f_last_hit_time` = '1970-01-01 00:00:00'
WHERE
    `f_last_hit_time` IS NULL;

ALTER TABLE `t_player_tracking`
CHANGE COLUMN `f_last_hit_time` `f_last_hit_time` DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00' ;


--changeset cj:4
--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:0 SELECT count(*) FROM information_schema.partitions WHERE TABLE_SCHEMA=DATABASE() AND TABLE_NAME = 't_clan_tracking' AND PARTITION_NAME IS NOT NULL
ALTER TABLE t_clan_tracking PARTITION BY KEY ( f_clan_tag ) PARTITIONS 15;

--changeset cj:5
--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:0 SELECT count(*) FROM information_schema.partitions WHERE TABLE_SCHEMA=DATABASE() AND TABLE_NAME = 't_player_tracking' AND PARTITION_NAME IS NOT NULL
ALTER TABLE t_player_tracking PARTITION BY KEY ( f_player_tag ) PARTITIONS 15;