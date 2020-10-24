package jw04;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

	///Field
	private String dburl = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	private String dbuser = "scott";
	private String dbpwd = "tiger";
	
	///Constructor
	public UserDao() {
	}
	
	///Method
	public void getUser(UserVO userVO) {
		
		Connection con = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		
		try {
			
			// 1단계 Connection
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(dburl, dbuser, dbpwd);
						
			// 2단계 Statement
			pStmt = con.prepareStatement("SELECT id, pwd FROM users WHERE id = ? and pwd = ?");
			 					// 이렇게 하면 password만 틀렸습니다 라고 할 수 없음. 무조건 id, pwd 다 맞아야 함 
								// DbBean 에서 한 것처럼 쿼리 날리면 id / pwd 구분해서 틀렸다고 해줄 수 있음 ! 
								// 상황에 맞게 조절해서 선택 사용하기 
										
			pStmt.setString(1, userVO.getId());
			pStmt.setString(2, userVO.getPwd());
			
			// 3단계 ResultSet
			rs = pStmt.executeQuery();
			
			if (rs.next()) { // id, pwd가 맞으면 결과 출력할 것이 있을 것 
				System.out.println("db에서 확인한 id, pwd==>: "+userVO.getId()+" : "+userVO.getPwd());
				//==> 데이터가 존재 한다면
				userVO.setActive(true); // default가 false니까 데이터가 있으면 true 
			} else { // id, pwd가 틀리면 출력할 결과가 없을 것
				System.out.println("db에 client에서 입력한 <"+userVO.getId()+"> 에 해당하는 자료가 없습니다.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
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
