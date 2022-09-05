package sf.backoffice.api.employees.application.interfaces;

import sf.backoffice.api.employees.commands.CreateEmployeeCommand;
import sf.backoffice.api.employees.domains.Employee;
import sf.backoffice.api.shared.aplication.usecases.UsecaseInterface;

public interface CreateEmployeeUsecaseInterface extends UsecaseInterface<CreateEmployeeCommand, Employee> {
}
