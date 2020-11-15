package dao_interface;

import java.sql.ResultSet;
import java.util.ArrayList;
import model.*;


public interface AdministrationDAOInterface {
	public void insertRegister(Administration register);
	public void deleteRegister(String ID);
	public ArrayList<Administration> retreive_administration_registers();
	public ArrayList<Administration> retreive_student_register(String student_id);	
	public ArrayList<Administration> retreive_employee_register(String employee_id);
	public ArrayList<Administration> retreive_course_register(String course_id);
}
