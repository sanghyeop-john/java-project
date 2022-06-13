package basic02_api;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class EmailCheckEx {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		// 1. 한번실행하고 반복해야 하니까 
		do {
			System.out.print("Email = ");
			String email = scan.nextLine(); // 3.이메일 문자열 받기 
			
			// 4. 이메일이 정상인지 아닌지 판단하기
			// @ 있어야 한다 / . 있어야 한다 / @. 붙어있으면 안된다. / .@ 붙어있으면 안된다. / @ 앞 3글자이상 허용 
			int atMark = email.indexOf("@"); // 4-1.
			int point = email.indexOf("."); // . 이 왼쪽에 있는가 (point<atMark) || @ 과 . 이 너무 붙어 있지는 않은가 (Math.abs(atMark-point)<=2)  
			
			
			if(atMark<3 || point<atMark || Math.abs(atMark-point)<=2 ) { // 4-0. && 4-2
				System.out.println(email+"은 잘못된 메일입니다.");
			} else { // 5. 정상 이메일 
				// split(), substring(), StringTokenizer 3가지 방법을 사용할 수 있다.
				
				
				// split() 으로 하는 방법 (가장 많이 사용하는 방법)
				String emailSplit[] = email.split("@");
				System.out.println("아이디 = " + emailSplit[0]);
				System.out.println("도메인 = " + emailSplit[1]);
				
				/*
				// substring() 으로 할때는 위의 atMark 에서 구한 @의 위치를 이용한다.
				String id = email.substring(0, atMark); // 처음 글자부터 @전까지
				String domain = email.substring(atMark+1); // @ 다음 글자부터 뒤 
				System.out.println("ID = " + id);
				System.out.println("DOMAIN = " + domain);
				*/
				
				/*
				// StringTokenizer 로 하는 방법 
				StringTokenizer emailObj = new StringTokenizer(email, "@");
				String id = emailObj.nextToken();
				String domain = emailObj.nextToken();
				System.out.println("Id = " + id);
				System.out.println("Domain = " + domain);
				*/
				
			}
			System.out.println("계속하시겠습니까(y:예, n:아니오)?"); // 6.
			if(!scan.nextLine().equals("y")) { // 7. 
				System.out.println("The End...");
				break;
			}
			
		} while(true); // 2. 
		
	}

}
/*
 실행결과
 
 이메일 = lsh9014@gmail.com
 아이디 = lsh9014
 도메인 = gmail.com
 다시하시겠습니까(y.예, n.아니오)?y
 
 이메일 = lsh9014.gmail.com
 잘못된 이메일 주소입니다.
 다시하시겠습니까(y.예, n.아니오)?y
 
 */



