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
@Table(name = "t_clan_details")
@DynamicInsert
@DynamicUpdate
public class CocClanDetails {
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
    @Column(name = "f_tag")
    private String tag;

    @Column(name = "f_type")
    private String type;

    @Column(name = "f_description",columnDefinition = "TEXT")
    private String description;

    @Column(name = "f_location_id")
    private long location;

    @Column(name = "f_clan_points")
    private int clanPoints;

    @Column(name = "f_clan_versus_points")
    private int clanVersusPoints;

    @Column(name = "f_required_trophies")
    private int requiredTrophies;

    @Column(name = "f_war_frequency")
    private String warFrequency;

    @Column(name = "f_war_win_streak")
    private int warWinStreak;

    @Column(name = "f_war_wins")
    private int warWins;

    @Column(name = "f_war_ties")
    private int warTies;

    @Column(name = "f_war_losses")
    private int warLosses;

    @Column(name = "f_war_log_public")
    private boolean warLogPublic;

    @Column(name = "f_total_Members")
    private int totalMembers;
    //List of members
}
