import java.util.Calendar;
import java.util.Scanner;

public class CalendarTask {

	public static void main(String[] args) {
		// 스캐너 새로운 객체 생성
		Scanner scan = new Scanner(System.in);
		// 캘린더 메소드로 객체 구하기
		Calendar cal = Calendar.getInstance();
		
		// 년도 입력
		System.out.print("년도 = ");
		int yearNum = scan.nextInt();
		// 월 입력
		System.out.print("월 = ");
		int monthNum = scan.nextInt();
		
		
		// header 년월 출력 
		System.out.printf("\t\t    %d년 %d월\n", yearNum, monthNum);
		// header 요일 출력
		System.out.println("일\t월\t화\t수\t목\t금\t토");
		
		
		// 입력받은 날짜로 변경하기  
		cal.set(yearNum, monthNum-1, 1);
		
		
		// Calendar 객체에서 1일의 요일 얻어오기 
		int startDow = cal.get(Calendar.DAY_OF_WEEK);
		// 입력받은 달의 마지막 날
		int lastDate = cal.getActualMaximum(Calendar.DATE);
		
		
		// 해당 월 1일의 요일전까지 공백처리 
		for(int i=1; i<startDow; i++) {
			System.out.print("\t");
		}
		
		// 날짜 출력
		for(int i=1; i<=lastDate; i++) {
			System.out.print(i+"\t");
			// 매주 줄바꿈하기
			if(startDow%7==0)
				System.out.println();
			startDow ++;
		}
	
	}

}
