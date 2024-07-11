package co.yedam.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.yedam.vo.StudentVO;

/*
 * 목록(R), 등록(C), 수정(U), 삭제(D) : CRUD 
 * 주위 : DAO 메시지(System.out.println)가 없음.
 */

// DAO를 상속받았기 때문에 사용할 필요 없다.
public class StudentDAO extends DAO {
	// DB 처리하는 기능

	// 목록반환기능.
	public List<StudentVO> selectList() {
		// 쿼리
		String sql = "SELECT * FROM tbl_student ORDER BY std_no";
		List<StudentVO> list = new ArrayList<>(); // 선언만 함
		// 커넥션
		conn = getConn(); // DAO에서 상속받았기 때문에 사용가능하다.
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql); // select 쿼리 결과를 받을 때 (DAO에서 선언함)
			// 결과가 set 컬렉션이라서 next로 반복을 한다.
			while (rs.next()) {
				// List 타입이 StudentVO 이기 때문에
				StudentVO svo = new StudentVO();

				// 값 담기
				svo.setStdNo(rs.getString("std_no"));
				svo.setStdName(rs.getString("std_name"));
				svo.setStdPhone(rs.getString("std_phone"));
				svo.setAddress(rs.getString("address"));
				svo.setBirthDate(rs.getString("birth_date"));
				svo.setCreationDate(rs.getDate("creation_date"));

				// 최종적으로 list에 넣기
				list.add(svo); // 가져온 건수 만큼 담아 준다.
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	} // end selectList

	// 등록기능.
	public boolean insertStudent(StudentVO svo) {
		// 쿼리
		String sql = "insert into tbl_student(std_no, std_name, std_phone, address, birth_date)";
		sql += "VALUES (?, ?, ?, ?, ?)";
		// 커넥션
		conn = getConn(); // DAO에서 상속받았기 때문에 사용가능하다.
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, svo.getStdNo());
			psmt.setString(2, svo.getStdName());
			psmt.setString(3, svo.getStdPhone());
			psmt.setString(4, svo.getAddress());
			psmt.setString(5, svo.getBirthDate());
			int r = psmt.executeUpdate();
			if (r == 1) {
				return true; // 정상처리.
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false; // 비정상처리.

	}// end insertStudent

	// 수정기능.

	// 단건조회.(학생이 있는지 확인하기)
	public int selectExists(String sno) {
		// 쿼리
		String sql = "SELECT COUNT(1) FROM tbl_student";
		sql += " WHERE std_no = ?";
		// 커넥션
		conn = getConn(); // DAO에서 상속받았기 때문에 사용가능하다.
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, sno);
			rs = psmt.executeQuery();
			while (rs.next()) {
				return rs.getInt(1); // 1번은 가지고 온 첫번째 컬럼을 가지고 온다.
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;

	}

	// 실제 수정 기능.
	public boolean updateStudent(StudentVO svo) {
		// 쿼리
		String sql = "UPDATE tbl_student ";
		sql += "SET std_name = ? ";
		sql += ", std_phone = ? ";
		sql += ", address = ? ";
		sql += ", birth_date = ? ";
		sql += "WHERE std_no = ? ";
		// 커넥션
		conn = getConn(); // DAO에서 상속받았기 때문에 사용가능하다.
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, svo.getStdName());
			psmt.setString(2, svo.getStdPhone());
			psmt.setString(3, svo.getAddress());
			psmt.setString(4, svo.getBirthDate());
			psmt.setString(5, svo.getStdNo());
			int r = psmt.executeUpdate();
			if (r == 1) {
				return true; // 정상처리.
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false; // 비정상처리.

	}// end updateStudent

	// 삭제기능.
	public boolean deleteStudent(StudentVO svo) {
		// 쿼리
		String sql = "DELETE FROM tbl_student ";
		sql += "WHERE std_no = ? ";
		// 커넥션
		conn = getConn(); // DAO에서 상속받았기 때문에 사용가능하다.
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, svo.getStdNo());
			int r = psmt.executeUpdate();
			if (r > 0) {
				return true; // 정상처리.
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false; // 비정상처리.

	}// end deleteStudent

}// end class
