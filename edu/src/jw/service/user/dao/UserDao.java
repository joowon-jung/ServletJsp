package jw.service.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jw.common.dao.AbstractDao;
import jw.service.user.vo.UserVO;

/*
 * 	AbstractDao ���� ȸ������ Service ��� DAO
 */
public class UserDao extends AbstractDao {

	///Field
	
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
			con = this.connect();
						
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
			this.close(con, pStmt, rs);
		}
	}
	
	public boolean addUser(UserVO userVO){
		Connection con = null;
		PreparedStatement pStmt = null;
		boolean insertResult   = false;
		try{		
			//1�ܰ� connection
			con = this.connect();
			
			//2�ܰ� statement 
			pStmt = con.prepareStatement(	"INSERT " +
												"INTO users ( no, id, pwd) " +
												"VALUES( ? , ? , ? )" );
			pStmt.setInt(1,userVO.getNo());
			pStmt.setString(2,userVO.getId());
			pStmt.setString(3,userVO.getPwd());

			//3�ܰ� ����� Ȯ���ϱ�
			if( 1 == pStmt.executeUpdate()){    
				insertResult = true;
			}
		}catch(Exception e){		
			e.printStackTrace();
		}finally{
			this.close(con,pStmt);
		}
		return insertResult; 
	}//end of addUser()

	// ȸ�������� ��� �޼ҵ�
	public List<UserVO> getUserList() {
		
		List<UserVO> arrayList = new ArrayList<UserVO>();
		
		Connection con = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		
		try {
			
			// 1�ܰ� Connection
			con = this.connect();
						
			// 2�ܰ� Statement
			pStmt = con.prepareStatement("SELECT no, id, pwd FROM users ORDER BY no");
			
			// 3�ܰ� ResultSet ��� ó��
			rs = pStmt.executeQuery();
			
			// ==> select ������ ȸ������ UserVO �� Binding
			// ==> ������ ȸ�������� ���� UserVO�� ArrayList �� ����
			while (rs.next()) {
				UserVO userVO = new UserVO();
				userVO.setNo(rs.getInt("no"));
				userVO.setPwd(rs.getString("pwd"));
				userVO.setId(rs.getString("id"));
				
				// ==> userVO ����Ȯ�� :: console Ȯ��
				System.out.println(userVO);
				
				// ==> arrayList�� UserVO �ν��Ͻ� add
				arrayList.add(userVO);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.close(con, pStmt, rs);
		}
		return arrayList;
	}
}
