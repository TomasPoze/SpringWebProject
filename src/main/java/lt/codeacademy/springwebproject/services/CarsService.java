package lt.codeacademy.springwebproject.services;

import lt.codeacademy.springwebproject.controller.CarNotFoundException;
import lt.codeacademy.springwebproject.models.Car;
import lt.codeacademy.springwebproject.repositories.CarRepository;
import lt.codeacademy.springwebproject.repositories.InternalCarsDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarsService {

    private CarRepository carRepository;

    public CarsService(CarRepository carRepository) {
        this.carRepository= carRepository;
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Car getCar(Long id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException("Car with id: " + id + " was not found"));
    }

    public Car createOrUpdateCar(Car car){
        return carRepository.save(car);
    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }
}
