package me.shufork.biz.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "t_player_legend_statistic")
@DynamicInsert
@DynamicUpdate
public class CocPlayerLegendStatistic {

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
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "f_id")
    private String id;

    @Column(name = "f_legend_trophies")
    private int legendTrophies;

    /*@ManyToOne(fetch = FetchType.LAZY,cascade = { CascadeType.PERSIST ,CascadeType.REFRESH })
    @JoinTable(name = "t_legend_statistics_current_seasons",
            joinColumns={@JoinColumn(name="f_legend_statistics_id", referencedColumnName="f_id")},
            inverseJoinColumns={@JoinColumn(name="f_season_id", referencedColumnName="f_season_id")}
    )
    private CocSeasonRankHistory currentSeason;

    @ManyToOne(fetch = FetchType.LAZY,cascade = { CascadeType.PERSIST ,CascadeType.REFRESH })
    @JoinTable(name = "t_legend_statistics_previous_seasons",
            joinColumns={@JoinColumn(name="f_legend_statistics_id", referencedColumnName="f_id")},
            inverseJoinColumns={@JoinColumn(name="f_season_id", referencedColumnName="f_season_id")}
    )
    private CocSeasonRankHistory previousSeason;

    @ManyToOne(fetch = FetchType.LAZY,cascade = { CascadeType.PERSIST ,CascadeType.REFRESH })
    @JoinTable(name = "t_legend_statistics_best_seasons",
            joinColumns={@JoinColumn(name="f_legend_statistics_id", referencedColumnName="f_id")},
            inverseJoinColumns={@JoinColumn(name="f_season_id", referencedColumnName="f_season_id")}
    )
    private CocSeasonRankHistory bestSeason;
*/

    @Column(name = "f_best_season_id")
    private String bestSeasonId;

    @Column(name = "f_best_season_rank")
    private int bestSeasonRank;

    @Column(name = "f_best_season_trophies")
    private int bestSeasonTrophies;

    @Column(name = "f_previous_season_id")
    private String previousSeasonId;

    @Column(name = "f_previous_season_rank")
    private int previousSeasonRank;

    @Column(name = "f_previous_season_trophies")
    private int previousSeasonTrophies;

    @Column(name = "f_current_season_id")
    private String currentSeasonId;

    @Column(name = "f_current_season_rank")
    private int currentSeasonRank;

    @Column(name = "f_current_season_trophies")
    private int currentSeasonTrophies;

    @Column(name = "f_player_tag",nullable = false,unique = true)
    private String player;
}
