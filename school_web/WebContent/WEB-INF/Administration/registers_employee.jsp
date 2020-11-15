<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="CSS/Styles.css" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>Registers of Employee</title>
</head>

<body>
	<div class="sidenav">
		<button class="dropdown-btn">Students</button>
		<div class="dropdown-container">
			<a href="student.jsp">Create a student</a> <a
				href="StudentController?action=List_of_students">List of
				students</a> <a href="registers_student.jsp">Registers of <br>
				a student
			</a>
		</div>

		<button class="dropdown-btn">Courses</button>
		<div class="dropdown-container">
			<a href="course.jsp">Create a course</a> <a
				href="CourseController?action=List_of_courses">List of courses</a> <a
				href="registers_course.jsp">Registers of <br> a course
			</a>
		</div>

		<button class="dropdown-btn">Employees</button>
		<div class="dropdown-container">
			<a href="employee.jsp">Create a <br>employee
			</a> <a href="EmployeeController?action=List_of_employees">List of
				employees</a> <a href="registers_employee.jsp">Registers of <br>
				a employee
			</a>
		</div>

		<a href="AdministrationController?action=retreive_registers">Registers</a>
	</div>


	<div class="main">
	<div class="main-info">
		<form method="post">
			<table>

				<tr>
					<td>
						<h2>Please introduce the employee ID:</h2>
					</td>
					<td><input type="text" name="employee_id"
						value="${employee.employeeID}" required /></td>
					<td><input type="submit" value="Submit"></td>
					<td><input type="hidden" name="action"
						value="AdministrationController?retreive_employee_registers"></td>
				</tr>

				<tr>
					<td><b>First name :</b> <c:out value="${employee.firstName}" />
					</td>
				</tr>

				<tr>
					<td><b>Last name :</b> <c:out value="${employee.lastName}" />
					</td>
				</tr>

				<tr>
					<td><b>Hire Date : </b> <c:out value="${employee.hireDate}" />
					</td>
				</tr>

				<tr>
					<td><b>Email : </b> <c:out value="${employee.email}" /></td>
				</tr>

				<tr>
					<td><b>Role : </b> <c:out value="${employee.role}" /></td>
				</tr>


			</table>
			
			<div style="overflow-x:auto;" class="display-info-table">
			<table>
				<thead>
					<tr>
						<th>Student ID</th>
						<th>Student Name</th>
						<th>Schedule</th>
						<th>Course Name</th>
						<th>Course ID</th>
						<th>Classroom</th>
						<th>Start Date</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${Registers_employee}" var="registers_employee">
						<tr>
							<td>${registers_employee.student.studentID}</td>
							<td>${registers_employee.student.firstName}
								${registers_employee.student.lastName}</td>
							<td>${registers_employee.schedule}</td>
							<td>${registers_employee.course.courseName}</td>
							<td>${registers_employee.course.courseID}</td>
							<td>${registers_employee.course.classRoom}</td>
							<td>${registers_employee.course.startDate}</td>

						</tr>
					</c:forEach>
				</tbody>
			</table>
			</div>
			
		</form>

		<p>
			<a href="EmployeeController?action=List_of_employees">Back</a>
		</p>
		</div>

	</div>



	<script>
/* Loop through all dropdown buttons to toggle between hiding and showing its dropdown content - This allows the user to have multiple dropdowns without any conflict */
var dropdown = document.getElementsByClassName("dropdown-btn");
var i;

for (i = 0; i < dropdown.length; i++) {
  dropdown[i].addEventListener("click", function() {
  this.classList.toggle("active");
  var dropdownContent = this.nextElementSibling;
  if (dropdownContent.style.display === "block") {
  dropdownContent.style.display = "none";
  } else {
  dropdownContent.style.display = "block";
  }
  });
}
</script>


</body>
</html>