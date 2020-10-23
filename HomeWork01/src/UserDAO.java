import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class UserDAO {

	// DB에서 Data를 insert 하는 method
	public void addUser(UserVO userVO) {

		// JDBC을 이용하기 위한 객체 생성
		Connection con = null;
		PreparedStatement pStmt = null;

		try {

			// 1단계 connetion하기(login과정)
			InitialContext ic = new InitialContext();
			DataSource ds = (DataSource) ic.lookup("java:comp/env/jdbc/ora");
			con = ds.getConnection();

			// 2단계 insert query 문을 전송하는단계 
			pStmt = con.prepareStatement("INSERT INTO  users2 VALUES(?,?,?,?,?,?,?)");
			pStmt.setString(1, userVO.getName());
			pStmt.setString(2, userVO.getSex());
			pStmt.setString(3, userVO.getBirth());
			pStmt.setString(4, userVO.getEdu());
			pStmt.setString(5, userVO.getJob());
			pStmt.setString(6, userVO.getPhone_num());
			pStmt.setString(7, userVO.getAddress());

			int confirm = pStmt.executeUpdate();

			// 3단계 결과값 확인하기 => DB에 data insert 유무확인 
			if (confirm == 1) {
				System.out.println("Table data Insert 완료!");
				userVO.setActive(true);
				System.out.println("Insert 데이터 : " + userVO);
			} else {
				System.out.println("Table data Insert 실패!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 각각의 DB와 관련된 객체 close
			if (pStmt != null)
				try {
					pStmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
	// DB에서 Data를 select 하는 method
	public UserVO findUser(String name) {
		
		
		
		return null;
	}
}
