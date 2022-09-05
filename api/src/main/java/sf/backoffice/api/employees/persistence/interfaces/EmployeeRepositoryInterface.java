package sf.backoffice.api.employees.persistence.interfaces;

import sf.backoffice.api.employees.domains.Employee;
import sf.backoffice.api.employees.domains.dtos.CreateOrUpdateEmployeeDto;
import sf.backoffice.api.employees.errors.EmployeeFailedToCreateException;
import sf.backoffice.api.employees.errors.EmployeeFailedToObtainException;
import sf.backoffice.api.employees.errors.EmployeeFailedToUpdateException;

import java.util.List;

public interface EmployeeRepositoryInterface{
    Employee create(CreateOrUpdateEmployeeDto createOrUpdateEmployeeDto) throws EmployeeFailedToCreateException;

    void update(CreateOrUpdateEmployeeDto createOrUpdateEmployeeDto) throws EmployeeFailedToUpdateException;

    List<Employee> getAll() throws EmployeeFailedToObtainException;

    Employee getById(long id) throws EmployeeFailedToObtainException;
}
