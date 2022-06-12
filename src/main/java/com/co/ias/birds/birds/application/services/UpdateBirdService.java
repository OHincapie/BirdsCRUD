package com.co.ias.birds.birds.application.services;

import com.co.ias.birds.birds.application.domain.Bird;
import com.co.ias.birds.birds.application.ports.input.UpdateBirdUseCase;
import com.co.ias.birds.birds.application.ports.output.BirdRepository;
import com.co.ias.birds.infrastructure.models.BirdDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateBirdService implements UpdateBirdUseCase {
    private final BirdRepository birdRepository;

    public UpdateBirdService(BirdRepository birdRepository) {
        this.birdRepository = birdRepository;
    }

    @Override
    public BirdDTO execute(BirdDTO birdDTO) {
        Bird bird = birdDTO.toDomain();
        Optional<Bird> optionalBird = birdRepository.getById(bird.getBirdId());
        if(optionalBird.isPresent()) {
            birdRepository.update(bird);
            birdDTO.setStatus("Updated");
        } else {
            birdDTO.setStatus("No Updated");
        }
        return birdDTO;
    }
}
