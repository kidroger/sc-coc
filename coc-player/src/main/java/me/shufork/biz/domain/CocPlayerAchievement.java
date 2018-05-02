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
@Table(name = "t_player_achievement")
@DynamicUpdate
@DynamicInsert
public class CocPlayerAchievement {
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
    @Column(name = "f_id")
    private String id;

    @Column(name = "f_name",nullable = false)
    private String name;

    @Column(name = "f_stars",nullable = false)
    private int stars;

    @Column(name = "f_value",nullable = false)
    private int value;

    @Column(name = "f_target",nullable = false)
    private int target;

    @Column(name = "f_info")
    private String info;

    @Column(name = "f_completion_info",columnDefinition = "TEXT")
    private String completionInfo;

    @Column(name = "f_village",nullable = false)
    private String village;

    @Column(name = "f_owner",nullable = false)
    private String owner;
    /*
    @ManyToOne
    @JoinColumn(name = "f_player")
    CocPlayerDetails playerDetails;
    */
}
