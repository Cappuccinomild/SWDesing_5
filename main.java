import java.sql.SQLException;
import java.util.Scanner;

public class main {

	public static void main(String[] args) throws SQLException {
		Scanner scanner = new Scanner(System.in);
		String ID = "0000";
		String pwd = "0000";
		
		ProfessorUI professorui = new ProfessorUI(new Professor(), ID, pwd, new ExamManagement());
		
		professorui.ProfessorMenu();
		professorui.ProfessorSelect(Integer.parseInt(scanner.nextLine()));
		
		StudentUI studentui = new StudentUI(new Student(), "2017112999", "1234");
		
		studentui.StudentMenu();
		studentui.StudentSelect(Integer.parseInt(scanner.nextLine()));
		
		
		
	}

}
