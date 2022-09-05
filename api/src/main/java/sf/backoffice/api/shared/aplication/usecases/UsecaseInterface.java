package sf.backoffice.api.shared.aplication.usecases;

import sf.backoffice.api.employees.errors.EmployeeFailedToCreateException;

public interface UsecaseInterface<C,T>{
    T execute(C command) throws EmployeeFailedToCreateException;
}
