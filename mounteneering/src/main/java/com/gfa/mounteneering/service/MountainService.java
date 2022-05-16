package com.gfa.mounteneering.service;

import com.gfa.mounteneering.model.Mountain;

import java.util.List;
import java.util.Optional;

public interface MountainService {
    List<Mountain> findAll();
    Optional<Mountain> findMountainById(Long id);
}
