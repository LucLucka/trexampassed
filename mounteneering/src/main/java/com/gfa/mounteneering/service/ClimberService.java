package com.gfa.mounteneering.service;

import com.gfa.mounteneering.dto.ClimberDto;
import com.gfa.mounteneering.model.Climber;
import com.gfa.mounteneering.model.Mountain;

import java.util.List;
import java.util.Optional;

public interface ClimberService {

    List<Climber> findAll();
    Climber addClimber(String name, String nationality, Mountain mountain);
    Optional<Climber> findById(Long id);

    Climber climb(Long id, Integer distance) throws Exception;

    Climber rescue(Long id) throws Exception;

    List<ClimberDto> search(String nationality, int above);
}
