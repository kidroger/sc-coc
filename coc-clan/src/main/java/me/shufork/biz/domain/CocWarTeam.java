package me.shufork.biz.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "t_war_team")
@DynamicInsert
@DynamicUpdate
public class CocWarTeam {

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
    private String id; //tag+warTime

    @Column(name = "f_opponent_team", nullable = false)
    private String opponent;

    @Column(name = "f_war_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date warTime;

    @Column(name = "f_clan", nullable = false)
    private String clan;

    @Column(name = "f_name")
    private String name;

    @Column(name = "f_badge_small")
    private String badgeSmall;

    @Column(name = "f_badge_medium")
    private String badgeMedium;

    @Column(name = "f_badge_large")
    private String badgeLarge;

    @Column(name = "f_clan_level")
    private int clanLevel;

    @Column(name = "f_attacks")
    private int attacks;

    @Column(name = "f_stars")
    private int stars;

    @Column(name = "f_destruction_percentage")
    private double destructionPercentage;

    @Column(name = "f_exp_earned")
    private int expEarned;
}
