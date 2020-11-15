package dao_implementation;

import connection.*;
import dao_interface.*;
import model.*;
import java.util.*;
import java.sql.*;

public class AdministrationDAOImplementation  implements AdministrationDAOInterface {

	static Connection connection;

	public AdministrationDAOImplementation() throws ClassNotFoundException {// The constructor method will call the connection
																		// to the database
		connection = Connection_database.get_Connection(); // such will be available for all the implemented methods
															// from the dao_interface
	}

	// Implemented Methods previously declared on the interfaces DAO
	public void insertRegister(Administration register) {// this method receive a Register object in order to send it to the
														// database

		CallableStatement statement;// since this method is calling a procedure made on the database
		try { // I need to create a "CallableStatement" Object
			statement = connection.prepareCall(" CALL insert_register ( ? , ? , ? , ? ) ");
			statement.setString(1, register.getCourse_id());
			statement.setString(2, register.getSchedule());
			statement.setString(3, register.getStudent_id());
			statement.setString(4, register.getEmployee_id());
			statement.executeUpdate();
			connection.commit();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteRegister(String ID) {
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement("DELETE  FROM ADMINISTRATION WHERE course_id = ? OR student_id = ? OR employee_id = ? ");

			statement.setString(1, ID);
			statement.setString(2, ID);
			statement.setString(3, ID);
			statement.executeUpdate();
			connection.commit();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public ArrayList<Administration> retreive_administration_registers() {
		Administration register = null;
		ArrayList<Administration> registers = new ArrayList<Administration>();
		
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement("  SELECT course_id, student_id, employee_id, schedule FROM administration ");
			ResultSet result = statement.executeQuery();
			
			while (result.next()) {
			register = new Administration();
			
			register.setCourse_id(result.getString("course_id"));
			register.setStudent_id(result.getString("student_id"));
			register.setEmployee_id(result.getString("employee_id"));
			register.setSchedule(result.getString("schedule"));
			
			registers.add(register);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return registers;
	}
	
	
	
		public ArrayList<Administration> retreive_student_register(String student_id) {
			Administration register = null;
			Course course = null;
			Employee employee = null;
	
			ArrayList<Administration> register_of_student = new ArrayList<Administration>();
			PreparedStatement statement;
			try {
				statement = connection.prepareStatement("  SELECT e.employee_id, e.first_name , e.last_name , " + 
														" c.course_id, c.course_name, c.class_room , c.start_date," + 
														" a.schedule " + 
														" FROM administration a " + 
														" JOIN employees e" + 
														" ON (a.employee_id = e.employee_id) " + 
														" JOIN courses  c " + 
														" ON (a.course_id = c.course_id) " + 
														" WHERE a.student_id = ? ");
				statement.setString(1, student_id);
				
				ResultSet result = statement.executeQuery();
				while (result.next()) {
				register = new Administration();
				course = new Course();
				employee = new Employee();
	
	
				course.setCourseID(result.getString("course_id"));
				course.setCourseName(result.getString("course_name"));
				course.setClassRoom(result.getInt("class_room"));
				String startDate=result.getString("start_date");
				if(startDate != null ) {
				course.setStartDate(result.getString("start_date").substring(0,10));
				}else {
					course.setStartDate(result.getString("start_date"));
				}
	
				employee.setEmployeeID(result.getString("employee_id"));
				employee.setFirstName(result.getString("first_name"));
				employee.setLastName(result.getString("last_name"));


				register.setCourse(course);
				register.setEmployee(employee);
				register.setSchedule(result.getString("schedule"));
	
				
				register_of_student.add(register);
				}
	
			} catch (SQLException e) {
				e.printStackTrace();
			}
	
			return register_of_student;
		}
	
		public ArrayList<Administration> retreive_employee_register(String employee_id) {
			Administration register = null;
			Course course = null;
			Student student = null;
			
	
			ArrayList<Administration> register_of_employee = new ArrayList<Administration>();
			PreparedStatement statement;
			try {
				statement = connection.prepareStatement(" SELECT s.student_id , s.first_name , s.last_name, "+
														" c.course_id, c.course_name, c.class_room , c.start_date," + 													
														" a.schedule " + 
														" FROM administration a " + 
														" JOIN students s " + 
														" ON (a.student_id = s.student_id) " + 
														" JOIN courses  c " + 
														" ON (a.course_id = c.course_id) " + 
														" WHERE a.employee_id = ? ");
				statement.setString(1, employee_id);
				
				ResultSet result = statement.executeQuery();
				while (result.next()) {
				register = new Administration();
				course = new Course();
				student = new Student();
				
	
				
	
				course.setCourseID(result.getString("course_id"));
				course.setCourseName(result.getString("course_name"));
				course.setClassRoom(result.getInt("class_room"));
				String startDate=result.getString("start_date");
				if(startDate != null ) {
				course.setStartDate(result.getString("start_date").substring(0,10));
				}else {
					course.setStartDate(result.getString("start_date"));
				}
				
				student.setStudentID(result.getString("student_id"));
				student.setFirstName(result.getString("first_name"));
				student.setLastName(result.getString("last_name"));
	
				register.setStudent(student);
				register.setCourse(course);
				register.setSchedule(result.getString("schedule"));
	
				
				register_of_employee.add(register);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
	
			return register_of_employee;
		}
	
		public ArrayList<Administration> retreive_course_register(String course_id) {
		Administration register = null;
		Student student = null;
		Course course = null;
		Employee employee = null;

		ArrayList<Administration> register_of_course = new ArrayList<Administration>();
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement("  SELECT s.student_id, s.first_name, s.last_name,s.date_of_birth,s.email,s.password, " + 
													" e.employee_id, e.first_name AS employee_firstName , e.last_name AS employee_lastName , e.email, e.hire_date , e.role, e.password, " + 
													" c.course_id, c.course_name, c.class_room , c.start_date," + 
													" a.schedule " + 
													" FROM administration a " + 
													" JOIN students s " + 
													" ON (a.student_id = s.student_id) " + 
													" JOIN employees e" + 
													" ON (a.employee_id = e.employee_id) " + 
													" JOIN courses  c " + 
													" ON (a.course_id = c.course_id) " + 
													" WHERE a.course_id = ? ");
			statement.setString(1, course_id);
			
			ResultSet result = statement.executeQuery();
			while (result.next()) {
			register = new Administration();
			student = new Student();
			course = new Course();
			employee = new Employee();

			student.setStudentID(result.getString("student_id"));
			student.setFirstName(result.getString("first_name"));
			student.setLastName(result.getString("last_name"));
			String dateOfBirth=result.getString("date_of_birth");
			if(dateOfBirth != null ) {
			student.setDateOfBirth(result.getString("date_of_birth").substring(0,10));
			}else {
			student.setDateOfBirth(result.getString("date_of_birth"));
			}
			student.setEmail(result.getString("email"));
			student.setPassword(result.getString("password"));

			course.setCourseID(result.getString("course_id"));
			course.setCourseName(result.getString("course_name"));
			course.setClassRoom(result.getInt("class_room"));
			String startDate=result.getString("start_date");
			if(startDate != null ) {
			course.setStartDate(result.getString("start_date").substring(0,10));
			}else {
				course.setStartDate(result.getString("start_date"));
			}

			employee.setEmployeeID(result.getString("employee_id"));
			employee.setFirstName(result.getString("employee_firstName"));
			employee.setLastName(result.getString("employee_lastName"));
			employee.setEmail(result.getString("email"));
			String hireDate=result.getString("hire_date").substring(0,10);
			if(hireDate != null ) {
			employee.setHireDate(result.getString("hire_date").substring(0,10));
			}else {
				employee.setHireDate(result.getString("hire_date"));
			}
			employee.setRole(result.getString("role"));
			employee.setPassword(result.getString("password"));

			register.setStudent(student);
			register.setCourse(course);
			register.setEmployee(employee);
			register.setSchedule(result.getString("schedule"));

			register_of_course.add(register);
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return register_of_course;
	}

	
	

	
}
