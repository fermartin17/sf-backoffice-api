package sf.backoffice.api.employees.application.interfaces;

import sf.backoffice.api.employees.commands.UpdateEmployeeCommand;
import sf.backoffice.api.shared.aplication.usecases.UsecaseInterface;

public interface UpdateEmployeeUsecaseInterface extends UsecaseInterface<UpdateEmployeeCommand, Void> {
}
