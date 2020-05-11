package lt.codeacademy.springwebproject.repositories;

import lt.codeacademy.springwebproject.models.Car;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CarsDao {
    private List<Car> cars;

    public CarsDao() {
        this.cars = buildCars();
    }

    private List<Car> buildCars() {
        Car car1 = new Car();
        car1.setId(1L);
        car1.setBrand("BMW");
        car1.setModel("E90");
        car1.setYear(2008);
        car1.setKW(210);

        Car car2 = new Car();
        car2.setId(2L);
        car2.setBrand("BMW");
        car2.setModel("F10");
        car2.setYear(2013);
        car2.setKW(200);

        List<Car> cars = new ArrayList<>();
        cars.add(car1);
        cars.add(car2);

        return cars;
    }

    public List<Car> getCars(){
        return cars;
    }

    public Optional<Car> getCar(Long id) {
        return cars.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    public Car updateCar(Car car) {
        getCar(car.getId()).ifPresent(existingCar -> {
            existingCar.setBrand(car.getBrand());
            existingCar.setModel(car.getModel());
            existingCar.setYear(car.getYear());
            existingCar.setKW(car.getKW());
        });

        return car;
    }

    public Car createCar(Car car) {
        Long maxId = cars.stream()
                .mapToLong(Car::getId)
                .max().orElse(0L);

        car.setId(maxId + 1);
        cars.add(car);

        return car;
    }

    public boolean deleteCar(Long id) {
        List<Car> newList = cars.stream()
                .filter(p -> !p.getId().equals(id))
                .collect(Collectors.toList());
        boolean deleted = newList.size() != cars.size();
        cars = newList;
        return deleted;
    }
}
