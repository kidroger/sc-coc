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
@Table(name = "t_player_details")
@DynamicInsert
@DynamicUpdate
public class CocPlayerDetails {
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

    @Column(name = "f_attack_Wins")
    private int attackWins;

    @Column(name = "f_defense_Wins")
    private int defenseWins;
    /**
     * CocClan ref
     */
    @Column(name = "f_clan")
    private String clan;

    @Column(name = "f_best_trophies")
    private int bestTrophies;

    @Column(name = "f_war_stars")
    private int warStars;

    @Column(name = "f_town_hall_level")
    private int townHallLevel;

    @Column(name = "f_builder_hall_level")
    private int builderHallLevel;

    @Column(name = "f_best_versus_trophies")
    private int bestVersusTrophies;

    @Column(name = "f_versus_battle_wins")
    private int versusBattleWins;

    /*
    @OneToMany(mappedBy = "playerDetails", fetch = FetchType.LAZY,cascade = { CascadeType.ALL },orphanRemoval = true)
    private Set<CocPlayerAchievement> achievements;

    @OneToMany(fetch = FetchType.LAZY,cascade = { CascadeType.PERSIST })
    @JoinTable(name = "t_player_troops_refs",
            joinColumns={@JoinColumn(name="f_detail_id", referencedColumnName="f_tag")},
            inverseJoinColumns={@JoinColumn(name="f_troop_id", referencedColumnName="f_id")}
    )
    private Set<CocPlayerTroop> troops;

    @OneToMany(fetch = FetchType.LAZY,cascade = { CascadeType.PERSIST })
    @JoinTable(name = "t_player_spells",
            joinColumns={@JoinColumn(name="f_detail_id", referencedColumnName="f_tag")},
            inverseJoinColumns={@JoinColumn(name="f_spell_id", referencedColumnName="f_id")}
    )
    private Set<CocPlayerTroop> spells;

    @OneToMany(fetch = FetchType.LAZY,cascade = { CascadeType.PERSIST })
    @JoinTable(name = "t_player_heroes_refs",
            joinColumns={@JoinColumn(name="f_detail_id", referencedColumnName="f_tag")},
            inverseJoinColumns={@JoinColumn(name="f_hero_id", referencedColumnName="f_id")}
    )
    private Set<CocPlayerTroop> heroes;

    @ManyToOne(fetch = FetchType.LAZY,cascade = { CascadeType.PERSIST })
    @JoinTable(name = "t_player_legend_statistics_refs",
            joinColumns={@JoinColumn(name="f_detail_id", referencedColumnName="f_tag")},
            inverseJoinColumns={@JoinColumn(name="f_legend_statistics_id", referencedColumnName="f_id")}
    )
    private CocPlayerLegendStatistic legendStatistics;
    */
}
