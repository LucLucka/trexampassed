package com.gfa.mounteneering.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ClimberDto {
    private String name;
    private String nationality;
    private Integer altitude;
    private Boolean injured;
    private String mountain;
}
