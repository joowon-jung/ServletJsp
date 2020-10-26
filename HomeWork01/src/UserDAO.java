import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.sql.DataSource;

/* 
 * FileName : UserDAO.java
 * ㅇ 회원관리 Service를 담당하는 DAO
*/

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
			pStmt = con.prepareStatement("INSERT INTO  users2 VALUES(?,?,?,?,?,?,?,?)");
			pStmt.setString(1, userVO.getId());
			pStmt.setString(2, userVO.getName());
			pStmt.setString(3, userVO.getSex());
			pStmt.setString(4, userVO.getBirth());
			pStmt.setString(5, userVO.getEdu());
			pStmt.setString(6, userVO.getJob());
			pStmt.setString(7, userVO.getPhone_num());
			pStmt.setString(8, userVO.getAddress());

			int confirm = pStmt.executeUpdate();

			// 3단계 결과값 확인하기 => DB에 data insert 유무확인
			if (confirm == 1) { // 정상적으로 한 행이 insert 되면 결과가 1이 담김 
				System.out.println("Table data Insert 완료!");
				userVO.setActive(true); // ★
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

	// 회원정보(회원목록)를 select 하여 UserVO 로 return 하는 method
	public UserVO findUser(String id) {

		UserVO userVO = new UserVO(); // return 할 UserVO 

		// JDBC을 이용하기 위한 객체 생성
		Connection con = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null; // 결과 확인 위함 

		try {

			// 1단계 connetion하기(login과정)
			InitialContext ic = new InitialContext();
			DataSource ds = (DataSource) ic.lookup("java:comp/env/jdbc/ora");
			con = ds.getConnection();

			// 2단계 select query 문을 전송하는단계
			pStmt = con.prepareStatement("SELECT * FROM users2 WHERE id = ?");
			pStmt.setString(1, id);

			// 3단계 ResultSet
			rs = pStmt.executeQuery();

			if (rs.next()) { // 찾은 Result가 있으면 출력할 것 
				System.out.println("db에서 확인한 id ==>: " + rs.getString(1));
				// 상태값 세팅
				userVO.setId(rs.getString(1));
				userVO.setName(rs.getString(2));
				userVO.setSex(rs.getString(3));
				userVO.setBirth(rs.getString(4));
				userVO.setEdu(rs.getString(5));
				userVO.setJob(rs.getString(6));
				userVO.setPhone_num(rs.getString(7));
				userVO.setAddress(rs.getString(8));
				System.out.println(userVO);
			} else {
				System.out.println("db에 client에서 입력한 <" + id + "> 에 해당하는 자료가 없습니다.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//각각의 DB와 관련된 객체 close
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
		return userVO;
	}
}
