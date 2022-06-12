package com.co.ias.birds.infrastructure.adapters.output;

import com.co.ias.birds.birds.application.domain.Bird;
import com.co.ias.birds.birds.application.domain.valueObjs.BirdId;
import com.co.ias.birds.birds.application.ports.output.BirdRepository;
import com.co.ias.birds.infrastructure.logSystem.Log;
import com.co.ias.birds.infrastructure.models.BirdDAO;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PostgresSqlBirdRepository implements BirdRepository {
    private final DataSource dataSource;

    public PostgresSqlBirdRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void store(Bird bird) {
        String sql = "INSERT INTO birds (common_name, scientific_name, zone_name, confirmed_quantity) values (?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, bird.getBirdCommonName().getValue());
            preparedStatement.setString(2, bird.getBirdScientificName().getValue());
            preparedStatement.setString(3, bird.getZoneName().getValue());
            if (bird.getConfirmedQuantity().getValue() != null) {
                preparedStatement.setInt(4, bird.getConfirmedQuantity().getValue());
            } else {
                preparedStatement.setNull(4, java.sql.Types.INTEGER);
                ;
            }
            preparedStatement.execute();
        } catch (SQLException e) {
            Log log = new Log(e.getMessage(), "Error querying database");
            log.logger();
            throw new RuntimeException("Error querying database", e);
        }
    }

    @Override
    public Optional<List<Bird>> getAll() {
        String sql = "SELECT * FROM birds";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            List<Bird> birds = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                System.out.println(resultSet.getLong("id"));
                BirdDAO birdDAO = BirdDAO.fromResultSet(resultSet);
                Bird bird = birdDAO.toDomain();
                birds.add(bird);
            }
            if (!birds.isEmpty()) {
                return Optional.of(birds);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            Log log = new Log(e.getMessage(), "Error querying database");
            log.logger();
            throw new RuntimeException("Error querying database", e);
        }
    }

    @Override
    public Optional<Bird> getById(BirdId id) {
        String sql = "SELECT * FROM birds WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id.getValue());

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                BirdDAO birdDAO = BirdDAO.fromResultSet(resultSet);
                Bird bird = birdDAO.toDomain();
                return Optional.of(bird);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            Log log = new Log(e.getMessage(), "Error querying database");
            log.logger();
            throw new RuntimeException("Error querying database", e);
        }
    }

    @Override
    public void update(Bird bird) {
        String sql = "UPDATE birds Set common_name = ?, scientific_name = ?, zone_name = ?, confirmed_quantity = ? Where id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, bird.getBirdCommonName().getValue());
            preparedStatement.setString(2, bird.getBirdScientificName().getValue());
            preparedStatement.setString(3, bird.getZoneName().getValue());
            if (bird.getConfirmedQuantity().getValue() != null) {
                preparedStatement.setInt(4, bird.getConfirmedQuantity().getValue());
            } else {
                preparedStatement.setNull(4, java.sql.Types.INTEGER);
            }
            preparedStatement.setLong(5, bird.getBirdId().getValue());
            preparedStatement.execute();
        } catch (SQLException e) {
            Log log = new Log(e.getMessage(), "Error querying database");
            log.logger();
            throw new RuntimeException("Error querying database", e);
        }
    }

    @Override
    public Boolean delete(BirdId id) {
        String sql = "DELETE from birds Where id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id.getValue());
            Boolean result = preparedStatement.execute();
            return result;
        } catch (SQLException e) {
            Log log = new Log(e.getMessage(), "Error querying database");
            log.logger();
            throw new RuntimeException("Error querying database", e);
        }
    }
}
