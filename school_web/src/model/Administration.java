package model;

public class Administration {
	Course course;
	Student student;
	Employee employee;
	
	String course_id;
	String schedule;
	String student_id;
	String employee_id;
	
	//Constructors
	
	public Administration() {
		super();
	}

	public Administration(String course_id,String schedule, String student_id, String employee_id) {
		super();
		this.course_id = course_id;
		this.schedule = schedule;
		this.student_id = student_id;
		this.employee_id = employee_id;
	}
	
	//Setters

	public void setCourse(Course course) {
		this.course = course;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}

	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}
	
	
	//Getters

	public Course getCourse() {
		return course;
	}

	public Student getStudent() {
		return student;
	}

	public Employee getEmployee() {
		return employee;
	}

	public String getCourse_id() {
		return course_id;
	}


	public String getSchedule() {
		return schedule;
	}

	public String getStudent_id() {
		return student_id;
	}

	public String getEmployee_id() {
		return employee_id;
	}

	@Override
	public String toString() {
		return "Administration [course =" + course + ", student=" + student + ", employee=" + employee + ", course_id="
				+ course_id + ", schedule=" + schedule + ", student_id=" + student_id + ", employee_id=" + employee_id
				+ "]";
	}


}
