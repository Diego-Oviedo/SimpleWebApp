package servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import dao_implementation.*;
import dao_interface.*;
import model.*;

@WebServlet("/EmployeeController")
public class EmployeeController extends HttpServlet {

	// Declaring variables
	private static String EDIT = "/employee_edit.jsp";
	private static String LIST_OF_EMPLOYEES = "/employees.jsp";
	private EmployeeDAOInterface employee_dao; // Setting the variable employee DAO to make it a global one
	private String action = " ";// the string will keep the parameter obtained from the request
							// and based on user´s election will call the desired method

	// Creating Construction that will
	// Initialize the Object Employee from DAO
	public EmployeeController() throws ServletException, IOException, ClassNotFoundException {
		super();
		employee_dao = new EmployeeDAOImplementation();
	}


	// DO GET Method
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Declaring variables
		action = request.getParameter("action");

		// defining the method to call based on the type of action

			// IF Create
			if (action.equalsIgnoreCase("create")) {
	
				create_employee(request,response);
				retreive_employees(request,response);
				
				RequestDispatcher view = request.getRequestDispatcher(LIST_OF_EMPLOYEES);
				view.forward(request, response);
			}
			//IF Search
			else if (action.equalsIgnoreCase("search")) {		
				search_employee(request,response);
				RequestDispatcher view = request.getRequestDispatcher(EDIT);
				view.forward(request, response);
			}
	
			//IF Delete
			else if (action.equalsIgnoreCase("delete")) {	
				delete_employee(request,response);
				RequestDispatcher view = request.getRequestDispatcher(LIST_OF_EMPLOYEES);
				view.forward(request, response);
			
			// IF Edit
			} else if (action.equalsIgnoreCase("edit")) {
				edit_employee(request,response);
				retreive_employees(request,response);
				RequestDispatcher view = request.getRequestDispatcher(LIST_OF_EMPLOYEES);
				view.forward(request, response);
		
			// IF List of Students
			} else if (action.equalsIgnoreCase("List_of_employees")) {
				retreive_employees(request,response);
				RequestDispatcher view = request.getRequestDispatcher(LIST_OF_EMPLOYEES);
				view.forward(request, response);
			}
			

		}

	// DO POST Method
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}
	
	public void create_employee(HttpServletRequest request, HttpServletResponse response) {
		// Declaring variables

				String firstName = request.getParameter("first_name");
				String lastName = request.getParameter("last_name");
				String employeeID = request.getParameter("employee_id");
				String hireDate = request.getParameter("hire_date");
				String email = request.getParameter("email");
				String role = request.getParameter("role");
				String password = request.getParameter("password");

				// Creating and setting employee object
				Employee employee = new Employee();
				employee.setFirstName(firstName);
				employee.setLastName(lastName);
				employee.setEmployeeID(employeeID);
				employee.setHireDate(hireDate);
				employee.setEmail(email);
				employee.setRole(role);
				employee.setPassword(password);
				
		
				employee_dao.insertEmployee(employee);// inserting employee object previously created
	}

	public void edit_employee(HttpServletRequest request, HttpServletResponse response) {
		// Declaring variables
				String firstName = request.getParameter("first_name");
				String lastName = request.getParameter("last_name");
				String employeeID = request.getParameter("employee_id");
				String hireDate = request.getParameter("hire_date");
				String email = request.getParameter("email");
				String role = request.getParameter("role");
				String password = request.getParameter("password");
		
				employee_dao.updateEmployee(employeeID,firstName,lastName,hireDate,email,role,password);// Updating employee object previously created
	}
	
	public void search_employee(HttpServletRequest request, HttpServletResponse response) {
		String employeeID = request.getParameter("employee_id");//Retrieving the value from the web form 
		
		request.setAttribute("employee", employee_dao.readEmployee(employeeID));//showing the employee selected
	}	

	public void delete_employee(HttpServletRequest request, HttpServletResponse response) {
		String employeeID = request.getParameter("employee_id");//Retrieving the value from the web form 
	
		employee_dao.deleteEmployee(employeeID);
		request.setAttribute("Employees", employee_dao.retreiveEmployees());
	}

	public void retreive_employees(HttpServletRequest request, HttpServletResponse response) {
		
		ArrayList<Employee> employees; // Creating List of employees to keep the objects retrieved from method DAO

		employees = employee_dao.retreiveEmployees(); // fetching employee objects object from DataBase into ArrayList
		request.setAttribute("Employees", employees);// setting array in the request
		
	}
	
	
	
	
	
	

}