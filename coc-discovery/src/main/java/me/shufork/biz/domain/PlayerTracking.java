package me.shufork.biz.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "t_player_tracking")
@DynamicInsert
@DynamicUpdate
public class PlayerTracking {
    @Version
    @Column(name = "z_version")
    private Long version;

    @Column(name = "z_created_time", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdTime;

    @Column(name = "z_modified_time", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedTime;

    @Id
    @Column(name = "f_player_tag")
    private String player;

    @Column(name = "f_name",nullable = false)
    private String name;

    @Column(name = "f_score")
    private int score;

    @Column(name = "f_last_hit_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastHit;

    public interface PlayerTracker{
        String getPlayer();
        String getName();
    }
}
