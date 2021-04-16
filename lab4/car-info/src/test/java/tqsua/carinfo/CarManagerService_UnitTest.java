package tqsua.carinfo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.jupiter.MockitoExtension;
import tqsua.carinfo.Model.Car;
import tqsua.carinfo.Repository.CarRepository;
import tqsua.carinfo.Service.CarManagerService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CarManagerService_UnitTest {

    @Mock(lenient = true)
    private CarRepository carRepository;

    @InjectMocks
    private CarManagerService carManagerService;

    @BeforeEach
    public void setUp() {
        Car bmw = new Car("BMW","1 series");
        bmw.setCarId(12L);
        Car audi = new Car("Audi","100");
        Car renault = new Car("Renault","Capture");

        List<Car> allCars = Arrays.asList(bmw, audi, renault);

        when(carRepository.findByCarId(bmw.getCarId())).thenReturn(bmw);
        when(carRepository.findByCarId(-99L)).thenReturn(null);
        when(carRepository.findAll()).thenReturn(allCars);
    }

    @Test
    public void whenValidId_thenCarShouldBeFound() {
        Optional<Car> carFromDb = carManagerService.getCarDetails(12L);
        assertThat(carFromDb.get().getModel()).isEqualTo("1 series");

        verify(carRepository, VerificationModeFactory.times(1)).findByCarId(Mockito.anyLong());
    }

    @Test
    public void whenInvalidId_thenCarShouldNotBeFound() {
        Optional<Car> carFromDb = carManagerService.getCarDetails(-99L);
        assertThat(carFromDb).isEmpty();
        verify(carRepository, VerificationModeFactory.times(1)).findByCarId(Mockito.anyLong());
    }

    @Test
    public void givenCars_whenGetAll_returnAll() {
        Car bmw = new Car("BMW","1 series");
        Car audi = new Car("Audi","100");
        Car renault = new Car("Renault","Capture");

        List<Car> allCars = carManagerService.getAllCars();
        assertThat(allCars).hasSize(3).extracting(Car::getModel).contains(bmw.getModel(), audi.getModel(), renault.getModel());
        verify(carRepository, VerificationModeFactory.times(1)).findAll();
    }
}
