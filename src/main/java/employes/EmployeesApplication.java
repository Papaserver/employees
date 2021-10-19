package employes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

//Sprinboot alkalmazás esetén nem szükséges a @ComponentScan annotáció,
// mert a @SpringBootApplication package-ben lévő összes állományt automatikusan felolvassa
@SpringBootApplication
public class EmployeesApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeesApplication.class, args);
	}

	//sajátkezűleg példányosítjuk a HelloService osztályt, így arra nem kell a @Service annotáció
	//a metódus @Bean annotációjáról tudja a spring, hogy ezt a metódust meg kell hívnia
	//sok service és controller esetén nincs értelme a rengeteg példnyosításnak
//	@Bean
//	public HelloService helloService() {
//		return new HelloService();
//	}

}
