package sf.backoffice.api.employees.persistence.entities;

import sf.backoffice.api.employees.domains.Employee;
import sf.backoffice.api.employees.domains.dtos.CreateOrUpdateEmployeeDto;
import sf.backoffice.api.shared.entities.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "employees")
public class EmployeeEntity extends BaseEntity {

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EmployeeEntity(){}

    public Employee toDomainModel(){
        return new Employee(this.getId(), this.getName());
    }

    public static EmployeeEntity createEntity(CreateOrUpdateEmployeeDto createOrUpdateEmployeeDto){
        EmployeeEntity employeeEntity = new EmployeeEntity();

        employeeEntity.setName(createOrUpdateEmployeeDto.getName());

        return employeeEntity;
    }
}
