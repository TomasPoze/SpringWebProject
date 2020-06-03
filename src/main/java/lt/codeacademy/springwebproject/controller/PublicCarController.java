package lt.codeacademy.springwebproject.controller;

import lt.codeacademy.springwebproject.models.Car;
import lt.codeacademy.springwebproject.models.User;
import lt.codeacademy.springwebproject.services.CarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/public")
public class PublicCarController {

    private CarsService carsService;

    public PublicCarController(CarsService carsService) {
        this.carsService = carsService;
    }

    @GetMapping("/carlist") //Gets all cars at localhost:8080/carlist
    public String getAllCars(Model model, @AuthenticationPrincipal User user){
        List<Car> cars = carsService.getAllCars();
        model.addAttribute("user",user);
        model.addAttribute("cars",cars);
        return "carlist";
    }

    @GetMapping("/car/{id}") //Gets info about car when clicked from the list at localhost:8080/car/id
    public String getCar(@PathVariable Long id, Model model, @AuthenticationPrincipal User user){
        Car car = carsService.getCar(id);
        model.addAttribute("car",car);
        model.addAttribute("user",user);
        return "carinfo";
    }

    @GetMapping("/home")
    public String getHomePage(Model model, @AuthenticationPrincipal User user){
        List<Car> cars = carsService.getAllCars();
        model.addAttribute("cars", cars);
        model.addAttribute("user",user);
        return "home";
    }


}
