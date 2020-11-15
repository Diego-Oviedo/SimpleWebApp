package dao_interface;

import java.util.ArrayList;
import model.*;

public interface EmployeeDAOInterface {
	public void insertEmployee(Employee teacher);
	public void deleteEmployee(String employee_id);
	public Employee readEmployee(String employee_id);
	public void updateEmployee(String employee_id,String firstName,String lastName,String hireDate,String email,String roll,String password);
	public ArrayList<Employee> retreiveEmployees();
}
