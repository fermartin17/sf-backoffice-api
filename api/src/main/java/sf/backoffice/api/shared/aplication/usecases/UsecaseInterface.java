package sf.backoffice.api.shared.aplication.usecases;

import sf.backoffice.api.employees.errors.EmployeeFailedToCreateException;
import sf.backoffice.api.employees.errors.EmployeeFailedToObtainException;
import sf.backoffice.api.employees.errors.EmployeeFailedToUpdateException;

public interface UsecaseInterface<C,T>{
    T execute(C command) throws EmployeeFailedToCreateException, EmployeeFailedToUpdateException, EmployeeFailedToObtainException;
}
