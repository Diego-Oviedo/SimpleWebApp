<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="CSS/Styles.css" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>Registration</title>
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
		<form method="post" action="AdministrationController">
			<table>
				<tr>
					<td>Student : <select name="student_id">
							<c:forEach items="${Students}" var="students">
								<option value="${students.studentID}">${students.studentID}
									- ${students.firstName} ${students.lastName}</option>
							</c:forEach>
					</select>
				</tr>
				<tr>
					<td>Course : <select name="course_id">
							<c:forEach items="${Courses}" var="courses">
								<option value="${courses.courseID}">${courses.courseID}
									- ${courses.courseName}</option>
							</c:forEach>
					</select>
				</tr>
				<tr>
					<td>Teacher : <select name="employee_id">
							<c:forEach items="${Employees}" var="employees">
								<option value="${employees.employeeID}">${employees.employeeID}
									- ${employees.firstName} ${employees.lastName}</option>
							</c:forEach>
					</select>
				</tr>
				<tr>
					<td>Schedule : <select name="schedule">
							<option value="DAY">Day</option>
							<option value="NIGHT">Night</option>
					</select>
				</tr>
				<tr>
					<td><input type="submit" value="Submit"></td>
					<td><input type="hidden" name="action" value="create"></td>
				</tr>

			</table>

			<div style="overflow-x:auto;" class="display-info-table" >
			<table>
				<thead>
					<tr>
						<th>Student Id</th>
						<th>Employee Id</th>
						<th>Course Id</th>
						<th>Schedule</th>
					</tr>
				</thead>

				<tbody>
					<c:forEach items="${Registers}" var="registers">
						<tr>
							<td><c:out value="${registers.student_id}" /></td>
							<td><c:out value="${registers.employee_id}" /></td>
							<td><c:out value="${registers.course_id}" /></td>
							<td><c:out value="${registers.schedule}" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			</div>
		</form>

		<br> <br>

		<form method="post" action="AdministrationController">
			<table>
				<tr>
					<td><b>Insert the ID [Course | Student | Employee]<br>
							in order to delete its registers
					</b> <input type="text" name="ID"></td>
				</tr>
				<tr>
					<td><input type="submit" value="Submit"></td>
					<td><input type="hidden" name="action" value="delete"></td>
				</tr>

			</table>
		</form>



		<p>
			<a href="Index.jsp">Back</a>
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