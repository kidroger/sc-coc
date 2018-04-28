package me.shufork.biz.domain;

import lombok.Data;
import me.shufork.common.enums.CocTagEnums;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "t_clan_tracking")
@DynamicInsert
@DynamicUpdate
public class ClanTracking {
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
    @Column(name = "f_clan_tag")
    private String clan;

    @Column(name = "f_name",nullable = false)
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
