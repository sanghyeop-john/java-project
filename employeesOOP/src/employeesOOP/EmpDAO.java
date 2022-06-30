package employeesOOP;

import java.util.ArrayList;
import java.util.List;

public class EmpDAO extends DBConn{

	public EmpDAO() {
		
	}
	public static EmpDAO getInstance() { // 클래스를 객체화하기 
		return new EmpDAO();
	}
	// 회원목록
	public List<EmpVO> empSelect(String searchWord) { // arraylist 에 담김 회원정보를 리턴
		List<EmpVO> list = new ArrayList<EmpVO>();
		try {
			getConn();
			
			sql = "select mem_id, username, depart, phone, email from member ";
					if(searchWord != null) {
						sql += " where username like ? ";
					}
					sql += " order by mem_id";
			
			// select mem_id, username, depart, phone, email from member order by mem_id
			// select mem_id, username, depart, phone, email from member where username like ? order by mem_id		
			pstmt = conn.prepareStatement(sql);
			
			if(searchWord!=null) {
				pstmt.setString(1,  "%"+searchWord+"%");
			}
			
			////
			rs = pstmt.executeQuery();
			while(rs.next()) {
				// 회원 한명을 VO에 담기
				EmpVO vo = new EmpVO();
				vo.setMem_id(rs.getInt(1));
				vo.setUsername(rs.getString(2));
				vo.setDepart(rs.getString(3));
				vo.setPhone(rs.getString(4));
				vo.setEmail(rs.getString(5));
				
				// VO를 ArrayList (collection) 에 담기
				list.add(vo);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getClose();
		}
		return list;  // ArrayList 에 담김 회원정보를 리턴
	}
	// 회원등록
	public int empInsert(EmpVO vo) {
		int result = 0;
		try {
			getConn();
			sql = "Insert into member(mem_id, username, depart, phone, email) "
					+ " values(?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getMem_id());
			pstmt.setString(2, vo.getUsername());
			pstmt.setString(3, vo.getDepart());
			pstmt.setString(4, vo.getPhone());
			pstmt.setString(5, vo.getEmail());
			
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			getClose();
		}
		return result;
		
	}
	
	// 회원수정
	public int empUpdate(EmpVO vo) {
		int result=0;
		try {
			getConn();
			sql = "update member set "+vo.getFieldName()+"=? where mem_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getPhone()); // 첫번째 물음표는 문자고 vo.getPhone 에 값이 들어있다.
			pstmt.setInt(2, vo.getMem_id()); // 두번째 물음표는 정수고 vo.getMem_id 에 값이 들어있다.
			
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getClose();
		}
		return result;
	}
	
	// 회원삭제
	public int empDelete(int mem_id) {
		int result=0;
		try {
			getConn();
			sql = "delete from member where mem_id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mem_id);
			
			result = pstmt.executeUpdate(); // 실행 후 리턴
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
