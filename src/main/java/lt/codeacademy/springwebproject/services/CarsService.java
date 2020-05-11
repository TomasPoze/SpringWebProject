package lt.codeacademy.springwebproject.services;

import lt.codeacademy.springwebproject.controller.CarNotFoundException;
import lt.codeacademy.springwebproject.models.Car;
import lt.codeacademy.springwebproject.repositories.CarsDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarsService {
    private CarsDao carsDao;

    public CarsService(CarsDao carsDao) {
        this.carsDao = carsDao;
    }

    public List<Car> getAllCars() {
        return carsDao.getCars();
    }

    public Car getCar(Long id) {
        return carsDao.getCar(id)
                .orElseThrow(() -> new CarNotFoundException("Car with id: " + id + " was not found"));
    }

    public Car createOrUpdateCar(Car car){
        if (car.getId() != null){
            return carsDao.updateCar(car);
        }else {
            return carsDao.createCar(car);
        }
    }

    public List<Car> deleteCar(Long id) {
        carsDao.deleteCar(id);
        return carsDao.getCars();
    }
}
