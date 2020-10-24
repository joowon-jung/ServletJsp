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
			
			// 1�ܰ� Connection
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(dburl, dbuser, dbpwd);
						
			// 2�ܰ� Statement
			pStmt = con.prepareStatement("SELECT id, pwd FROM users WHERE id = ? and pwd = ?");
			 					// �̷��� �ϸ� password�� Ʋ�Ƚ��ϴ� ��� �� �� ����. ������ id, pwd �� �¾ƾ� �� 
								// DbBean ���� �� ��ó�� ���� ������ id / pwd �����ؼ� Ʋ�ȴٰ� ���� �� ���� ! 
								// ��Ȳ�� �°� �����ؼ� ���� ����ϱ� 
										
			pStmt.setString(1, userVO.getId());
			pStmt.setString(2, userVO.getPwd());
			
			// 3�ܰ� ResultSet
			rs = pStmt.executeQuery();
			
			if (rs.next()) { // id, pwd�� ������ ��� ����� ���� ���� �� 
				System.out.println("db���� Ȯ���� id, pwd==>: "+userVO.getId()+" : "+userVO.getPwd());
				//==> �����Ͱ� ���� �Ѵٸ�
				userVO.setActive(true); // default�� false�ϱ� �����Ͱ� ������ true 
			} else { // id, pwd�� Ʋ���� ����� ����� ���� ��
				System.out.println("db�� client���� �Է��� <"+userVO.getId()+"> �� �ش��ϴ� �ڷᰡ �����ϴ�.");
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
