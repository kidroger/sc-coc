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
@Table(name = "t_war_log")
@DynamicInsert
@DynamicUpdate
public class CocWarLog {
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
    private String id;//tag(home team ) + end time

    @Column(name = "f_home_team",nullable = false)
    private String homeTeam;

    @Column(name = "f_away_team",nullable = false)
    private String awayTeam;

    @Column(name = "f_result")
    private String result;

    @Column(name = "f_end_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;

    @Column(name = "f_team_size")
    private int teamSize;

    @Column(name = "f_owner",nullable = false)
    private String owner;
}
