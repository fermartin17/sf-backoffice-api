package sf.backoffice.api.employees.commands;

public class CreateEmployeeCommand {
    private String name;

    public CreateEmployeeCommand(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
