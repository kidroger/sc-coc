--liquibase formatted SQL
--changeset cj:2

-- t_clan_tracking
ALTER TABLE t_clan_tracking DROP INDEX idx_top_last_hit_score;
CREATE INDEX idx_top_last_hit_score ON t_clan_tracking ( f_last_hit_time, f_score, z_version,f_clan_tag, f_name );
-- t_player_tracking
ALTER TABLE t_player_tracking DROP INDEX idx_top_last_hit_score;
CREATE INDEX idx_top_last_hit_score ON t_player_tracking ( f_last_hit_time, f_score, z_version,f_player_tag, f_name );
