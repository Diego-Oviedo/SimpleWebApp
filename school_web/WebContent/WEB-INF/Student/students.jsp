<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="CSS/Styles.css" />
<title>List of Students</title>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		<h1 class="display-info-title">List of students</h1>
		
		<div style="overflow-x:auto;" class="display-info-table">
		<table>
			<thead>
				<tr>
					<th>Student Id</th>
					<th>First name</th>
					<th>Last name</th>
					<th>Date of birth</th>
					<th>E-mail</th>
					<th colspan=2>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${Students}" var="student">
					<tr>
						<td><c:out value="${student.studentID}" /></td>
						<td><c:out value="${student.firstName}" /></td>
						<td><c:out value="${student.lastName}" /></td>
						<td><c:out value="${student.dateOfBirth}" /></td>
						<td><c:out value="${student.email}" /></td>
						<td><a
							href="StudentController?action=search&student_id=<c:out value="${student.studentID}"/>">
								Edit</a></td>
						<td><a
							href="StudentController?action=delete&student_id=<c:out value="${student.studentID}"/>">
								Delete</a></td>
						<td><a
							href="AdministrationController?action=retreive_student_registers&student_id=<c:out value="${student.studentID}"/>">Registers
								of student</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<p>
			<a href="student.jsp">Add Student</a> <a href="Index.jsp">Back</a>
		</p>
		</div>
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