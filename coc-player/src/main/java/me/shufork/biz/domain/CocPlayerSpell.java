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
@Table(name = "t_player_spell")
@DynamicUpdate
@DynamicInsert
public class CocPlayerSpell {

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

    @Column(name = "f_player_tag",nullable = false)
    private String player;

    /*@OneToMany(fetch = FetchType.LAZY,cascade = { CascadeType.PERSIST })
    @JoinTable(name = "t_player_spell_refs",
            joinColumns={@JoinColumn(name="f_player_tag", referencedColumnName="f_player_tag")},
            inverseJoinColumns={@JoinColumn(name="f_spell_id", referencedColumnName="f_id")}
    )
    private Set<CocSpell> spells;*/

    @Column(name = "f_spell_id",nullable = false)
    private String spell;
}
