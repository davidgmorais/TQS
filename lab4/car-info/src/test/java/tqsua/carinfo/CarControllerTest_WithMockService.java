package tqsua.carinfo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tqsua.carinfo.Controller.CarController;
import tqsua.carinfo.Model.Car;
import tqsua.carinfo.Service.CarManagerService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import net.minidev.json.JSONUtil;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;


@WebMvcTest(CarController.class)
public class CarControllerTest_WithMockService {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private CarManagerService service;

    @BeforeEach
    public void setUp() {}

    @Test
    public void whenPostCar_thenCreateCar() throws Exception {
        Car bmw = new Car("BMW","1 series");
        when(service.save(Mockito.any())).thenReturn(bmw);

        mvc.perform(post("/api/cars").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(bmw)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.model", is(bmw.getModel())));
        verify(service, times(1)).save(Mockito.any());
    }

    @Test
    public void givenCars_whenGetCars_thenReturnArray() throws Exception {
        Car bmw = new Car("BMW","1 series");
        Car audi = new Car("Audi","100");
        Car renault = new Car("Renault","Capture");

        List<Car> allCars = Arrays.asList(bmw, audi, renault);
        given(service.getAllCars()).willReturn(allCars);

        mvc.perform(get("/api/cars").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].model", is(bmw.getModel())))
                .andExpect(jsonPath("$[1].model", is(audi.getModel())))
                .andExpect(jsonPath("$[2].model", is(renault.getModel())));
        verify(service, VerificationModeFactory.times(1)).getAllCars();
    }
}
