package servlets;


import java.io.IOException;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import dao_implementation.*;
import dao_interface.*;
import model.*;

@WebServlet("/StudentController")
public class StudentController extends HttpServlet {
	// Declaring variables
	private static String EDIT= "/student_edit.jsp";
	private static String LIST_OF_STUDENTS = "/students.jsp";
	private StudentDAOInterface student_dao; // Setting the variable student DAO to make it a global one
	private String action;	// the string will keep the parameter obtained from the request
							// and based on user´s election will call the desired method

	// Creating Construction that will
	// Initialize the Object Student from DAO
	public StudentController() throws ServletException, IOException, ClassNotFoundException {
		super();
		student_dao = new StudentDAOImplementation();
	}

	// DO GET Method
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Declaring variables
		action = request.getParameter("action");
		
		// IF Create
		if(action.equalsIgnoreCase("create")) {
			create_student(request,response);
			retreive_students(request,response);
			RequestDispatcher view = request.getRequestDispatcher(LIST_OF_STUDENTS);
			view.forward(request, response);
			
		//IF Delete
			}else if (action.equalsIgnoreCase("delete")) {	
			delete_student(request,response);
			RequestDispatcher view = request.getRequestDispatcher(LIST_OF_STUDENTS);
			view.forward(request, response);			
		
		// IF Edit
		} else if (action.equalsIgnoreCase("edit")) {
			edit_student(request,response);
			retreive_students(request,response);
			RequestDispatcher view = request.getRequestDispatcher(LIST_OF_STUDENTS);
			view.forward(request, response);

		// IF Search
		} else if (action.equalsIgnoreCase("search")) {
			search_student(request,response);
			RequestDispatcher view = request.getRequestDispatcher(EDIT);
			view.forward(request, response);

		// IF List of Students		
		} else if (action.equalsIgnoreCase("List_of_students")) {
			retreive_students(request,response);
			RequestDispatcher view = request.getRequestDispatcher(LIST_OF_STUDENTS);
			view.forward(request, response);
		} 
		
		

	}

//DO POST Method
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);

	}
	
	
	
	
	
	
	public Student create_student(HttpServletRequest request, HttpServletResponse response) {
		//Declaring variables
		
				String studentID = request.getParameter("student_id");
				String firstName = request.getParameter("first_name");
				String lastName = request.getParameter("last_name");
				String dateOfBirth = request.getParameter("date_of_birth");
				String email = request.getParameter("email");
				String password = request.getParameter("password");

		//Creating and setting Student object
				Student student = new Student();
				student.setFirstName(firstName);
				student.setLastName(lastName);
				student.setStudentID(studentID);
				student.setDateOfBirth(dateOfBirth);
				student.setEmail(email);
				student.setPassword(password);
				
		
		
				student_dao.insertStudent(student);// inserting student object previously created
				
				return student;
	}

	public void edit_student(HttpServletRequest request, HttpServletResponse response) {
		//Declaring variables
		
				String studentID = request.getParameter("student_id");
				String firstName = request.getParameter("first_name");
				String lastName = request.getParameter("last_name");
				String dateOfBirth = request.getParameter("date_of_birth");
				String email = request.getParameter("email");
				String password = request.getParameter("password");
		
				student_dao.updateStudent(studentID, firstName, lastName, dateOfBirth, email, password);//updating student
	}
	
	public void search_student(HttpServletRequest request, HttpServletResponse response) {
		String studentID = request.getParameter("student_id");//Retrieving the value from the web form 
		
		request.setAttribute("student", student_dao.readStudent(studentID));//showing the student selected
	}
	
	public void delete_student(HttpServletRequest request, HttpServletResponse response) {
		String studentID = request.getParameter("student_id");//Retrieving the value from the web form 
	
		student_dao.deleteStudent(studentID);
		request.setAttribute("Students", student_dao.retreiveStudents());
	}
	
	public void retreive_students(HttpServletRequest request, HttpServletResponse response) {
		
		ArrayList<Student> students; // Creating List of students to keep the objects retrieved from method DAO

		students = student_dao.retreiveStudents(); // fetching students objects object from DataBase into ArrayList
		request.setAttribute("Students", students);// setting array in the request
		
	}
	

}