package jw04;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import jw.common.pool.OracleConnectionPool;

public class UserDataSourceDao {

	///Field
	//private String dburl = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	//private String dbuser = "scott";
	//private String dbpwd = "tiger";
	
	///Constructor
	public UserDataSourceDao() {
	}
	
	///Method
	public void getUser(UserVO userVO) {
		
		Connection con = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		
		try {
			
			// 1�ܰ� Connection
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			//con = DriverManager.getConnection(dburl, dbuser, dbpwd);
			
			//==> OracleConnectionPool �� ���� Connection �� �ν��Ͻ� GET
			//con = OracleConnectionPool.getInstance().getConnection();
			
			//===========================================================//
			//==> DataSource���� connection GET
			InitialContext ic = new InitialContext();
			DataSource ds = (DataSource)ic.lookup("java:comp/env/jdbc/ora");
										//==> java:comp : naming service root 
										//==> env : environment 
										//==> jdbc/ora : server.xml�� ��ϵ� name 
			con = ds.getConnection();
			//===========================================================//
			
			// 2�ܰ� Statement
			pStmt = con.prepareStatement("SELECT id, pwd FROM users WHERE id = ? and pwd = ?");
			pStmt.setString(1, userVO.getId());
			pStmt.setString(2, userVO.getPwd());
			
			// 3�ܰ� ResultSet
			rs = pStmt.executeQuery();
			
			if (rs.next()) { // id, pwd�� ������ ��� ����� ���� ���� �� 
				System.out.println("db���� Ȯ���� id, pwd==>: "+userVO.getId()+" : "+userVO.getPwd());
				//==> �����Ͱ� ���� �Ѵٸ�
				userVO.setActive(true);
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
