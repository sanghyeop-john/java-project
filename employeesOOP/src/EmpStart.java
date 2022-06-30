

import java.util.List;
import java.util.Scanner;

import employeesOOP.EmpDAO;
import employeesOOP.EmpVO;

public class EmpStart {
	Scanner scan = new Scanner(System.in);
	EmpDAO dao = new EmpDAO();

	public EmpStart() {
		start();
		
	}
	public void start() {
		
		do {
			try {
				String menu = menuShow();
				if(menu.equals("1")) { // 회원목록
					empList();
				}else if(menu.equals("2")) { // 회원등록
					empAdd();
				}else if(menu.equals("3")) { // 회원수정
					empEdit();
				}else if(menu.equals("4")) { // 회원삭제
					empDel();
				}else if(menu.equals("5")) { // 종료
					empClose();
				}else if(menu.equals("6")) { // 회원검색
					empSearch();
				}
			}catch(Exception e) {
				System.out.println("Invalid menu.");
			}
		}while(true);
		
	}
	// 회원검색
	// 검색은 단순히 쿼리 select 과 동일하지만 where 구문이 추가 된것이다.
	public void empSearch() {
		System.out.print("Search by username -> ");
		String searchWord = scan.nextLine();
		// 출력 
		empPrint(dao.empSelect(searchWord));
		
	}
	// 회원목록 메소드
	public void	empList() {
		// 회원목록 DB 에서 선택할 수 있는 메소드를 호출
		// EmpDAO dao = EmpDAO.getInstance();
		// EmpDAO dao = new EmpDAO(); // 이렇게도 가능
		String searchWord = null;
		empPrint(dao.empSelect(searchWord));

	}
	// 목록출력
	public void empPrint(List<EmpVO> list) {
		for(int i=0; i<list.size(); i++) {
			EmpVO vo = list.get(i);
			System.out.printf("%-6d %-12s %-10s %-16s %-20s\n", 
					vo.getMem_id(), vo.getUsername(), vo.getDepart(), vo.getPhone(), vo.getEmail());
		}
	}
	
	// 회원정보 삭제
	public void empDel() {
		System.out.print("Delete mem_id -> ");
		int mem_id = Integer.parseInt(scan.nextLine());
		// vo 객체 생성 없이
		// EmpDAO dao = new EmpDAO();
		int result = dao.empDelete(mem_id);
		if(result>0) {
			System.out.println(mem_id+" is deleted.");
		}else {
			System.out.println("Failed to delete.");
		}
		
	}
	
	// 회원정보 수정
	public void empEdit() {
		EmpVO vo = new EmpVO();
		
		// 수정할 회원번호 입력받기
		System.out.print("Edit mem_id -> ");
		vo.setMem_id(Integer.parseInt(scan.nextLine()));
		
		// 수정할 항목 입력받
		System.out.print("Edit menu [1.phone, 2.depart, 3.email] -> ");
		// EmpVO 에 변수 하나 (fieldName) 추가하고 Setter/Getter 생성
		String editField = scan.nextLine();
		
		if(editField.equals("1")) {
			vo.setFieldName("phone");
			System.out.print("Enter a new phone -> ");
		}else if(editField.equals("2")) {
			vo.setFieldName("depart");
			System.out.print("Enter a new depart -> ");
		}else if(editField.equals("3")) {
			vo.setFieldName("email");
			System.out.print("Enter a new email -> ");
			
		}
		vo.setPhone(scan.nextLine());
		
		// EmpDAO dao = EmpDAO.getInstance();
		// EmpDAO 의 empUpdate 에서 int 로 반환된 값으로
		int cnt = dao.empUpdate(vo);
		if(cnt>0) {
			System.out.println("Succesfuly updated to "+vo.getPhone()+".");
		}else {
			System.out.println("Failed to update.");
		}
		
	}
	
	// 회원등록 메소드
	public void empAdd() {
		// 입력받은 데이터를 VO 저장하기 위해서 객체를 생성
		EmpVO vo = new EmpVO();
		System.out.print("mem_id -> ");
		vo.setMem_id(Integer.parseInt(scan.nextLine()));
		
		System.out.print("username -> ");
		vo.setUsername(scan.nextLine());
		
		System.out.print("depart -> ");
		vo.setDepart(scan.nextLine());
		
		System.out.print("phone -> ");
		vo.setPhone(scan.nextLine());
		
		System.out.print("email -> ");
		vo.setEmail(scan.nextLine());
		
		//EmpDAO dao = new EmpDAO();
		int cnt = dao.empInsert(vo);
		if(cnt>0) {
			System.out.println(vo.getUsername()+" is successfully added.");
		}else {
			System.out.println("Failed to add.");
		}
		
	}
	

	
	
	// 종료 메소드
	public void empClose() {
		System.exit(0);
	}
	
	// 메뉴 출력 메소드
	public String menuShow() {
		System.out.print("Menu [1.Member List, 2.Add, 3.Edit, 4.Delete, 5.End, 6.Search] -> ");
		return scan.nextLine();
	}

	public static void main(String[] args) {
		new EmpStart();

	}

}
