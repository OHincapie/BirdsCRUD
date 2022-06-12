package com.co.ias.birds.birds.application.services;

import com.co.ias.birds.birds.application.domain.Bird;
import com.co.ias.birds.birds.application.domain.valueObjs.BirdId;
import com.co.ias.birds.birds.application.ports.input.QueryBirdUseCase;
import com.co.ias.birds.birds.application.ports.output.BirdRepository;
import com.co.ias.birds.infrastructure.models.BirdDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QueryBirdService implements QueryBirdUseCase {
    private final BirdRepository birdRepository;

    public QueryBirdService(BirdRepository birdRepository) {
        this.birdRepository = birdRepository;
    }

    @Override
    public Optional<BirdDTO> execute(Long aLong) {
        BirdId birdId = new BirdId(aLong);
        Optional<Bird> optionalBird = birdRepository.getById(birdId);
        return optionalBird.map(bird -> {
            BirdDTO birdDTO = BirdDTO.fromDomain(bird);
            return birdDTO;
        });
    }
}
