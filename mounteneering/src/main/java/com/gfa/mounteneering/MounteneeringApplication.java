package com.gfa.mounteneering;

import com.gfa.mounteneering.model.Climber;
import com.gfa.mounteneering.model.Mountain;
import com.gfa.mounteneering.repositories.ClimberRepository;
import com.gfa.mounteneering.repositories.MountainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class MounteneeringApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(MounteneeringApplication.class, args);
    }

    private final ClimberRepository climberRepository;
    private final MountainRepository mountainRepository;

    @Override
    public void run(String... args) throws Exception {
        Mountain m1 = mountainRepository.save(new Mountain("El Capitan", 2308));
        Mountain m2 = mountainRepository.save(new Mountain("Cerro Torre", 3128));
        Mountain m3 = mountainRepository.save(new Mountain("Matterhorn", 4478));
        Mountain m4 = mountainRepository.save(new Mountain("Kilimanjaro", 5895));
        Mountain m5 = mountainRepository.save(new Mountain("Denali", 6190));

        climberRepository.save(new Climber("Esy", "Czech", m1, 1502, false));
        climberRepository.save(new Climber("Martin", "Austrian", m2, 1902, false));
        climberRepository.save(new Climber("Ondra", "USA", m2, 1102, true));
        climberRepository.save(new Climber("John", "Canadian", m2, 1501, false));
    }
}
