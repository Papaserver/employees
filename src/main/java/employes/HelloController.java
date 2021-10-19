package employes;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

//Ha @RestController annotációt használunk, akkor nem szükséges a metódusra a @ResponseBody annotáció
@RestController
public class HelloController {
    //Ha egy konstruktor van akkor SB-ban nincs szükség az @Autowired annotációra
    private HelloService helloService;

    //a kötelező elemeket konstruktor injectionben kell átadni
    //amikor a spring példányosítja a HC-t akkor látja, hogy van egy HS függősége, amit automatikusan
    // példányosítani fog és át fog adni
    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    //a hívást csak delegálja a Service felé
    @GetMapping("/")
    public String sayHello() {
        return helloService.sayHello();
    }
}
