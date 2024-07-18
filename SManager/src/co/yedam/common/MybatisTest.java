package co.yedam.common;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import co.yedam.mapper.DataSource;
import co.yedam.mapper.StudentMapper;
import co.yedam.vo.StudentVO;

public class MybatisTest {
	public static void main(String[] args) {
		SqlSession sqlSession = DataSource.getInstance().openSession(true);//()안에 true를 넣어주면 자동 commit방식

		// mapper 인터페이스의 구현.
		// 왼쪽 인터페이스 = 오른쪽 구현 클래스
		StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
		
		// 학생 추가
		StudentVO student = new StudentVO();
		student.setStdNo("S2024-12");
		student.setStdName("테스트2");
		student.setStdPhone("010-1111-4444");

		int cnt = mapper.insertStudent(student);

		System.out.println("처리된 건수 : " + cnt);
//		sqlSession.commit(); // 수동으로 커밋하는 방법
		
		// 학생 수정
		student = new StudentVO();
		student.setStdNo("S2024-11");
		student.setStdPhone("010-1111-1111");
		cnt = mapper.updateStudent(student);

		System.out.println("처리된 건수 : " + cnt);

		// 학생 조회
		List<StudentVO> list = mapper.studentList();

		for (StudentVO svo : list) {
			System.out.println(svo);
		}

		System.out.println("OK");

	}
}
