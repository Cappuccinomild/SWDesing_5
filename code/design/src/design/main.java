package design;

import java.sql.*;
import java.util.*;

public class main {

	public static Scanner scanner;
	private static String ID = "";
	private static String pwd = "";

	public static void main(String[] args) throws SQLException {

		scanner = new Scanner(System.in);
		int command = 0;

		Scanner scanner = new Scanner(System.in);

		main_menu mm = new main_menu();

		boolean svalid = false;
		boolean pvalid = false;

		while (true) {

			// �α���
			boolean login_flag = false;

			while (!login_flag) {

				System.out.println("LOGIN");
				System.out.println("�α��� : 1 \t���� : 2");

				switch (command = Integer.parseInt(scanner.nextLine())) {

				// �α���
				case 1:
					System.out.println("�α���");

					System.out.print("ID : ");
					ID = scanner.nextLine();

					System.out.print("PASSWORD : ");
					pwd = scanner.nextLine();

					pvalid = mm.Professor_session(ID, pwd);
					
					System.out.println(pvalid);
					svalid = mm.Student_session(ID, pwd);

					System.out.println(svalid);
					// �α��� ��� Ȯ��
					if ((svalid && pvalid) || (!svalid && !pvalid)) {
						System.out.println("Unvalid ID");
						// command �Է��� �ٽ� �޴´�
						command = 0;
					} else { // valid �� ��� �״�� ���θ� �α��� while�� �� ������
						System.out.println("�α��� ����");
						login_flag = true;
					}
					break;
				// ���α׷� ����
				case 2:
					System.exit(0);
					break;

				}
			}

			if (pvalid) {// �����޴�
				while (login_flag) {
					mm.Call_menu();
					command = scanner.nextInt();
					
					if(command == 5) {
						System.out.println("�α׾ƿ��մϴ�.");
						System.exit(0);
					}
					mm.GetData(command);
				}

			} else if (svalid) {// �л��޴�
				while (login_flag) {
					mm.Call_menu();
					command = scanner.nextInt();
					
					if(command == 4) {
						System.out.println("�α׾ƿ��մϴ�.");
						System.exit(0);
					}
					mm.GetData(command);
				}
			}

		}

	}

}