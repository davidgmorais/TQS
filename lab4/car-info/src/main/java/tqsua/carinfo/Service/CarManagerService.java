package tqsua.carinfo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tqsua.carinfo.Model.Car;
import tqsua.carinfo.Repository.CarRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CarManagerService {

    @Autowired
    private CarRepository carRepository;

    public Car save(Car car) {
        return carRepository.save(car);
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Optional<Car> getCarDetails(Long carId) {
        Car found = carRepository.findByCarId(carId);
        return found != null ? Optional.of(found) : Optional.empty();
    }
}
