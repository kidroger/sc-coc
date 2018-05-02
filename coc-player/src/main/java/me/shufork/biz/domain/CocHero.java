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
@Table(name = "t_hero")
@DynamicUpdate
@DynamicInsert
public class CocHero {
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
    @Column(name = "f_level",nullable = false)
    private int level;
    @Column(name = "f_max_level",nullable = false)
    private int maxLevel;
    @Column(name = "f_village",nullable = false)
    private String village;
}
