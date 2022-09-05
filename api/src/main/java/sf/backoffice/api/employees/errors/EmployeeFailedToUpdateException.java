package sf.backoffice.api.employees.errors;

public class EmployeeFailedToUpdateException extends Exception{
    public EmployeeFailedToUpdateException(String message){
        super(message);
    }
}
