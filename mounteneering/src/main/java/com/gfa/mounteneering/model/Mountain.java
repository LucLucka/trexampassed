package com.gfa.mounteneering.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Mountain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer height;
    private Date firstAscend;

    public Mountain(String name, Integer height) {
        this.name = name;
        this.height = height;
    }

    @OneToMany(mappedBy = "mountain")
    private List<Climber> climbers = new ArrayList<>();

    public String getTitle() {
        return String.format(
                "%s - %dm (level %d)",
                getName(), getHeight(), getDifficulty());
    }

    public int getDifficulty() {
        return (getHeight() / 1000) + 1;
    }
}
