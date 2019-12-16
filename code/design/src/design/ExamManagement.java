package design;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class ExamManagement {
	private static final String URL = "jdbc:oracle:thin:@localhost:1600:xe";
	private static final String USER_KNU = "knu";
	private static final String USER_PASSWD = "comp322";
	private Connection conn = null; // Connection object
	private Statement stmt = null; // Statement object
	private String sql = ""; // an SQL statement
	private ResultSet res;
	private Scanner scanner = new Scanner(System.in);

	public ExamManagement() {

		try {
			// Load a JDBC driver for Oracle DBMS
			Class.forName("oracle.jdbc.driver.OracleDriver");
// Get a Connection object
			System.out.println("Success!");
		} catch (ClassNotFoundException e) {
			System.err.println("error = " + e.getMessage());
			System.exit(1);
		}

// Make a connection
		try {
			conn = DriverManager.getConnection(URL, USER_KNU, USER_PASSWD);
		} catch (SQLException ex) {
			System.err.println("Cannot get a connection: " + ex.getMessage());
			System.exit(1);
		}
		try {
			conn.setAutoCommit(false); // auto-commit disabled
			// Create a statement object
			stmt = conn.createStatement();

		} catch (SQLException ex2) {
			System.err.println("sql error = " + ex2.getMessage());
			System.exit(1);
		}

	}

	void InsertExam() {
		String cnum;
		String classname = null;
		String date;
		String stime, ftime;
		String floor, classroom = null;
		int exam_id = 0;
		// �ش� ������ ���� ��� ������
		sql = "select Cnumber, Cname, Course_room, Csdate, Cfdate, Hnumber from Course, classhour where Pnum = 1 and Conum = Cnumber";
		try {
			res = stmt.executeQuery(sql);
			System.out.println("Course_Number      Course_Name    Course_room      Start     End       Hours");
			while (res.next()) {
				System.out.println(res.getString("Cnumber") + "             " + res.getString("Cname") + " "
						+ res.getString("Course_room") + " " + res.getString("Csdate") + " " + res.getString("Cfdate")
						+ " " + res.getString("Hnumber"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("���� ��� ã�µ� ������");
		}

		// � ���� �� ���� �Է¹ޱ�
		System.out.print("���� �����ڵ带 �Է��ϼ���: ");
		cnum = scanner.next();

		sql = "select Cname from course where Cnumber = '" + cnum + "'";
		try {
			res = stmt.executeQuery(sql);
			res.next();
			classname = res.getString("Cname");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		System.out.print("������ �����ϼ���(MON TUE WED THU FRI SAT SUN): ");
		date = scanner.next();

		// �ش� ��¥ �ð���� ������
		sql = "select Cnum, Exam_room, Esdate, Efdate, Eday from exam where Eday = '" + date + "'";
		try {
			res = stmt.executeQuery(sql);
			System.out.println("Course_Number   Exam_room   Start   End   Day");
			while (res.next()) {
				System.out.println(res.getString("Cnum") + " " + res.getString("exam_room") + " "  + res.getString("esdate")
				 + " " + res.getString("efdate")  + " " + res.getString("eday"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("���ǽ��� �Է��ϼ���(�� ���ǽ�): ");
		floor = scanner.next();
		classroom = scanner.next();

		System.out.print("���� �ð��� �Է��㼼��(hh:mm): ");
		stime = scanner.next();
		stime = stime + ":00";
		System.out.print("��ħ �ð��� �Է��ϼ���(hh:mm): ");
		ftime = scanner.next();
		ftime = ftime + ":00";
		
		System.out.println(stime +"  " + ftime);

		sql = "select count(Enumber) from exam";
		try {
			res = stmt.executeQuery(sql);
			res.next();
			exam_id = res.getInt(1);
			exam_id++;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("���� ���̵� ã�µ� ������");
		}

		sql = "INSERT INTO EXAM VALUES (" + exam_id + ", " + cnum + ", '" + floor + " " + classroom + "' , TO_date('"
				+ stime + "', 'HH24:MI:SS'), TO_date('" + ftime + "', 'HH24:MI:SS'), '" + date + "', '')";
		System.out.println(sql);
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("�ش� �ð����� ������ ĥ �� �����ϴ�.");
		}

		System.out.println(classname + " (" + cnum + ")" + "�� ����ð��� " + date + " " + stime + "~" + ftime + " " + classroom + "���� �����Ǿ����ϴ�.");

	}

}