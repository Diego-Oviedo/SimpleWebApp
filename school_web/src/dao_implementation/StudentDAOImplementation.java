package dao_implementation;

import connection.*;
import dao_interface.*;
import model.*;
import java.sql.*;
import java.util.ArrayList;

public class StudentDAOImplementation implements StudentDAOInterface
{

	Connection connection;

	public StudentDAOImplementation() throws ClassNotFoundException {// The constructor method will call the connection
																		// to the database
		connection = Connection_database.get_Connection(); // such will be available for all the implemented methods
															// from the dao_interface
	}

	// Implemented Methods previously declared on the interfaces DAO
	public void insertStudent(Student student) {// this method receive a Student object in order to send it to the
												// database

		CallableStatement statement;// since this method is calling a procedure made on the database
		try { // I need to create a "CallableStatement" Object
			statement = connection.prepareCall(" CALL insert_student ( ? , ? , ? , ? , ? , ?) ");
			statement.setString(1, student.getStudentID());
			statement.setString(2, student.getFirstName());
			statement.setString(3, student.getLastName());
			statement.setString(4, student.getDateOfBirth());
			statement.setString(5, student.getEmail());
			statement.setString(6, student.getPassword());
			statement.executeUpdate();
			connection.commit();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void deleteStudent(String student_id) {
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement("DELETE  FROM Students " + " WHERE student_id = ? ");
			statement.setString(1, student_id);
			statement.executeUpdate();
			connection.commit();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		
	}

	public Student readStudent(String student_id) {
		Student student = new Student();
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(
					" SELECT  student_id , first_name , last_name , TO_CHAR(date_of_birth, 'DD MONTH YYYY') AS date_of_birth , email  "
							+ " FROM Students " + " WHERE student_id = ? ");

			statement.setString(1, student_id);

			ResultSet result = statement.executeQuery();
			while (result.next()) {
				student.setStudentID(result.getString("student_id"));
				student.setFirstName(result.getString("first_name"));
				student.setLastName(result.getString("last_name"));
				student.setDateOfBirth(result.getString("date_of_birth")); 
				student.setEmail(result.getString("email"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return student;
	}

	public void updateStudent(String student_id, String firstName, String lastName, String dateOfBirth, String email,String password) {
		PreparedStatement statement;

		try {
			statement = connection.prepareStatement(
					" UPDATE Students SET first_name = ? , last_name = ? , date_of_birth = ? , email = ? , password = ? "
							+ " WHERE student_id = ? ");

			statement.setString(6, student_id);
			statement.setString(1, firstName);
			statement.setString(2, lastName);
			statement.setString(3, dateOfBirth);
			statement.setString(4, email);
			statement.setString(5, password);

			statement.executeUpdate();
			connection.commit();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public ArrayList<Student> retreiveStudents() {
		ArrayList<Student> registed_students = new ArrayList<Student>();
		Student student = null;
		PreparedStatement statement;

		try {

			statement = connection.prepareStatement(
					" SELECT  student_id , first_name , last_name , date_of_birth , email FROM  Students ");

			ResultSet result = statement.executeQuery();

			while (result.next()) {
				student = new Student();
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

				registed_students.add(student);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return registed_students;

	}

}