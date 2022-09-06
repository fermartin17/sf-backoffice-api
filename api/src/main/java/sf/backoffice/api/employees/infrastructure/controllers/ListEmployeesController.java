package sf.backoffice.api.employees.infrastructure.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import sf.backoffice.api.employees.application.interfaces.ListEmployeesUsecaseInterface;
import sf.backoffice.api.employees.commands.ListEmployeesCommand;
import sf.backoffice.api.employees.domains.Employee;

import javax.ws.rs.core.Response;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class ListEmployeesController {
    private final ListEmployeesUsecaseInterface listEmployeesUsecase;

    @Autowired
    public ListEmployeesController(ListEmployeesUsecaseInterface listEmployeesUsecase) {
        this.listEmployeesUsecase = listEmployeesUsecase;
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Response list(){
        try {
            ListEmployeesCommand command = new ListEmployeesCommand();
            List<Employee> employees = listEmployeesUsecase.execute(command);

            return Response.ok(employees)
                    .build();
        } catch (Exception ex){
            System.out.println("Exception: " + ex.getMessage());
            return Response.serverError().build();
        }
    }
}
