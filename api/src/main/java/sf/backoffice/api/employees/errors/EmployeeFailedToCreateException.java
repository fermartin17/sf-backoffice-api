package sf.backoffice.api.employees.errors;

public class EmployeeFailedToCreateException extends Exception {

    public EmployeeFailedToCreateException(String message){
        super(message);
    }
}
