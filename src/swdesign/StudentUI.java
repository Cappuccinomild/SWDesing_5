package swdesign;

import java.sql.SQLException;
import java.util.ArrayList;

public class StudentUI {
	
	Student student;
	boolean valid;
	
	
	public StudentUI(Student student, String ID, String pwd) {
		this.student = student;
		valid = this.student.logIn(ID, pwd);
		this.student.setLecture();
		this.student.setExam();
	}
	public void StudentMenu()
	{
		System.out.println("1.���� ���� ��ȸ");
		System.out.println("2.���� ��ȸ");
		
	}
	
	public ArrayList<String> StudentSelect(int command) throws SQLException
	{
		switch(command) {
		case 1: System.out.println("�������� ����");
			return student.getLecture();
		
		case 2: System.out.println("���� ��ȸ");
			return student.getExam();
		case 3:
			return student.getETime();
		}
		return null;
	}
}

