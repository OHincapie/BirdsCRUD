package com.co.ias.birds.birds.application.ports.input;

import com.co.ias.birds.commons.UseCase;
import com.co.ias.birds.infrastructure.models.BirdDTO;

import java.util.List;
import java.util.Optional;


public interface QueryAllBirdUseCase extends UseCase<Void, Optional<List<BirdDTO>>> {
}
