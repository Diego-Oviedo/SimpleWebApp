package dao_implementation;

import connection.*;
import dao_interface.*;
import model.*;
import java.sql.*;
import java.util.ArrayList;

public class EmployeeDAOImplementation implements EmployeeDAOInterface {
	Connection connection;

	public EmployeeDAOImplementation() throws ClassNotFoundException {// The constructor method will call the connection
																		// to the database
		connection = Connection_database.get_Connection(); // such will be available for all the implemented methods
															// from the dao_interface
	}

	// Implemented Methods previously declared on the interfaces DAO
	public void insertEmployee(Employee employee) { // this method receive an Employee object in order to send it to the
													// database

		CallableStatement statement;// since this method is calling a procedure made on the database
		try { // I need to create a "CallableStatement" Object in order to set my procedure declared on the ORACLE DB
			statement = connection.prepareCall(" CALL insert_employee ( ? , ? , ? , ? , ? , ? , ? ) ");
			statement.setString(1, employee.getEmployeeID());
			statement.setString(2, employee.getFirstName());
			statement.setString(3, employee.getLastName());
			statement.setString(4, employee.getHireDate());
			statement.setString(5, employee.getEmail());
			statement.setString(6, employee.getRole());
			statement.setString(7, employee.getPassword());
			statement.executeUpdate();
			connection.commit();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void deleteEmployee(String employee_id) {
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement("DELETE  FROM Employees " + " WHERE employee_id = ? ");
			statement.setString(1, employee_id);
			statement.executeUpdate();
			connection.commit();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Employee readEmployee(String employee_id) {
		Employee teacher = new Employee();
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(
					" SELECT  employee_id , first_name , last_name , TO_CHAR(hire_date, 'DD MONTH YYYY' ) AS hire_date , email , role  "
							+ " FROM Employees " + " WHERE employee_id = ? ");

			statement.setString(1, employee_id);

			ResultSet result = statement.executeQuery();
			while (result.next()) {
				teacher.setEmployeeID(result.getString("employee_id"));
				teacher.setFirstName(result.getString("first_name"));
				teacher.setLastName(result.getString("last_name"));
				teacher.setHireDate(result.getString("hire_date"));
				teacher.setEmail(result.getString("email"));
				teacher.setRole(result.getString("role"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return teacher;
	}

	public void updateEmployee(String employee_id, String firstName, String lastName, String hireDate, String email, String role, String password) {
		PreparedStatement statement;

		try {
			statement = connection.prepareStatement(" UPDATE Employees "
					+ " SET first_name = ? , last_name = ? , hire_date = ? , email = ? , role = ? , password = ? " 
					+ " WHERE employee_id = ? ");

			statement.setString(7, employee_id);
			statement.setString(1, firstName);
			statement.setString(2, lastName);
			statement.setString(3, hireDate);
			statement.setString(4, email);
			statement.setString(5, role);
			statement.setString(6, password);

			statement.executeUpdate();
			connection.commit();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public ArrayList<Employee> retreiveEmployees() {
		ArrayList<Employee> registed_employees = new ArrayList<Employee>();
		Employee employee = null;
		PreparedStatement statement;

		try {

			statement = connection.prepareStatement(
					" SELECT  employee_id , first_name ,last_name , hire_date , email , role  FROM  Employees ");

			ResultSet result = statement.executeQuery();

			while (result.next()) {
				employee = new Employee();
				employee.setEmployeeID(result.getString("employee_id"));
				employee.setFirstName(result.getString("first_name"));
				employee.setLastName(result.getString("last_name"));
				String hireDate=result.getString("hire_date").substring(0,10);
				if(hireDate != null ) {
				employee.setHireDate(result.getString("hire_date").substring(0,10));
				}else {
					employee.setHireDate(result.getString("hire_date"));
				}
				employee.setEmail(result.getString("email"));
				employee.setRole(result.getString("role"));

				registed_employees.add(employee);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return registed_employees;

	}

}