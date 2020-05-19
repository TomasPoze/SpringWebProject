package lt.codeacademy.springwebproject.repositories;

import lt.codeacademy.springwebproject.models.Car;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Primary
@Repository
public class DbCarDao implements CarsDao {

    private JdbcTemplate jdbcTemplate;

    public DbCarDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Car> getCar(Long id) {
        return Optional.ofNullable(
                jdbcTemplate.queryForObject("SELECT * FROM Cars WHERE id = ?",
                        new Object[]{id},
                        (resultSet, rowNum) -> mapToCar(resultSet,rowNum))
        );
    }

    @Override
    public boolean deleteCar(Long id) {
        return false;
    }

    @Override
    public List<Car> getCars() {
        return jdbcTemplate.query("SELECT * FROM Cars;",
                (resultSet, rowNum) -> mapToCar(resultSet, rowNum));
    }

    private Car mapToCar(ResultSet resultSet, int rowNum) throws SQLException {
        Car car = new Car();
        car.setId(resultSet.getLong("id"));
        car.setBrand(resultSet.getString("brand"));
        car.setModel(resultSet.getString("model"));
        car.setYear(resultSet.getInt("year"));
        car.setKW(resultSet.getInt("kw"));

        return car;
    }

    @Override
    public Car updateCar(Car car) {
        return null;
    }

    @Override
    public Car createCar(Car car) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO Cars(brand, model, year, kw) VALUES(?, ?, ?, ?);",
                    new String[] {"product_id"}
            );
            ps.setString(1, car.getBrand());
            ps.setString(2, car.getModel());
            ps.setInt(3, car.getYear());
            ps.setInt(3, car.getKW());

            return ps;
        }, keyHolder);

        return getCar(keyHolder.getKey().longValue()).get();
    }
}
