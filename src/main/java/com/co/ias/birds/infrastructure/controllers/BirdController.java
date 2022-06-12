package com.co.ias.birds.infrastructure.controllers;

import com.co.ias.birds.birds.application.ports.input.*;
import com.co.ias.birds.infrastructure.models.ApplicationError;
import com.co.ias.birds.infrastructure.models.BirdDTO;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class BirdController {
    private final CreateBirdUseCase createBirdUseCase;
    private final QueryBirdUseCase queryBirdUseCase;
    private final QueryAllBirdUseCase queryAllBirdUseCase;
    private final UpdateBirdUseCase updateBirdUseCase;
    private final DeleteBirdUseCase deleteBirdUseCase;

    public BirdController(CreateBirdUseCase createBirdUseCase, QueryBirdUseCase queryBirdUseCase, QueryAllBirdUseCase queryAllBirdUseCase, UpdateBirdUseCase updateBirdUseCase, DeleteBirdUseCase deleteBirdUseCase) {
        this.createBirdUseCase = createBirdUseCase;
        this.queryBirdUseCase = queryBirdUseCase;
        this.queryAllBirdUseCase = queryAllBirdUseCase;
        this.updateBirdUseCase = updateBirdUseCase;
        this.deleteBirdUseCase = deleteBirdUseCase;
    }

    @RequestMapping(value = "/bird", method = RequestMethod.GET)
    public ResponseEntity<?> getAll() {
        try {
            ObjectUtils.Null aNull = null;
            Optional<List<BirdDTO>> birdDTO = queryAllBirdUseCase.execute(null);
            if (birdDTO.isPresent()) {
                return ResponseEntity.ok(birdDTO.get());
            } else {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No exist bird with this id");
            }
        } catch (NullPointerException | IllegalArgumentException e) {
            ApplicationError applicationError = new ApplicationError("InputDataValidationError", "Bad input data",
                    Map.of("error", e.getMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(applicationError);
        } catch (Exception e) {
            ApplicationError applicationError = new ApplicationError("SystemError", "Try more later", Map.of());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(applicationError);
        }
    }

    @RequestMapping(value = "/bird/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getById(@PathVariable("id") Long id ) {
        try {
            Optional<BirdDTO> birdDTO = queryBirdUseCase.execute(id);
            if (birdDTO.isPresent()) {
                return ResponseEntity.ok(birdDTO.get());
            } else {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No exist bird with this id");
            }
        } catch (NullPointerException | IllegalArgumentException e) {
            ApplicationError applicationError = new ApplicationError("InputDataValidationError", "Bad input data",
                    Map.of("error", e.getMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(applicationError);
        } catch (Exception e) {
            ApplicationError applicationError = new ApplicationError("SystemError", "Try more later", Map.of());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(applicationError);
        }
    }

    @RequestMapping(value = "/bird", method = RequestMethod.POST)
    public ResponseEntity<?> createBird(@RequestBody BirdDTO birdDTO) {
        try {
            BirdDTO birdDTOOutput = createBirdUseCase.execute(birdDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(birdDTOOutput);
        } catch (NullPointerException | IllegalArgumentException e) {
            ApplicationError applicationError = new ApplicationError("InputDataValidationError", "Bad input data",
                    Map.of("error", e.getMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(applicationError);
        } catch (Exception e) {
            System.out.println(e.getCause().getMessage());
            if (e.getCause().getMessage().contains("duplicate key value violates unique constraint")) {
                ApplicationError applicationError = new ApplicationError("InputDataValidationError", "Bad input data",
                        Map.of("error", e.getCause().getMessage()));
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(applicationError);
            } else {
                ApplicationError applicationError = new ApplicationError("System Error", "Try more later",
                        Map.of());
                System.out.println("Error......: " + e.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(applicationError);
            }
        }
    }

    @RequestMapping(value = "/bird", method = RequestMethod.PUT)
    public ResponseEntity<?> updateBird(@RequestBody BirdDTO birdDTO) {
        try {
            BirdDTO birdDTOOutput = updateBirdUseCase.execute(birdDTO);
            return ResponseEntity.ok(birdDTOOutput);
        } catch (NullPointerException | IllegalArgumentException e) {
            ApplicationError applicationError = new ApplicationError("InputDataValidationError", "Bad input data",
                    Map.of("error", e.getMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(applicationError);
        } catch (Exception e) {
            System.out.println(e.getCause().getMessage());
            if (e.getCause().getMessage().contains("duplicate key value violates unique constraint")) {
                ApplicationError applicationError = new ApplicationError("InputDataValidationError", "Bad input data",
                        Map.of("error", e.getCause().getMessage()));
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(applicationError);
            } else {
                ApplicationError applicationError = new ApplicationError("System Error", "Try more later",
                        Map.of());
                System.out.println("Error......: " + e.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(applicationError);
            }
        }
    }

    @RequestMapping(value = "/bird/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteBirds(@PathVariable("id") Long birdId) {
        try {
            Boolean resultDelete = deleteBirdUseCase.execute(birdId);
            if (resultDelete) {
                return ResponseEntity.ok("The bird with the id: " + birdId + " was deleted");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The bird with the id: "+ birdId + " doesn't exist");
            }
        } catch (NullPointerException | IllegalArgumentException e) {
            ApplicationError applicationError = new ApplicationError("InputDataValidationError", "Bad input data",
                    Map.of("error", e.getMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(applicationError);
        } catch (Exception e) {
            ApplicationError applicationError = new ApplicationError("SystemError", "Try more later", Map.of());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(applicationError);
        }
    }


}
