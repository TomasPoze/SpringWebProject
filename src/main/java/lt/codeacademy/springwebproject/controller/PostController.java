package lt.codeacademy.springwebproject.controller;

import lt.codeacademy.springwebproject.models.Car;
import lt.codeacademy.springwebproject.services.CarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class PostController {

    private CarsService carsService;

    public PostController(CarsService carsService) {
        this.carsService = carsService;
    }

    @GetMapping("/carlist") //Gets all cars at localhost:8080/carlist
    public String getAllCars(Model model){
        List<Car> cars = carsService.getAllCars();
        model.addAttribute("cars",cars);
        return "carlist";
    }

    @GetMapping("/car/{id}") //Gets info about car when clicked from the list at localhost:8080/car/id
    public String getCar(@PathVariable Long id, Model model){
        Car car = carsService.getCar(id);
        model.addAttribute("car",car);
        return "carinfo";
    }

    @GetMapping("/car/creation")
    public String createCar(Model model){
        model.addAttribute("car", new Car());
        return "carform";
    }

    @GetMapping("/car/update/{id}")
    public String updateCarForm(@PathVariable Long id, Model model){
        Car car = carsService.getCar(id);
        model.addAttribute("car", car);
        return "carform";
    }

    @GetMapping("/car/{id}/delete")
    public String deleteCar(@PathVariable Long id, Model model){
        List<Car> cars = carsService.deleteCar(id);
        model.addAttribute("cars", cars);
        return "carlist";
    }

    @PostMapping("/car/{id}")
    public String submitCar(@ModelAttribute Car car, Model model){
        Car newCar = carsService.createOrUpdateCar(car);
        model.addAttribute("car",newCar);
        return "carinfo";
    }
}
