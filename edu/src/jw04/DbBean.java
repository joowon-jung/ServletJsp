package jw04;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbBean  {

	///Field
	private String dburl = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	private String dbuser = "scott";
	private String dbpwd = "tiger";
	private String id;
	private String pwd;
	
	///Constructor
	public DbBean() {
	}
	
	///Method
	public void setId(String id) {
		this.id = id;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	// DB 접근. Data 검색하여 비교 후 true / false return
	public boolean getUser() {
		
		Connection con = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		
		try {
			
			// 1단계 Connection
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(dburl, dbuser, dbpwd);
						
			// 2단계 Statement
			pStmt = con.prepareStatement("SELECT id, pwd FROM users WHERE id = ?");
			pStmt.setString(1, id);
			
			// 3단계 ResultSet
			rs = pStmt.executeQuery();
			String str = null;
			
			if (rs.next()) { // id가 맞으면 결과 출력할 것이 있을 것 
				str = rs.getString("pwd");
				//==> debug :: console 확인
				System.out.println("db에서 확인한 id, pwd==>: "+id+" : "+str);
			} else { // id가 틀리면 출력할 결과가 없을 것
				//==> debug :: console 확인
				System.out.println("db에 client에서 입력한 <"+id+"> 에 해당하는 자료가 없습니다.");
			}
			
			// 회원 유무를 확인하고, 회원이면 true, 아니면 false return 
			if (str != null && str.equals(pwd)) {
				return true;
			} else {
				return false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return false; // 여기에 왜 return 문이 존재해야 하는가?
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pStmt != null) {
				try {
					pStmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	

}