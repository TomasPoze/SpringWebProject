package lt.codeacademy.springwebproject.repositories;

import lt.codeacademy.springwebproject.models.Car;

import java.util.List;
import java.util.Optional;

public interface CarsDao {
    Optional<Car> getCar(Long id);

    boolean deleteCar(Long id);

    List<Car> getCars();

    Car updateCar(Car car);

    Car createCar(Car car);
}
