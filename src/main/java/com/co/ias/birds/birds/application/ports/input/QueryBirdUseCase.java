package com.co.ias.birds.birds.application.ports.input;

import com.co.ias.birds.commons.UseCase;
import com.co.ias.birds.infrastructure.models.BirdDTO;

import java.util.Optional;

public interface QueryBirdUseCase extends UseCase<Long, Optional<BirdDTO>> {
}
