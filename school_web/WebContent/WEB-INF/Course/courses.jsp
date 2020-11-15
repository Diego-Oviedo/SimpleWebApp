<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="CSS/Styles.css"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<title>List of Course</title>
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
	<h1 class="display-info-title">List of courses</h1>
	
	<div style="overflow-x:auto;"  class="display-info-table"  >
    <table>
        <thead>
            <tr>
            	<th>Course Id</th>
                <th>Course name</th>
                <th>Start Date</th>
                <th>Classroom</th>
                <th colspan=3>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${Courses}" var="course">
                <tr>
                	<td><c:out value="${course.courseID}" /></td>
                    <td><c:out value="${course.courseName}" /></td>
                    <td><c:out value="${course.startDate}" /></td>
                    <td><c:out value="${course.classRoom}" /></td>
                
                    <td><a href="CourseController?action=search&course_id=<c:out value="${course.courseID}"/>"> Edit</a></td>
                    <td><a href="CourseController?action=delete&course_id=<c:out value="${course.courseID}"/>"> Delete</a></td>
                    <td><a href="AdministrationController?action=retreive_course_registers&course_id=<c:out value="${course.courseID}"/>">Registers of course</a></td>
                    
                </tr>
            </c:forEach>
        </tbody>
        
    </table>
    
        
    <p> 
    <a href="course.jsp">Add Course</a>
    <a href="Index.jsp">Back</a>
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