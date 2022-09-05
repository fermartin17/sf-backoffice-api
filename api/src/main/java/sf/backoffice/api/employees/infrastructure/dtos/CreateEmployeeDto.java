package sf.backoffice.api.employees.infrastructure.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CreateEmployeeDto {

    @NotNull
    @NotEmpty
    private String name;

    public CreateEmployeeDto(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
