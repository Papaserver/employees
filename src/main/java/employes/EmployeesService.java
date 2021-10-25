package employes;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

//automatikus componentscan felolvasás
@Service
public class EmployeesService {

    private ModelMapper modelMapper;

    //az atomictól szálbiztos lesz, tehát nem fog kétszer ugyanaz a szám szerepelni
    private AtomicLong idGenerator = new AtomicLong();

    private List<Employee> employees = Collections.synchronizedList(new ArrayList<>(List.of(
            new Employee(idGenerator.incrementAndGet(), "John Doe"),
            new Employee(idGenerator.incrementAndGet(), "Jack Doe")
    )));

    public EmployeesService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<EmployeeDto> listEmployees(Optional<String> prefix) {
        //ezzel tudjuk megmondani, hogy milyen típusú listává konvertálja a modelmapper az átadott listát
        Type targetListType = new TypeToken<List<EmployeeDto>>(){}.getType();

        //az employees listát stream-mé alakítjuk, majd szűrjük, ha a prefix nem üres string, tehát meg van adva.
        //ha meg van adva a prefix, akkor az employee nevét elkérem, kis betűssé alakítom, és megnézem, hogy úgy kezdődik-e,
        //mint a prefix optional-től get()-tel elkért string kisbetűs változata. Végül listába gyűjtjük
        List<Employee> filtered = employees.stream()
                .filter(e -> prefix.isEmpty() || e.getName().toLowerCase().startsWith(prefix.get().toLowerCase()))
                .collect(Collectors.toList());

        //1. forrás lista, 2. milyen típusúvá konvertálja
        return modelMapper.map(filtered, targetListType);
    }

    //listából stream, szűrés az id-ra, findAny() optional-t ad vissza, ha nem talál akkor dob excpetiont, ha talál,
    // akkor azt visszaadja, és a modelMapper.map() metódus ezt alakítja át, EmployeeDto-vá
    public EmployeeDto findEmployeeById(long id) {
        return modelMapper.map(employees.stream()
                .filter(e -> e.getId() == id).findAny()
                .orElseThrow(() -> new IllegalArgumentException("Employee not found with " + id + " id")), EmployeeDto.class);
    }


    public EmployeeDto createEmployee(CreateEmployeeCommand command) {
        Employee employee = new Employee(idGenerator.incrementAndGet(), command.getName());
        employees.add(employee);
        return modelMapper.map(employee, EmployeeDto.class);
    }
}
