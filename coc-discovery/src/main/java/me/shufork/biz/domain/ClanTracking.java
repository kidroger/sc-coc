package me.shufork.biz.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "t_clan_tracking")
@DynamicInsert
@DynamicUpdate
public class ClanTracking {
    @Setter(AccessLevel.PRIVATE)
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
    @Column(name = "f_clan_tag",length = 24)
    private String clan;

    @Column(name = "f_name",nullable = false,length = 64)
    private String name;

    @Column(name = "f_score")
    private int score;

    @Column(name = "f_last_hit_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastHit;

    public interface ClanTracker{
        String getClan();
        String getName();
    }
}
