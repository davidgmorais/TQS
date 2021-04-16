package tqsua.carinfo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import tqsua.carinfo.Model.Car;
import tqsua.carinfo.Repository.CarRepository;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

@DataJpaTest
public class CarRepositoryTest {
    @Autowired
    TestEntityManager entityManager;

    @Autowired
    private CarRepository carRepository;

    @Test
    public void whenFindCarById_andValidId_thenReturnCar() {
        Car car = new Car("BMW","1 series");
        entityManager.persistAndFlush(car);

        Car fromDb = carRepository.findByCarId(car.getCarId());
        assertThat(fromDb).isNotNull();
        assertThat(fromDb.getModel()).isEqualTo(car.getModel());
    }

    @Test
    public void whenFindCarById_andInvalidId_thenReturnNull() {
        Car fromDb = carRepository.findByCarId(-1L);
        assertThat(fromDb).isNull();
    }

    @Test
    public void givenSetOfCars_whenFindAll_returnAll() {
        Car bmw = new Car("BMW","1 series");
        Car audi = new Car("Audi","100");
        Car renault = new Car("Renault","Capture");

        entityManager.persist(bmw);
        entityManager.persist(audi);
        entityManager.persist(renault);

        List<Car> allCars = carRepository.findAll();
        assertThat(allCars).hasSize(3).extracting(Car::getModel).contains(bmw.getModel(), audi.getModel(), renault.getModel());
    }
}
