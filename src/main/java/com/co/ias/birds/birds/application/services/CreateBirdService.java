package com.co.ias.birds.birds.application.services;

import com.co.ias.birds.birds.application.domain.Bird;
import com.co.ias.birds.birds.application.domain.valueObjs.*;
import com.co.ias.birds.birds.application.ports.input.CreateBirdUseCase;
import com.co.ias.birds.birds.application.ports.output.BirdRepository;
import com.co.ias.birds.infrastructure.models.BirdDTO;
import org.springframework.stereotype.Service;

@Service
public class CreateBirdService implements CreateBirdUseCase {

    private final BirdRepository birdRepository;

    public CreateBirdService(BirdRepository birdRepository) {
        this.birdRepository = birdRepository;
    }

    @Override
    public BirdDTO execute(BirdDTO birdDTO) {
        //¿Validar existencia por medio del getByID, o al tener el UNIQUE en la base de datos basta?
        Bird bird = new Bird(
                null,
                new BirdCommonName(birdDTO.getCommon_name()),
                new BirdScientificName(birdDTO.getScientific_name()),
                new ZoneName(birdDTO.getZone_name()),
                new ConfirmedQuantity(birdDTO.getConfirmed_quantity())
        );

        birdRepository.store(bird);
        birdDTO.setStatus("Created");
        return birdDTO;
    }
}
