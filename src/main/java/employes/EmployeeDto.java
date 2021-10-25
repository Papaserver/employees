package employes;

import lombok.Data;

//Ez az osztály az adatok áramoltatásáért felelős
@Data
public class EmployeeDto {

    private Long id;

    private String name;
}
