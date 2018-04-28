package me.shufork.biz.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;


/**
 * Basic info for clan
 */
@Data
@Entity
@Table(name = "t_clan")
@DynamicInsert
@DynamicUpdate
public class CocClan {

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
    @Column(name = "f_tag")
    private String tag;

    @Column(name = "f_name",nullable = false)
    private String name;

    @Column(name = "f_clan_level",nullable = false)
    private int clanLevel;

    @Column(name = "f_badge_small")
    private String badgeSmall;

    @Column(name = "f_badge_medium")
    private String badgeMedium;

    @Column(name = "f_badge_large")
    private String badgeLarge;
}
