package me.shufork.biz.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;


/**
 * Basic info for player
 */
@Data
@Entity
@Table(name = "t_player")
@DynamicInsert
@DynamicUpdate
public class CocPlayer {

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

    @Column(name = "f_name",nullable = false)
    private String name;

    @Column(name = "f_role")
    private String role;

    @Column(name = "f_exp_level")
    private int expLevel;

    /*@ManyToOne(fetch = FetchType.LAZY,cascade = { CascadeType.PERSIST})
    @JoinTable(name = "t_player_league_refs",
            joinColumns={@JoinColumn(name="f_player_id", referencedColumnName="f_tag")},
            inverseJoinColumns={@JoinColumn(name="f_league_id", referencedColumnName="f_league_id")}
    )
    private CocLeague league;*/
    @Column(name = "f_league")
    private long league;

    @Column(name = "f_trophies")
    private int trophies;

    @Column(name = "f_versus_trophies")
    private int versusTrophies;

    @Column(name = "f_clan_rank")
    private int clanRank;

    @Column(name = "f_previous_clan_rank")
    private int previousClanRank;

    @Column(name = "f_donations")
    private int totalDonations;

    @Column(name = "f_donations_received")
    private int totalDonationsReceived;

    /*@ManyToOne(fetch = FetchType.LAZY,cascade = { CascadeType.PERSIST,CascadeType.MERGE })
    @JoinTable(
            name = "t_player_detail_refs",
            joinColumns={@JoinColumn(name="f_player_id", referencedColumnName="f_tag")},
            inverseJoinColumns={@JoinColumn(name="f_detail_id", referencedColumnName="f_tag")}
    )
    private CocPlayerDetails details;*/
}
