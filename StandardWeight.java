import java.util.Scanner;

public class StandardWeight {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		// 1. 기본 데이터 구하기
	
		
		// 변수 생성 
		int age, sex;
		double height, weight, stdWeight, weightScore;
		String type = "";
		
		// 나이 입력
		System.out.print("나이 = ");
		age = scan.nextInt();	
		// 성별 입력 
		System.out.print("성별(1:남성,2:여성) = ");
		sex = scan.nextInt();
		// 키 입력
		System.out.print("키 = ");
		height = scan.nextDouble();
		// 현재체중 입력
		System.out.print("현재체중 = ");
		weight = scan.nextDouble();
		
		/*
		// 조건문 표준체중 계산 
		if(age<=35 && sex==1) { // 35세 이하 남성 
			stdWeight = (height-100)*0.90;
		} else if(age<=35 && sex==2) { // 35세 이하 여성
			stdWeight = (height-100)*0.85;
		} else if(age>=36 && sex==1) { // 35세 이상 남성 
			stdWeight = (height-100)*0.95;
		} else { // 35세 이상 여성 
			stdWeight = (height-100)*0.90;
		}
		*/
		
		// 조건문 표준체중 계산 
		if(age<=35 && sex==2) { // 35세 이하 여성 
			stdWeight = (height-100)*0.85;
		} else if(age>=36 && sex==1) { // 35세 이상 남성 
			stdWeight = (height-100)*0.95;
		} else { // 35세 이하 남성 && 35세 이상 여성 
			stdWeight = (height-100)*0.90;
		}
		
		
		// 표준체중 지수 계산 
		weightScore = (weight/stdWeight)*100;
		
		// 조건문 표준체중지수 평가기준
		if(weightScore>=126) {
			type = "비만형";
		} else if(weightScore>=116) {
			type = "조금 비만형";
		} else if(weightScore>=96) {
			type = "표준형";
		} else if(weightScore>=86) {
			type = "조금 마른형";
		} else {
			type = "마른형";
		}
		
	
		// 3. 결과 출력 
		System.out.printf("표준체중 = %.2f\n", stdWeight);
		System.out.printf("당신의 표준체중지수는 %f으로 %s입니다.", weightScore, type);
		

	}

}
