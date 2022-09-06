package sf.backoffice.api.employees.application.interfaces;

import sf.backoffice.api.employees.commands.ListEmployeesCommand;
import sf.backoffice.api.employees.domains.EmployeePresenter;
import sf.backoffice.api.shared.aplication.usecases.UsecaseInterface;

import java.util.List;

public interface ListEmployeesUsecaseInterface extends UsecaseInterface<ListEmployeesCommand, List<EmployeePresenter>> {
}
