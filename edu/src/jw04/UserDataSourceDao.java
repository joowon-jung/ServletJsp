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
			
			// 1단계 Connection
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			//con = DriverManager.getConnection(dburl, dbuser, dbpwd);
			
			//==> OracleConnectionPool 로 부터 Connection 한 인스턴스 GET
			//con = OracleConnectionPool.getInstance().getConnection();
			
			//===========================================================//
			//==> DataSource에서 connection GET
			InitialContext ic = new InitialContext();
			DataSource ds = (DataSource)ic.lookup("java:comp/env/jdbc/ora");
										//==> java:comp : naming service root 
										//==> env : environment 
										//==> jdbc/ora : server.xml에 등록된 name 
			con = ds.getConnection();
			//===========================================================//
			
			// 2단계 Statement
			pStmt = con.prepareStatement("SELECT id, pwd FROM users WHERE id = ? and pwd = ?");
			pStmt.setString(1, userVO.getId());
			pStmt.setString(2, userVO.getPwd());
			
			// 3단계 ResultSet
			rs = pStmt.executeQuery();
			
			if (rs.next()) { // id, pwd가 맞으면 결과 출력할 것이 있을 것 
				System.out.println("db에서 확인한 id, pwd==>: "+userVO.getId()+" : "+userVO.getPwd());
				//==> 데이터가 존재 한다면
				userVO.setActive(true);
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
