package sf.backoffice.api.employees.infrastructure.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UpdateEmployeeDto {

    @NotNull
    @NotEmpty
    private String name;

    public UpdateEmployeeDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
