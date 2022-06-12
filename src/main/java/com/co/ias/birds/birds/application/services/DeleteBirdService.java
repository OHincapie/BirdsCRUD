package com.co.ias.birds.birds.application.services;

import com.co.ias.birds.birds.application.domain.Bird;
import com.co.ias.birds.birds.application.domain.valueObjs.BirdId;
import com.co.ias.birds.birds.application.ports.input.DeleteBirdUseCase;
import com.co.ias.birds.birds.application.ports.output.BirdRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteBirdService implements DeleteBirdUseCase {
    private final BirdRepository birdRepository;

    public DeleteBirdService(BirdRepository birdRepository) {
        this.birdRepository = birdRepository;
    }

    @Override
    public Boolean execute(Long aLong) {
        BirdId birdId = new BirdId(aLong);
        Optional<Bird> optionalBird = birdRepository.getById(birdId);
        if (optionalBird.isPresent()) {
            birdRepository.delete(birdId);
        }
        return optionalBird.isPresent();
    }
}
