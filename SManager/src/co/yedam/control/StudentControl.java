package co.yedam.control;

import java.util.List;
import java.util.Scanner;

import co.yedam.dao.StudentDAO;
import co.yedam.vo.StudentVO;

/*
 * 사용자입력을 가이드, 처리된 결과 출력. 
 */

public class StudentControl {
	// 필드
	Scanner sc = new Scanner(System.in); // 스캐너
	StudentDAO sdao = new StudentDAO(); // 학생관리 DAO

	// 생성자

	// 메소드
	public void run() {
		boolean isTrue = true;

		while (isTrue) {
			System.out.println("학생관리 프로그램 v 1.0\n");
			System.out.println("-------------------------------------");
			System.out.println("1. 학생목록 2. 등록 3. 수정 4. 삭제 5. 종료");
			System.out.println("-------------------------------------");
			System.out.print("선택 > ");
			int menu = Integer.parseInt(sc.nextLine());

			// 메뉴 선택 시 스위치문으로 반복
			switch (menu) {
			case 1:
				// 목록 조회
				studentList();
				break;
			case 2:
				addStudent();
				break;
			case 3:
				modStudent();
				break;
			case 4:
				delStudent();
				break;
			case 5:
				System.out.println("프로그램을 종료합니다!");
				isTrue = false;
			}
		}
	}// end run

	// 목록 조회
	void studentList() {
		List<StudentVO> students = sdao.selectList();
		System.out.println("학생번호 \t\t 학생이름 \t\t 연락처");
		System.out.println("---------------------------------------------");
		for (StudentVO svo : students) {
			System.out.println(svo.briefShow());
		}
	} // end studentList

	// 등록
	void addStudent() {
		System.out.print("학생 번호 > ");
		String sno = sc.nextLine(); // 로컬변수

		System.out.print("학생 이름 > ");
		String sname = sc.nextLine();

		System.out.print("학생 연락처 > ");
		String sphone = sc.nextLine();

		System.out.print("학생 주소 > ");
		String address = sc.nextLine();

		System.out.print("학생 생년월일 (예 : 1999-01-01) > ");
		String birthdate = sc.nextLine();

		StudentVO std = new StudentVO();
		std.setStdNo(sno);
		std.setStdName(sname);
		std.setStdPhone(sphone);
		std.setAddress(address);
		std.setBirthDate(birthdate);
		// 입력기능 호출
		if (sdao.insertStudent(std)) {
			System.out.println("저장 완료!");
		} else {
			System.out.println("처리중 예외발생!");
		}

	}// end addStudent

	// 수정
	private void modStudent() {
		System.out.print("학생 번호 > ");
		String sno = sc.nextLine(); // 로컬변수

		System.out.print("학생 이름 > ");
		String sname = sc.nextLine();

		System.out.print("학생 연락처 > ");
		String sphone = sc.nextLine();

		System.out.print("학생 주소 > ");
		String address = sc.nextLine();

		System.out.print("학생 생년월일 (예 : 1999-01-01) > ");
		String birthdate = sc.nextLine();

		StudentVO std = new StudentVO();
		std.setStdNo(sno);
		std.setStdName(sname);
		std.setStdPhone(sphone);
		std.setAddress(address);
		std.setBirthDate(birthdate);
		
		// 수정기능 호출
		if (sdao.updateStudent(std)) {
			System.out.println("수정 완료!");
		} else {
			System.out.println("처리중 예외발생!");
		}

	}// end modStudent

	// 삭제
	private void delStudent() {
		System.out.print("삭제할 학생 번호 > ");
		String sno = sc.nextLine(); // 로컬변수

		StudentVO std = new StudentVO();
		std.setStdNo(sno);
		
		// 삭제기능 호출
		if (sdao.deleteStudent(std)) {
			System.out.println("삭제 완료!");
		} else {
			System.out.println("처리중 예외발생!");
		}
		
	}// end delStudent

}// end class
