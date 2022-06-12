package com.co.ias.birds.birds.application.ports.output;

import com.co.ias.birds.birds.application.domain.Bird;
import com.co.ias.birds.birds.application.domain.valueObjs.BirdId;
import com.co.ias.birds.commons.UseCase;

import java.util.List;
import java.util.Optional;

public interface BirdRepository {
    void store(Bird bird);

    Optional<List<Bird>> getAll();

    Optional<Bird> getById(BirdId id);

    void update(Bird bird);

    Boolean delete(BirdId id);
}
