package sf.backoffice.api.employees.infrastructure.dtos;

public class UpdateEmployeeDto {
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
