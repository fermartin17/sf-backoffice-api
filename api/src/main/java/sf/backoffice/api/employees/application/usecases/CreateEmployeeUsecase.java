package sf.backoffice.api.employees.application.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sf.backoffice.api.employees.application.interfaces.CreateEmployeeUsecaseInterface;
import sf.backoffice.api.employees.commands.CreateEmployeeCommand;
import sf.backoffice.api.employees.domains.Employee;
import sf.backoffice.api.employees.domains.dtos.CreateOrUpdateEmployeeDto;
import sf.backoffice.api.employees.errors.EmployeeFailedToCreateException;
import sf.backoffice.api.employees.persistence.interfaces.EmployeeRepositoryInterface;

import javax.transaction.Transactional;

@Service
@Transactional
public class CreateEmployeeUsecase implements CreateEmployeeUsecaseInterface {
    private final EmployeeRepositoryInterface employeeRepository;

    @Autowired
    public CreateEmployeeUsecase(EmployeeRepositoryInterface employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee execute(CreateEmployeeCommand command) throws EmployeeFailedToCreateException {
        CreateOrUpdateEmployeeDto createOrUpdateEmployeeDto = new CreateOrUpdateEmployeeDto();
        try{
            createOrUpdateEmployeeDto.setName(command.getName());

            return employeeRepository.create(createOrUpdateEmployeeDto);
        } catch (EmployeeFailedToCreateException exception){
            throw exception;
        }
    }
}
