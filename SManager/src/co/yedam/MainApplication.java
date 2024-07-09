package co.yedam;

import co.yedam.control.StudentControl;

public class MainApplication {

	public static void main(String[] args) {
		// 프로그램의 시작.
		// 사용자의 입력을 받아서 하는 행위들은 컨트롤
		StudentControl scontrol = new StudentControl();
		scontrol.run();
		
	} // end main

} // end class
