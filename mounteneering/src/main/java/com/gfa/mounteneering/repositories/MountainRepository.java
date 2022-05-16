package com.gfa.mounteneering.repositories;

import com.gfa.mounteneering.model.Mountain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MountainRepository extends JpaRepository<Mountain, Long> {
}
