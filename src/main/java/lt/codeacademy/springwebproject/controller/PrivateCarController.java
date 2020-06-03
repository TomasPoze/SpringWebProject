package lt.codeacademy.springwebproject.controller;

import lt.codeacademy.springwebproject.models.Car;
import lt.codeacademy.springwebproject.models.User;
import lt.codeacademy.springwebproject.services.CarsService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/private")
public class PrivateCarController {
    private CarsService carsService;

    public PrivateCarController(CarsService carsService) {
        this.carsService = carsService;
    }

    @GetMapping("/car/creation")
    @PreAuthorize("hasRole('ADMIN')")
    public String createCar(Model model, @AuthenticationPrincipal User user){
        model.addAttribute("car", new Car());
        model.addAttribute("user",user);
        return "carform";
    }

    @GetMapping("/car/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String updateCarForm(@PathVariable Long id, Model model, @AuthenticationPrincipal User user){
        Car car = carsService.getCar(id);
        model.addAttribute("user",user);
        model.addAttribute("car", car);
        return "carform";
    }

    @GetMapping("/car/{id}/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteCar(@PathVariable Long id, Model model, @AuthenticationPrincipal User user){
        carsService.deleteCar(id);
        List<Car> cars = carsService.getAllCars();
        model.addAttribute("cars", cars);
        model.addAttribute("user",user);
        return "carlist";
    }
    @PostMapping("/car/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String submitCar(@Valid Car car, BindingResult errors, Model model, @AuthenticationPrincipal User user){
        if (errors.hasErrors()){
            return "carform";
        }
        Car newCar = carsService.createOrUpdateCar(car);
        model.addAttribute("car",newCar);
        model.addAttribute("user",user);
        return "carinfo";
    }
}
