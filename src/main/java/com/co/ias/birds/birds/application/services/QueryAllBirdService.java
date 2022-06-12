package com.co.ias.birds.birds.application.services;

import com.co.ias.birds.birds.application.domain.Bird;
import com.co.ias.birds.birds.application.ports.input.QueryAllBirdUseCase;
import com.co.ias.birds.birds.application.ports.input.QueryBirdUseCase;
import com.co.ias.birds.birds.application.ports.output.BirdRepository;
import com.co.ias.birds.infrastructure.models.BirdDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QueryAllBirdService implements QueryAllBirdUseCase {
    private final BirdRepository birdRepository;

    public QueryAllBirdService(BirdRepository birdRepository) {
        this.birdRepository = birdRepository;
    }


    @Override
    public Optional<List<BirdDTO>> execute(Void unused) {
        Optional<List<Bird>> birds = birdRepository.getAll();

        List<BirdDTO> birdsDTO = birds.get().stream().map(birds1 -> {
            BirdDTO birdDTO = BirdDTO.fromDomain(birds1);
            return birdDTO;
        }).collect(Collectors.toList());
        return Optional.of(birdsDTO);
    }
}
