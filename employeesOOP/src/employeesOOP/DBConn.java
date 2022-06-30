package employeesOOP;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConn {

	// 드라이버로딩 메소드
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	// 변수 
	// protected 로 외부에서 사용 안되고 반드시 상속받아서 쓰게만 허용
	protected Connection conn;
	protected PreparedStatement pstmt;
	protected ResultSet rs;
	protected String sql = null;
	protected final String URL = "jdbc:mysql://@127.0.0.1/multi";
	protected final String DB_ID = "root";
	protected final String DB_PWD = "djftlrn9";
	
	// DB 연결 메소드
	protected void getConn() {
		try {
			conn = DriverManager.getConnection(URL, DB_ID, DB_PWD);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// DB 닫기 메소드 
	protected void getClose() {
		try {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
