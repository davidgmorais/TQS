package tqsua.carinfo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import tqsua.carinfo.Model.Car;
import tqsua.carinfo.Repository.CarRepository;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureTestDatabase
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class CarControllerIT {
    @LocalServerPort
    int randomServerPort;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CarRepository carRepository;

    @AfterEach
    public void cleanUp() {
        carRepository.deleteAll();
    }

    @Test
    public void whenValidInput_thenCreateCar() {
        Car bmw = new Car("BMW","1 series");
        ResponseEntity<Car> entity = restTemplate.postForEntity("/api/cars", bmw, Car.class);

        List<Car> found = carRepository.findAll();
        assertThat(found).extracting(Car::getModel).containsOnly("1 series");
    }

    @Test
    public void givenCars_whenGetCars_thenStatus200() {
        carRepository.saveAndFlush(new Car("BMW", "1 series"));
        carRepository.saveAndFlush(new Car("Renault", "Capture"));

        ResponseEntity<List<Car>> response = restTemplate.exchange("/api/cars", HttpMethod.GET, null, new ParameterizedTypeReference<>() {});
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).extracting(Car::getModel).containsExactly("1 series", "Capture");
    }
}
