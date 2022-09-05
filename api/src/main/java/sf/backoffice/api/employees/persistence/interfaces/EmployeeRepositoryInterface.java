package sf.backoffice.api.employees.persistence.interfaces;

import sf.backoffice.api.employees.domains.Employee;
import sf.backoffice.api.employees.domains.dtos.CreateOrUpdateEmployeeDto;

import java.util.List;

public interface EmployeeRepositoryInterface{
    Employee create(CreateOrUpdateEmployeeDto createOrUpdateEmployeeDto);

    void update(CreateOrUpdateEmployeeDto createOrUpdateEmployeeDto);

    List<Employee> getAll();

    Employee getById(long id);
}
