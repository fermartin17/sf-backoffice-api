package sf.backoffice.api.employees.infrastructure.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sf.backoffice.api.employees.application.interfaces.CreateEmployeeUsecaseInterface;
import sf.backoffice.api.employees.commands.CreateEmployeeCommand;
import sf.backoffice.api.employees.domains.Employee;
import sf.backoffice.api.employees.infrastructure.dtos.CreateEmployeeDto;

import javax.ws.rs.core.Response;
import java.net.URI;

@RestController
@RequestMapping("/api/employees")
public class CreateEmployeeController {
    private final String PATH_BASE = "employees/";
    private final CreateEmployeeUsecaseInterface createEmployeeUsecase;

    @Value("${base.url}")
    private String urlBase;

    @Autowired
    public CreateEmployeeController(CreateEmployeeUsecaseInterface createEmployeeUsecase) {
        this.createEmployeeUsecase = createEmployeeUsecase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response create(@RequestBody CreateEmployeeDto createEmployeeDto) {
        try{
            CreateEmployeeCommand command = new CreateEmployeeCommand(createEmployeeDto.getName());

            Employee employee = createEmployeeUsecase.execute(command);

            return Response.created(URI.create(urlBase + PATH_BASE + employee.getId()))
                    .contentLocation(URI.create(urlBase + PATH_BASE + employee.getId()))
                    .build();
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            return Response.serverError().build();
        }
    }
}
