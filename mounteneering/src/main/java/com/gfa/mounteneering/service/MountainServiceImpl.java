package com.gfa.mounteneering.service;

import com.gfa.mounteneering.model.Mountain;
import com.gfa.mounteneering.repositories.MountainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MountainServiceImpl implements MountainService {

    private final MountainRepository mountainRepository;

    @Override
    public List<Mountain> findAll() {
        return mountainRepository.findAll(Sort.by("name").ascending());
    }

    @Override
    public Optional<Mountain> findMountainById(Long id) {
        return mountainRepository.findById(id);
    }
}
