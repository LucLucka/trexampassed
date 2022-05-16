package com.gfa.mounteneering.service;

import com.gfa.mounteneering.dto.ClimberDto;
import com.gfa.mounteneering.model.Climber;
import com.gfa.mounteneering.model.Mountain;
import com.gfa.mounteneering.repositories.ClimberRepository;
import com.gfa.mounteneering.repositories.MountainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClimberServiceImpl implements ClimberService {

    private final ClimberRepository climberRepository;
    private final MountainRepository mountainRepository;

    @Override
    public List<Climber> findAll() {
        return climberRepository.findAll(
                Sort.by("name").ascending()
        );
    }

    @Override
    public Climber addClimber(String name, String nationality, Mountain mountain) {
        return climberRepository.save(new Climber(
                name, nationality, mountain, 0, false
        ));
    }

    @Override
    public Optional<Climber> findById(Long id) {
        return climberRepository.findById(id);
    }

    public Climber climb(Long id, Integer distance) throws Exception {
        if (Objects.isNull(id) || Objects.isNull(distance) || (distance < 0)) {
            throw new Exception();
        }

        var climberOptional = findById(id);

        if (climberOptional.isEmpty()) {
            throw new Exception();
        }

        Climber climber = climberOptional.get();

        int injuryProbability = Math.min(70, climber.getMountain().getDifficulty() * 10);
        boolean isInjured = (Math.random() * 100) < injuryProbability;

        if (
                climber.isInjured() ||
                isInjured ||
                climber.getMountain().getHeight() <= climber.getAltitude()) {

            if (!climber.isInjured() && isInjured) {
                climber.setInjured(true);
                climberRepository.saveAndFlush(climber);
            }

            throw new Exception();
        } else {
            var mountain = climber.getMountain();

            climber.setAltitude(Math.min(
                    climber.getAltitude() + distance,
                    mountain.getHeight()));

            if (climber.getAltitude() >= mountain.getHeight()) {
                if (Objects.isNull(mountain.getFirstAscend())) {
                    mountain.setFirstAscend(Date.valueOf(LocalDate.now()));
                    mountainRepository.save(mountain);
                }
            }

            return climberRepository.save(climber);
        }
    }

    @Override
    public Climber rescue(Long id) throws Exception {
        if (Objects.isNull(id)) {
            throw new Exception();
        }

        var climber = climberRepository.findById(id);

        if (climber.isEmpty() || !climber.get().isInjured()) {
            throw new Exception();
        }

        climber.get().setAltitude(0);
        climber.get().setInjured(false);

        climberRepository.save(climber.get());
        return climber.get();
    }

    @Override
    public List<ClimberDto> search(String nationality, int above) {

        List<Climber> climbers;

        if (Objects.nonNull(nationality)) {
            climbers = climberRepository.findAllByNationalityContainsIgnoreCaseAndAltitudeGreaterThanOrderByAltitudeDesc(
                    nationality, above
            );
        } else {
            climbers = climberRepository.findAllByAltitudeGreaterThanOrderByAltitudeDesc(
                    above
            );
        }

        return climbers.stream()
                .map(x -> new ClimberDto(x.getName(), x.getNationality(),
                        x.getAltitude(), x.isInjured(),
                        x.getMountain().getName()))
                .collect(Collectors.toList());
    }
}
