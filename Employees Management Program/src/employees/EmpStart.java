package employees;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;

import module.EmpDataSet;
import module.EmpVO;

public class EmpStart {
	Scanner scan = new Scanner(System.in);

	public EmpStart() {
		// 현재 등록되어있는 사원목록
		EmpDataSet.dataSet(); // 초기 데이터 셋팅
		
		// do while 로 반복실행
		do {
			try {
				System.out.print(menu());
				//String inMenu = scan.nextLine(); // 숫자 다음 엔터를 인식시키기 위해 nextInt 가 아닌 nextLine
				int inMenu = Integer.parseInt(scan.nextLine());
				if(inMenu==5) { // 종료
					empStop();
				}else if(inMenu==1){ // 사원목록(모든사원)
					empListOutput();
				}else if(inMenu==2) { // 사원등록
					empInsert();
				}else if(inMenu==3) { // 사원수정
					empEdit();
				}else if(inMenu==4){ // 사원삭제
					empDel();
				}else if(inMenu==6) { // 사원 이름으로 검색
					getNameSearch();
				}else {
					throw new Exception();
				}
			}catch(Exception e) {
				System.out.println("Invalid menu.");
			}
			
		}while(true);
	}
	// 사원검색
	public void getNameSearch() {
		System.out.print("Enter Employee Name = ");
		String searchName = scan.nextLine();
		
		// searchName 이 vo 에 있는지 확인하기
		titlePrint(); // 제목출력
		// vo
		Collection<EmpVO> list = EmpDataSet.empList.values();
		Iterator<EmpVO> ii = list.iterator();
		
		int cnt = 0;
		while(ii.hasNext()) {
			EmpVO v = ii.next(); // 끄집어내기
			if(v.getEmpName().equals(searchName)) {
				System.out.println(v.toString());
				cnt++;
			}
		}
		System.out.println("Found "+ cnt + " results.");

	}
	
	
	// 사원삭제
	public void empDel() {
		System.out.print("Enter Employee ID = ");
		int empno = Integer.parseInt(scan.nextLine());
		EmpDataSet.empList.remove(empno); // 객체지워짐
		System.out.println("Employee ID "+empno+" is deleted.");
	}
	
	// 사원수정
	public void empEdit() {
		// 어떤사원을 수정할것인지 입력받는다.
		System.out.print("Enter Employee ID = ");
		int editEmpno = Integer.parseInt(scan.nextLine());
		// 어떤정보를 수정할것인지 입력받는다.
		System.out.print("Edit [1.Department, 2.Telephone] = ");
		String editMenu = scan.nextLine();
		
		// 1번이면 부서, 2번이면 연락처 수정
		if(editMenu.equals("1")) { // 부서 수정
			departmentEdit(editEmpno);
		}else if(editMenu.equals("2")) { // 연락처 수정
			telEdit(editEmpno);
		}else {
			System.out.println("Invalid menu.");
		}
		
	}
	// 부서명 수정 메소드
	public void departmentEdit(int empno) {
		EmpVO vo = EmpDataSet.empList.get(empno); // 수정할 사원번호 가져오기
		System.out.print("Enter new department = ");
		String editDepartName = scan.nextLine();
		vo.setDepartment(editDepartName); // 부서명이 변경됨.
		System.out.println(vo.getEmpName()+"'s department is updated to " + vo.getDepartment() + ".");
		
	}
	// 연락처 수정 메소드
	public void telEdit(int empno) {
		EmpVO vo = EmpDataSet.empList.get(empno);
		System.out.print("Enter new telephone = ");
		vo.setTel(scan.nextLine());
		System.out.println(vo.getEmpName()+"'s telephone is updated to "+ vo.getTel() + ".");
	}
	
	// 사원목록
	public void empListOutput() {
		titlePrint(); // 제목출력
		// 앱의 모든 value 를 (vo 객체) 를 구하여 목록을 출력한다.
		Collection<EmpVO> emp = EmpDataSet.empList.values();
		Iterator<EmpVO> i = emp.iterator();
		
		while(i.hasNext()) {
			EmpVO v = i.next();
			System.out.println(v.toString());
		}
		
	}
	
	public void titlePrint() { // 사원목록 제목출력
		System.out.println("EmpID\tEmpName\tDepartment\tTelephone");
	}
	
	// 사원등록
	public void empInsert() {
		// 입력을 받아서 사원한명의 정보를 넣기위해 만든 empVO 넘기
		EmpVO vo = new EmpVO(); // 입력받은 사원정보를 저장할 vo 객체 생성
		// EmployeeID
		System.out.print("Employee ID = ");
		vo.setEmpno(Integer.parseInt(scan.nextLine())); // vo 의 setter 호출, 문자로 받아서 숫자로 변환
		// EmployeeName
		System.out.print("Employee Name = ");
		vo.setEmpName(scan.nextLine());
		// Department
		System.out.print("Department = ");
		vo.setDepartment(scan.nextLine());
		// Telephone
		System.out.print("Telephone = ");
		vo.setTel(scan.nextLine());
		
		// 컬렉션에 vo를 추가 (== 사원 한명 추가)
		EmpDataSet.empList.put(vo.getEmpno(), vo);
		System.out.println("Added a new employee.");
	}
	
	// 종료
	public void empStop() {
		try {
			// 사원정보가 있는 empList를 파일로 저장하고 프로그램 종료
			File f = new File("/Users/sanghyeop/testFolder", "employee.txt");
			FileOutputStream fos = new FileOutputStream(f);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(EmpDataSet.empList);
			
			oos.close(); // 쓰기가 끝나면 close
			fos.close();
			
		}catch(Exception e) {
			System.out.println("Exception occured when saving employees in a file! " + e.getMessage());
			e.printStackTrace();
		}
		System.exit(0);
	}
	
	public String menu() {
		return "Menu [1.Employee List, 2.Add, 3.Edit, 4.Delete, 5.End, 6.Search] = ";
	}

	public static void main(String[] args) {
		new EmpStart();

	}

}
