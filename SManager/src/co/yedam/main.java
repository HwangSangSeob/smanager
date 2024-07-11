package co.yedam;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd E");
        System.out.println(df.format(cal.getTime()));
        System.out.println("SU MO TU WE TH FR SA");

        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        
        System.out.println(cal.get(Calendar.MONTH) + 1); // 월 구하기
        
        cal.set(Calendar.DATE, 1);
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        
        for (int i = 1; i < dayOfWeek; i++) {
            System.out.print("   ");
        }
        for (int i = 1; i <= lastDay; i++) {
        	// i : 일자 데이터 11
        	// 메서드(방이름, 일자) 
            System.out.printf("%02d ", i);
            if (dayOfWeek % 7 == 0) {
                System.out.println();
            }
            dayOfWeek++;
        }
	}

}
