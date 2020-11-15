package servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import dao_implementation.*;
import dao_interface.*;
import model.*;

@WebServlet("/CourseController")
public class CourseController extends HttpServlet {
	// Declaring variables
	private static String EDIT = "/course_edit.jsp";
	private static String LIST_OF_COURSES = "/courses.jsp";
	private CourseDAOInterface course_dao; // Setting the variable course DAO to make it a global one
	private String action = " ";// the string will keep the parameter obtained from the request
	// and based on user´s election will call the desired method

	// Creating Construction that will
	// Initialize the Object Course from DAO
	public CourseController() throws ServletException, IOException, ClassNotFoundException {
		super();
		course_dao = new CourseDAOImplementation();
	}

	// DO GET Method
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Declaring variables
		action = request.getParameter("action");

		// defining the method to call based on the type of action
		if (action.equalsIgnoreCase("create")) {
			
			create_course(request,response);
			retreive_courses(request,response);
			RequestDispatcher view = request.getRequestDispatcher(LIST_OF_COURSES);
			view.forward(request, response);
		}
		else if (action.equalsIgnoreCase("search")) {
			search_course(request,response);
			RequestDispatcher view = request.getRequestDispatcher(EDIT);
			view.forward(request, response);
		}
		else if (action.equalsIgnoreCase("List_of_courses")) {
			retreive_courses(request,response);
			RequestDispatcher view = request.getRequestDispatcher(LIST_OF_COURSES);
			view.forward(request, response);
		}
		
		else if (action.equalsIgnoreCase("delete")) {	
			delete_course(request,response);
			RequestDispatcher view = request.getRequestDispatcher(LIST_OF_COURSES);
			view.forward(request, response);
		}
		
		else if (action.equalsIgnoreCase("edit")) {	
			edit_course(request,response);
			retreive_courses(request,response);
			RequestDispatcher view = request.getRequestDispatcher(EDIT);
			view.forward(request, response);
		}
		
	}

//DO POST Method
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);

	}
	
	
	
	
	public void create_course(HttpServletRequest request, HttpServletResponse response) {
		// Declaring variables
				String courseID = request.getParameter("course_id");
				String courseName = request.getParameter("course_name");
				String startDate = request.getParameter("start_date");
				int classRoom = Integer.valueOf(request.getParameter("classroom"));

				// Creating and setting Course object
				Course course = new Course();
				course.setCourseName(courseName);
				course.setCourseID(courseID);
				course.setStartDate(startDate);
				course.setClassRoom(classRoom);
				
				course_dao.insertCourse(course);// inserting course object previously created
	}
	
	public void edit_course(HttpServletRequest request, HttpServletResponse response) {
				// Declaring variables
				String courseID = request.getParameter("course_id");
				String courseName = request.getParameter("course_name");
				String startDate = request.getParameter("start_date");
				int classRoom = Integer.parseInt(request.getParameter("classroom"));
				
				course_dao.updateCourse(courseID, courseName, startDate, classRoom);// updating course object
	}

	public void search_course(HttpServletRequest request, HttpServletResponse response) {
		String courseID = request.getParameter("course_id");//Retrieving the value from the web form 
		
		request.setAttribute("course", course_dao.readCourse(courseID));//showing the course selected
	}

	public void delete_course(HttpServletRequest request, HttpServletResponse response) {
		String courseID = request.getParameter("course_id");//Retrieving the value from the web form 
	
		course_dao.deleteCourse(courseID);
		request.setAttribute("Courses", course_dao.retreiveCourses());
	}

	public void retreive_courses(HttpServletRequest request, HttpServletResponse response) {
		
		ArrayList<Course> courses; // Creating List of courses to keep the objects retrieved from method DAO

		courses = course_dao.retreiveCourses(); // fetching course objects object from DataBase into ArrayList
		request.setAttribute("Courses", courses);// setting array in the request
		
	}
	
}