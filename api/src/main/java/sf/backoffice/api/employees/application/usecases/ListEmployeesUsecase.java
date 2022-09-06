package sf.backoffice.api.employees.application.usecases;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import sf.backoffice.api.employees.application.interfaces.ListEmployeesUsecaseInterface;
import sf.backoffice.api.employees.commands.ListEmployeesCommand;
import sf.backoffice.api.employees.domains.Employee;
import sf.backoffice.api.employees.domains.EmployeePresenter;
import sf.backoffice.api.employees.errors.EmployeeFailedToObtainException;
import sf.backoffice.api.employees.persistence.interfaces.EmployeeRepositoryInterface;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ListEmployeesUsecase implements ListEmployeesUsecaseInterface {
    private final EmployeeRepositoryInterface employeeRepository;

    @Autowired
    public ListEmployeesUsecase(EmployeeRepositoryInterface employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<EmployeePresenter> execute(ListEmployeesCommand command) throws EmployeeFailedToObtainException {
        try{
            List<Employee> employees = employeeRepository.getAll();

            return employees
                    .stream()
                    .map(EmployeePresenter::fromDomain)
                    .collect(Collectors.toList());
        } catch (EmployeeFailedToObtainException exception){
            throw exception;
        }
    }
}
