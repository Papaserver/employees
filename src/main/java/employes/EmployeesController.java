package employes;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//ezzel adjuk meg, hogy a metódusok visszatérési értékét be kell írni a http responseba (először JSON-be konvertálja)
//@RequestMapping - milyen címen figyeljen
@RestController
@RequestMapping("/api/employees")
public class EmployeesController {

    private EmployeesService employeesService;

    public EmployeesController(EmployeesService employeesService) {
        this.employeesService = employeesService;
    }

    //Mivel csak lekérdezzük a listát, ezért @GetMapping-et használunk|||
    // @RequestParam Optional<String> prefix -URL
    // paraméterként opcionálisan vár egy stringet, ami alapján kikeresi majd a feltételnek megfelelő alkalmazottat
    @GetMapping
    public List<EmployeeDto> listEmployees(@RequestParam Optional<String> prefix) {
        return employeesService.listEmployees(prefix);
    }

    //id alapján szűri az alkalmazottakat. @GetMapping("/{id}") egy placeholder, mert az URL tartalmazni fogja az id-t
    //ezt a kettő összekonkatenálja, ezért /api/employees/id címen lehet lekérni az alakalmazottat
    //@PathVariable long id ezzel adjuk meg, hogy a paraméter id az ugyanaz, mint az URL-ben szereplő id placeholder
    @GetMapping("/{id}")
    public EmployeeDto findEmployeeById(@PathVariable long id) {
        return employeesService.findEmployeeById(id);
    }

    //@RequestBody CreateEmployeeCommand command - a bodyban beérkező adatok alapján kell ezt létrehoznia
    @PostMapping
    public EmployeeDto createEmployee(@RequestBody CreateEmployeeCommand command) {
        return employeesService.createEmployee(command);
    }
}
