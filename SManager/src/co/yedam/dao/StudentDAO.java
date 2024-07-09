package co.yedam.dao;

import java.sql.SQLException;
import java.sql.Statement;
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
	
	// 수정

}// end class
