package me.shufork.biz.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "f_league")
@DynamicInsert
@DynamicUpdate
public class CocLeague {

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
    @Column(name = "f_league_id")
    private long leagueId;

    @Column(name = "f_name",nullable = false)
    private String name;

    @Column(name = "f_icon_tiny")
    private String iconTiny;

    @Column(name = "f_icon_small")
    private String iconSmall;

    @Column(name = "f_icon_medium")
    private String iconMedium;


}
