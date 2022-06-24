package module;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;

public class EmpDataSet {
	// 사원정보를 저장할 컬렉션을 선언
	public static HashMap<Integer, EmpVO> empList = new HashMap<Integer, EmpVO>();

	public EmpDataSet() {
		
	}
	public static void dataSet() {
		// 파일에 있는 Object 를 구하여 HashMap 에 셋팅
		try {
			
			File f = new File("/Users/sanghyeop/testFolder", "employee.txt");
			if(f.exists()) { // 파일이 있을때만 작업 가능
				FileInputStream fis = new FileInputStream(f); // FileInputStream은 File 객체가 필요.
				ObjectInputStream ois = new ObjectInputStream(fis); //InputStream 객체를 넣어줌.
				
				EmpDataSet.empList = (HashMap)ois.readObject(); // 초기회원목록
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
