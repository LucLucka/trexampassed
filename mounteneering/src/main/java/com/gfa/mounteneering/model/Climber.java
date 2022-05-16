package com.gfa.mounteneering.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Climber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String nationality;

    public Climber(String name, String nationality, Mountain mountain, Integer altitude, Boolean injured) {
        this.name = name;
        this.nationality = nationality;
        this.mountain = mountain;
        this.altitude = altitude;
        this.injured = injured;
    }

    @ManyToOne
    private Mountain mountain;
    private Integer altitude;
    @Getter(AccessLevel.NONE) private Boolean injured;

    public Boolean isInjured() {
        return injured;
    }
}
