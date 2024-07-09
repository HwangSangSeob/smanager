package co.yedam.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import co.yedam.vo.StudentVO;

public class AppTests {
	// 커넥션
	public static Connection getConn() {
		// 1) Oracle JDBC Driver 자바라이브러리.
		// 2) Connection 객체. 세션
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String driver = "oracle.jdbc.driver.OracleDriver";
		String user = "jsp";
		String pass = "jsp";
		Connection conn = null;
		try {
			Class.forName(driver); // 
			conn = DriverManager.getConnection(url, user, pass);
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("오라클 드라이버 없음.");
			e.printStackTrace();
			return null;
		}
		return conn;
	}

	// 실행 메서드
	public static void main(String[] args) {
		// 1) Oracle JDBC Driver 자바라이브러리.
		// 2) Connection 객체. 세션
		// 3) 쿼리를 실행하면 자동으로 commit 별도로 commit가 가능하다.

		// 입력.
		Scanner sc = new Scanner(System.in);

//		System.out.print("학생 번호 > ");
//		String sno = sc.nextLine();
//
//		System.out.print("학생 이름 > ");
//		String sname = sc.nextLine();
//
//		System.out.print("학생 연락처 > ");
//		String phon = sc.nextLine();
//
//		addStudent(sno, sname, phon); // 학생 쿼리 입력

		// 수정

		System.out.println("수정할 학생 정보를 입력하세요.");
		System.out.print("수정할 학생 번호 > ");
		String snoUp = sc.nextLine();

		System.out.print("수정할 학생 이름 > ");
		String nameUp = sc.nextLine();

		System.out.print("수정할 학생 연락처 > ");
		String phoneUp = sc.nextLine();

		System.out.print("수정할 학생 주소 > ");
		String addrUp = sc.nextLine();

		System.out.print("수정할 학생 생년월일 (예 : 1999-01-01) > ");
		String bDateUp = sc.nextLine();

//		modStudent(snoUp, bDateUp, addrUp);

		StudentVO std = new StudentVO();
		std.setStdNo(snoUp);
		std.setStdPhone(phoneUp);
		std.setStdName(nameUp);
		std.setAddress(addrUp);
		std.setBirthDate(bDateUp);
		modStu(std);

		// 삭제
//		System.out.println("수정할 학생 정보를 입력하세요.");
//		System.out.print("수정할 학생 번호 > ");
//		String snoDel = sc.nextLine();
//		
//		removeStudent(snoDel); // 삭제호출

		sc.close();

		System.out.println();

		search(); // 목록조회.

	}

	// 조회기능.
	public static void search() {
		try {
			Connection conn = getConn();
			Statement stmt = conn.createStatement(); // 실행할 쿼리를 넣어주면 쿼리를 해석해서 처리한 결과를 가져온다.
			ResultSet rs = stmt.executeQuery("SELECT * FROM tbl_student ORDER BY std_no"); // 쿼리 실행
			// [객체1, 객체2, 객체3]
			while (rs.next()) {
				System.out.println(rs.getString("std_no") + ", " + rs.getString("std_name") + ", "
						+ rs.getString("std_phone") + ", " + rs.getString("address"));
			}
			System.out.println("end of data.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}// 조회기능 끝.

	// 입력기능.
	public static void addStudent(String stdNo, String stdName, String phone) {
		Connection conn = getConn();
		String sql = "insert into tbl_student(std_no, std_name, std_phone)";
		sql += "VALUES ('" + stdNo + "', '" + stdName + "', '" + phone + "')";
		try {
			Statement stmt = conn.createStatement();
			int r = stmt.executeUpdate(sql); // insert, update, delete => 처리된 건수를 리턴해준다.
			System.out.println("처리된 건수는 " + r + "건.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}// 입력기능 끝.

	/*
	 * 
	 * update tbl_student set address = '대구시 대구로 대구아파트 2' where std_no = 'S2024-02';
	 */
	// 수정기능
	public static void modStudent(String stdNo, String birthDate, String address) {
		Connection conn = getConn();
		String sql = "UPDATE tbl_student ";
		sql += "SET birth_date = TO_DATE('" + birthDate + "', 'yyyy-mm-dd',";
		sql += " address = '" + address + "'";
		sql += " WHERE std_no = '" + stdNo + "'";

		try {
			Statement stmt = conn.createStatement();
			int r = stmt.executeUpdate(sql); // insert, update, delete => 처리된 건수를 리턴해준다.
			System.out.println("처리된 건수는 " + r + "건.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 수정기능
	public static void modStu(StudentVO svo) {
		Connection conn = getConn();
		String sql = "UPDATE tbl_student ";
		sql += "SET std_name = NVL('" + svo.getStdName() + "', std_name)";
		sql += " ,std_phone = NVL('" + svo.getStdPhone() + "', std_phone)";
		sql += " ,address = NVL('" + svo.getAddress() + "', address)";
		sql += " ,birth_date = NVL(TO_DATE('" + svo.getBirthDate() + "', 'yyyy-mm-dd'),birth_date)";
		sql += " WHERE std_no = '" + svo.getStdNo() + "'";

		try {
			Statement stmt = conn.createStatement();
			int r = stmt.executeUpdate(sql); // insert, update, delete => 처리된 건수를 리턴해준다.
			System.out.println("처리된 건수는 " + r + "건.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 삭제기능
	public static void removeStudent(String stdNo) {
		Connection conn = getConn();
		String sql = "DELETE FROM tbl_student ";
		sql += " WHERE std_no = '" + stdNo + "'";

		try {
			Statement stmt = conn.createStatement();
			int r = stmt.executeUpdate(sql); // insert, update, delete => 처리된 건수를 리턴해준다.
			System.out.println("처리된 건수는 " + r + "건.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
