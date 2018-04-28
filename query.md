# db_coc_clan
```sql
-- 连胜榜
SELECT t1.f_tag,t2.f_name,t1.f_war_win_streak as 连胜,t1.f_war_wins as 胜局,t1.f_war_ties as 平局,t1.f_war_losses as 败局 from t_clan_details t1 ,t_clan t2 WHERE t1.f_tag = t2.f_tag ORDER BY f_war_win_streak desc LIMIT 10;
-- 胜局榜
SELECT t1.f_tag,t2.f_name,t1.f_war_win_streak as 连胜,t1.f_war_wins as 胜局,t1.f_war_ties as 平局,t1.f_war_losses as 败局 from t_clan_details t1 ,t_clan t2 WHERE t1.f_tag = t2.f_tag ORDER BY f_war_wins desc LIMIT 10;
-- 平局榜
SELECT t1.f_tag,t2.f_name,t1.f_war_win_streak as 连胜,t1.f_war_wins as 胜局,t1.f_war_ties as 平局,t1.f_war_losses as 败局 from t_clan_details t1 ,t_clan t2 WHERE t1.f_tag = t2.f_tag ORDER BY f_war_ties desc LIMIT 10;
-- 败局榜
SELECT  t1.f_tag,t2.f_name,t1.f_war_win_streak as 连胜,t1.f_war_wins as 胜局,t1.f_war_ties as 平局,t1.f_war_losses as 败局 from t_clan_details t1 ,t_clan t2 WHERE t1.f_tag = t2.f_tag ORDER BY f_war_losses desc LIMIT 10;
```

# db_coc_discovery

```sql
SELECT TABLE_NAME,TABLE_ROWS
     FROM INFORMATION_SCHEMA.TABLES 
     WHERE TABLE_SCHEMA IN ('db_coc_discovery','db_coc_clan','db_coc_player')
		 AND TABLE_NAME LIKE 't_%' 
		 ORDER BY TABLE_ROWS DESC;
		 
SELECT 
   SUM(!ISNULL(f_last_hit_time)) AS was_hit,
   SUM(ISNULL(f_last_hit_time)) AS not_hit
FROM t_clan_tracking;

SELECT 
   SUM(!ISNULL(f_last_hit_time)) AS was_hit,
   SUM(ISNULL(f_last_hit_time)) AS not_hit
FROM t_player_tracking;


SELECT f_score,f_clan_tag
    FROM t_clan_tracking
    ORDER BY f_score DESC
    LIMIT 10;

SELECT f_score, count(*)
    FROM t_clan_tracking
    GROUP BY f_score
    ORDER BY count(*) DESC
    LIMIT 10;
```