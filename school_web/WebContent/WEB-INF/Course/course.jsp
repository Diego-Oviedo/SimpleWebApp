<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="CSS/Styles.css" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>Course</title>
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
		<h1>Course</h1>
		<form method="post" action="CourseController?action=create"
			name="Formcourse">
			<table align="left">
				<tr>
					<td>Course ID :</td>
					<td><input type="text" name="course_id" required /></td>
				</tr>
				<tr>
					<td>Course name :</td>
					<td><input type="text" name="course_name" /></td>
				</tr>
				<tr>
					<td>Start Date :</td>
					<td><input type="date" name="start_date" min="2000-01-01"
						max="2020-12-31" value="" /></td>
				</tr>
				<tr>
					<td>Classroom :</td>
					<td><input type="text" name="classroom" /></td>
				</tr>
				<tr>
					<td><input type="submit" value="Submit"></td>
					<td><a href="Index.jsp">Back</a></td>
				</tr>
			</table>
		</form>

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