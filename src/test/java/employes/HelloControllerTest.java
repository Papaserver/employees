package employes;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

//a service-t mockolni kell, mert ez külső függőség a controllerhez
@ExtendWith(MockitoExtension.class)
class HelloControllerTest {

    //ez nem valódi példány
    @Mock
    HelloService helloService;

    //ez valódi példány, amibe a mock adat injektálásra kerül
    @InjectMocks
    HelloController helloController;

    @Test
    void sayHello() {
        when(helloService.sayHello()).thenReturn("Szevasz bácsikám!");

        String message = helloController.sayHello();

        assertThat(message).isEqualTo("Szevasz bácsikám!");
    }
}
