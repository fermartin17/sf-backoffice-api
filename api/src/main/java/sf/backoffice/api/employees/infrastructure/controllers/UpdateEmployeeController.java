package sf.backoffice.api.employees.infrastructure.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sf.backoffice.api.employees.application.interfaces.UpdateEmployeeUsecaseInterface;
import sf.backoffice.api.employees.commands.UpdateEmployeeCommand;
import sf.backoffice.api.employees.infrastructure.dtos.UpdateEmployeeDto;

import javax.ws.rs.core.Response;

@RestController
@RequestMapping("/api/employees")
public class UpdateEmployeeController {
    private final String PATH_BASE = "employees/";
    private final UpdateEmployeeUsecaseInterface updateEmployeeUsecase;

    @Autowired
    public UpdateEmployeeController(UpdateEmployeeUsecaseInterface updateEmployeeUsecase) {
        this.updateEmployeeUsecase = updateEmployeeUsecase;
    }

    @PutMapping("{employeeId}")
    @ResponseStatus(HttpStatus.OK)
    public Response update(@PathVariable(value = "employeeId") final Long employeeId, @RequestBody UpdateEmployeeDto updateEmployeeDto) {
        try {
            UpdateEmployeeCommand command = new UpdateEmployeeCommand(employeeId, updateEmployeeDto.getName());

            updateEmployeeUsecase.execute(command);

            return Response.ok()
                    .build();
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            return Response.serverError().build();
        }
    }
}
