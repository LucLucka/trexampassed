package com.gfa.mounteneering.repositories;

import com.gfa.mounteneering.model.Climber;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClimberRepository extends JpaRepository<Climber, Long> {

    List<Climber> findAllByNationalityContainsIgnoreCaseAndAltitudeGreaterThanOrderByAltitudeDesc(
            @Param("nationality") String nationality, @Param("above") Integer above);
    List<Climber> findAllByAltitudeGreaterThanOrderByAltitudeDesc(@Param("above") Integer above);

}
