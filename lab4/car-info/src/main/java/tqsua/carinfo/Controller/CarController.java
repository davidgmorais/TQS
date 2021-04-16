package tqsua.carinfo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tqsua.carinfo.Model.Car;
import tqsua.carinfo.Service.CarManagerService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CarController {
    @Autowired
    private CarManagerService carManagerService;

    @PostMapping("/cars")
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        HttpStatus status = HttpStatus.CREATED;
        Car saved = carManagerService.save(car);
        return new ResponseEntity<Car>(saved, status);
    }

    @GetMapping("/cars")
    public List<Car> getAllCars() {
        return carManagerService.getAllCars();
    }

    public ResponseEntity<Car> getCarById(Long carId) {
        return null;
    }
}
