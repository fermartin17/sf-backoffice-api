package sf.backoffice.api.employees.application.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sf.backoffice.api.employees.application.interfaces.UpdateEmployeeUsecaseInterface;
import sf.backoffice.api.employees.commands.UpdateEmployeeCommand;
import sf.backoffice.api.employees.domains.dtos.CreateOrUpdateEmployeeDto;
import sf.backoffice.api.employees.errors.EmployeeFailedToUpdateException;
import sf.backoffice.api.employees.persistence.interfaces.EmployeeRepositoryInterface;

import javax.transaction.Transactional;

@Service
@Transactional
public class UpdateEmployeeUsecase implements UpdateEmployeeUsecaseInterface {

    private final EmployeeRepositoryInterface employeeRepository;

    @Autowired
    public UpdateEmployeeUsecase(EmployeeRepositoryInterface employeeRepository){
        this.employeeRepository = employeeRepository;
    }


    @Override
    public Void execute(UpdateEmployeeCommand command) throws EmployeeFailedToUpdateException {
        CreateOrUpdateEmployeeDto createOrUpdateEmployeeDto = generateCreateOrUpdateEmployeeDto(command);
        try{
            createOrUpdateEmployeeDto.setName(command.getName());

            employeeRepository.update(createOrUpdateEmployeeDto);

            return null;
        } catch (EmployeeFailedToUpdateException exception){
            throw exception;
        }
    }

    private CreateOrUpdateEmployeeDto generateCreateOrUpdateEmployeeDto(UpdateEmployeeCommand command){
        CreateOrUpdateEmployeeDto createOrUpdateEmployeeDto = new CreateOrUpdateEmployeeDto();

        createOrUpdateEmployeeDto.setName(command.getName());
        createOrUpdateEmployeeDto.setId(command.getId());

        return createOrUpdateEmployeeDto;
    }
}
