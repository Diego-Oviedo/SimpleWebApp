package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao_implementation.AdministrationDAOImplementation;
import dao_implementation.CourseDAOImplementation;
import dao_implementation.EmployeeDAOImplementation;
import dao_implementation.StudentDAOImplementation;
import dao_interface.AdministrationDAOInterface;
import dao_interface.CourseDAOInterface;
import dao_interface.EmployeeDAOInterface;
import dao_interface.StudentDAOInterface;
import model.Administration;
import model.Course;
import model.Employee;
import model.Student;

@WebServlet("/AdministrationController")
public class AdministrationController extends HttpServlet {
	
	// Declaring variables
		private static String LIST_OF_REGISTERS = "/registration.jsp";
		private static String STUDENT_REGISTERS = "/registers_student.jsp";
		private static String COURSE_REGISTERS = "/registers_course.jsp";
		private static String EMPLOYEE_REGISTERS = "/registers_employee.jsp";
		private CourseDAOInterface course_dao; 
		private EmployeeDAOInterface employee_dao;
		private StudentDAOInterface student_dao;
		private AdministrationDAOInterface register_dao;// Setting the variable administration DAO to make it a global one
		private String action = " ";	// the string will keep the parameter obtained from the request
								// and based on user´s election will call the desired method
    public AdministrationController() throws ServletException, IOException, ClassNotFoundException {
        super();
        register_dao = new AdministrationDAOImplementation();
        course_dao = new CourseDAOImplementation();
        employee_dao = new EmployeeDAOImplementation();
        student_dao = new StudentDAOImplementation();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		action = request.getParameter("action");
		
		if (action.equalsIgnoreCase("create")) {	
			create_register(request,response);
			retreive_students(request,response);
			retreive_courses(request,response);
			retreive_employees(request,response);
			retreive_registers(request,response);
			RequestDispatcher view = request.getRequestDispatcher(LIST_OF_REGISTERS);
			view.forward(request, response);
		}
		
		else if (action.equalsIgnoreCase("delete")) {
			delete_register(request,response);
			retreive_students(request,response);
			retreive_courses(request,response);
			retreive_employees(request,response);
			retreive_registers(request,response);
			RequestDispatcher view = request.getRequestDispatcher(LIST_OF_REGISTERS);
			view.forward(request, response);
		}
		
		else if (action.equalsIgnoreCase("retreive_registers")) {	
			retreive_students(request,response);
			retreive_courses(request,response);
			retreive_employees(request,response);
			retreive_registers(request,response);
			RequestDispatcher view = request.getRequestDispatcher(LIST_OF_REGISTERS);
			view.forward(request, response);
		}
	
		
		
		else if (action.equalsIgnoreCase("retreive_student_registers")) {	
			search_student(request,response);
			retreive_student_registered(request,response);
			RequestDispatcher view = request.getRequestDispatcher(STUDENT_REGISTERS);
			view.forward(request, response);
		}
		
		else if (action.equalsIgnoreCase("retreive_course_registers")) {	
			search_course(request,response);
			retreive_course_registered(request,response);
			RequestDispatcher view = request.getRequestDispatcher(COURSE_REGISTERS);
			view.forward(request, response);
		}
		else if (action.equalsIgnoreCase("retreive_employee_registers")) {
			search_employee(request,response);
			retreive_employee_registered(request,response);
			RequestDispatcher view = request.getRequestDispatcher(EMPLOYEE_REGISTERS);
			view.forward(request, response);
		}
		
		

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	public void create_register (HttpServletRequest request, HttpServletResponse response) {
		
		String courseID = request.getParameter("course_id");
		String employeeID = request.getParameter("employee_id");
		String studentID = request.getParameter("student_id");
		String schedule = request.getParameter("schedule");
		
		Administration register = new Administration();
		
		register.setCourse_id(courseID);
		register.setEmployee_id(employeeID);
		register.setStudent_id(studentID);
		register.setSchedule(schedule);
		
		register_dao.insertRegister(register);
		
	}
	
	public void delete_register (HttpServletRequest request, HttpServletResponse response) {
		String ID = request.getParameter("ID");
		
		register_dao.deleteRegister(ID);
		request.setAttribute("Registers", register_dao.retreive_administration_registers());
	}
	
	public void retreive_registers (HttpServletRequest request, HttpServletResponse response) {
		ArrayList<Administration> registers;
		
		registers = register_dao.retreive_administration_registers();
		request.setAttribute("Registers",registers);
	}

	
	
		public void search_employee(HttpServletRequest request, HttpServletResponse response) {
			String employeeID = request.getParameter("employee_id");//Retrieving the value from the web form 
			
			request.setAttribute("employee", employee_dao.readEmployee(employeeID));//showing the employee selected
		}
		public void retreive_employees(HttpServletRequest request, HttpServletResponse response) {
			
			ArrayList<Employee> employees; // Creating List of employees to keep the objects retrieved from method DAO

			employees = employee_dao.retreiveEmployees(); // fetching employee objects object from DataBase into ArrayList
			request.setAttribute("Employees", employees);// setting array in the request
			
		}
		public void retreive_employee_registered (HttpServletRequest request, HttpServletResponse response) {
			ArrayList<Administration> registers_employee;
			String employeeID = request.getParameter("employee_id");
			
			registers_employee = register_dao.retreive_employee_register(employeeID);
			request.setAttribute("Registers_employee",registers_employee);
		}
		
		
		public void search_student(HttpServletRequest request, HttpServletResponse response) {
			String studentID = request.getParameter("student_id");//Retrieving the value from the web form 
			
			request.setAttribute("student", student_dao.readStudent(studentID));//showing the student selected
		}	
		public void retreive_students(HttpServletRequest request, HttpServletResponse response) {
			
			ArrayList<Student> students; // Creating List of students to keep the objects retrieved from method DAO

			students = student_dao.retreiveStudents(); // fetching students objects object from DataBase into ArrayList
			request.setAttribute("Students", students);// setting array in the request
			
		}
		public void retreive_student_registered (HttpServletRequest request, HttpServletResponse response) {
			ArrayList<Administration> registers_student;
			String studentID = request.getParameter("student_id");
			
			registers_student = register_dao.retreive_student_register(studentID);
		
			request.setAttribute("Registers_student",registers_student);
		}
		
		
		public void search_course(HttpServletRequest request, HttpServletResponse response) {
			String courseID = request.getParameter("course_id");//Retrieving the value from the web form 
			
			request.setAttribute("course", course_dao.readCourse(courseID));//showing the course selected
		}
		public void retreive_courses(HttpServletRequest request, HttpServletResponse response) {
			
			ArrayList<Course> courses; // Creating List of courses to keep the objects retrieved from method DAO

			courses = course_dao.retreiveCourses(); // fetching course objects object from DataBase into ArrayList
			request.setAttribute("Courses", courses);// setting array in the request
			
		}
		public void retreive_course_registered (HttpServletRequest request, HttpServletResponse response) {
			ArrayList<Administration> registers_course;
			String courseID = request.getParameter("course_id");
			
			registers_course = register_dao.retreive_course_register(courseID);
			request.setAttribute("Registers_course",registers_course);
		}
		
	
	
	
	
	
	
	
	
	
	
	
}
