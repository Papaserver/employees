package employes;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

//IT - integrációs teszt
//a @SpringBootTest annotáció hatására az egész SB konténerünk el fog indulni
@SpringBootTest
class HelloIT {

    //dependency injection történik az @Autowired annotáció révén
    @Autowired
    HelloController helloController;

    @Test
    void sayHello() {
        String message = helloController.sayHello();

        assertThat(message).startsWith("Hello Spring");
    }
}
