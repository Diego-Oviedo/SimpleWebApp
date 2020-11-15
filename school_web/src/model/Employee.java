package model;

public class Employee extends Person{
	
	private String employeeID;
	private String hireDate;
	private String email;
	private String role;
	private String password;
	
	
	//CONSTRUCTORS 
	public Employee() {
	}
	
	public Employee(String employeeID,String hireDate,String email,String role,String password) {
		super();
		this.employeeID = employeeID;
		this.hireDate = hireDate;
		this.email = email;
		this.role = role;
		this.password = password;
	}
	
	//SETTERS 
	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}
	
	public void setHireDate(String hireDate) {
		this.hireDate = hireDate;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	//GETTERS
	public String getEmployeeID() {
		return employeeID;
	}

	public String getHireDate() {
		return hireDate;
	}

	public String getEmail() {
		return email;
	}
	
	public String getRole() {
		return role;
	}

	public String getPassword() {
		return password;
	}
	
	//METHOD TO STRING

	public String toString() {
		return "Employee ID: " + employeeID + "\nHire date: " + hireDate + "\nE-mail=" + email ;
	}
	
	
}
