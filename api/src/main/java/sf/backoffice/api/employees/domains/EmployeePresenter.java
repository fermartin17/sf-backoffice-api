package sf.backoffice.api.employees.domains;

public class EmployeePresenter {
    private Long id;
    private String name;

    public EmployeePresenter(Long id, String name){
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static EmployeePresenter fromDomain(Employee employee){
        return new EmployeePresenter(employee.getId(), employee.getName());
    }
}
