package dao_implementation;

import connection.*;
import dao_interface.*;
import model.*;
import java.sql.*;
import java.util.ArrayList;

public class CourseDAOImplementation implements CourseDAOInterface {

	Connection connection;

	public CourseDAOImplementation() throws ClassNotFoundException {// The constructor method will call the connection to
																	// the database
		connection = Connection_database.get_Connection(); // such will be available for all the implemented methods
															// from the dao_interface
	}

	// Implemented Methods previously declared on the interfaces DAO
	public void insertCourse(Course course) {// this method receive a Course object in order to send it to the database

		CallableStatement statement;// since this method is calling a procedure made on the database
		try { // I need to create a "CallableStatement" Object
			statement = connection.prepareCall(" CALL insert_course( ? , ? , ? , ? ) ");
			statement.setString(1, course.getCourseID());
			statement.setString(2, course.getCourseName());
			statement.setString(3, course.getStartDate());
			statement.setInt(4, course.getClassRoom());
			statement.executeUpdate();
			connection.commit();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void deleteCourse(String course_id) {
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement("DELETE  FROM COURSES WHERE COURSE_ID = ? ");

			statement.setString(1, course_id);
			statement.executeUpdate();
			connection.commit();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public Course readCourse(String course_id) {
		Course course = new Course();
		PreparedStatement statement;

		try {
			statement = connection.prepareStatement(
					" SELECT  course_id, course_name, TO_CHAR(start_date, 'DD MONTH YYYY' ) AS start_date ,class_room "
							+ " FROM Courses " + " WHERE course_id = ? ");
			statement.setString(1, course_id);

			ResultSet result = statement.executeQuery();
			while (result.next()) {
				course.setCourseID(result.getString("course_id"));
				course.setCourseName(result.getString("course_name"));
				course.setStartDate(result.getString("start_date"));
				course.setClassRoom(result.getInt("class_room"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return course;
	}

	public void updateCourse(String courseID, String courseName, String startDate, int classRoom) {
		PreparedStatement statement;

		try {
			statement = connection.prepareStatement("UPDATE Courses "
					+ " SET course_name = ? , start_date = ? , class_room = ? " + " WHERE course_id = ? ");

			statement.setString(4, courseID);
			statement.setString(1, courseName);
			statement.setString(2, startDate);
			statement.setInt(3, classRoom);

			statement.executeUpdate();
			connection.commit();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public ArrayList<Course> retreiveCourses() {
		ArrayList<Course> registed_courses = new ArrayList<Course>();
		Course course = null;

		PreparedStatement statement;

		try {

			statement = connection.prepareStatement(
					" SELECT  course_id , course_name , start_date , class_room " + " FROM  Courses ");

			ResultSet result = statement.executeQuery();

			while (result.next()) {
				course = new Course();
				course.setCourseID(result.getString("course_id"));
				course.setCourseName(result.getString("course_name"));
				String startDate=result.getString("start_date");
				if(startDate != null ) {
				course.setStartDate(result.getString("start_date").substring(0,10));
				}else {
					course.setStartDate(result.getString("start_date"));
				}	
				course.setClassRoom(result.getInt("class_room"));

				registed_courses.add(course);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return registed_courses;

	}

}
