package employes;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

//Ez az osztály fogja előállítani az üdvözlő üzenetet
//Ha @Bean-t készítünk az application kontextben, akkor a service osztálynak nincs függősége a springre,
// ilyenkor nincs szükség a @Service annotációra
@Service
public class HelloService {

    public String sayHello() {
        return "Hello Spring Boot (Service devtools) " + LocalDateTime.now();
    }
}
