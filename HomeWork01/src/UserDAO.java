import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class UserDAO {

	public void addUser(UserVO userVO) {

		Connection con = null;
		PreparedStatement pStmt = null;

		try {

			InitialContext ic = new InitialContext();
			DataSource ds = (DataSource) ic.lookup("java:comp/env/jdbc/ora");
			con = ds.getConnection();

			pStmt = con.prepareStatement("INSERT INTO  users2 VALUES(?,?,?,?,?,?,?)");
			pStmt.setString(1, userVO.getName());
			pStmt.setString(2, userVO.getSex());
			pStmt.setString(3, userVO.getBirth());
			pStmt.setString(4, userVO.getEdu());
			pStmt.setString(5, userVO.getJob());
			pStmt.setString(6, userVO.getPhone_num());
			pStmt.setString(7, userVO.getAddress());

			int confirm = pStmt.executeUpdate();

			if (confirm == 1) {
				System.out.println("Table data Insert 완료!");
				userVO.setActive(true);
			} else {
				System.out.println("Table data Insert 실패!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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
}
