package jw.service.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jw.common.dao.AbstractDao;
import jw.service.user.vo.UserVO;

/*
 * 	AbstractDao 공유 회원관리 Service 담당 DAO
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
			
			// 1단계 Connection
			con = this.connect();
						
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
			this.close(con, pStmt, rs);
		}
	}
	
	public boolean addUser(UserVO userVO){
		Connection con = null;
		PreparedStatement pStmt = null;
		boolean insertResult   = false;
		try{		
			//1단계 connection
			con = this.connect();
			
			//2단계 statement 
			pStmt = con.prepareStatement(	"INSERT " +
												"INTO users ( no, id, pwd) " +
												"VALUES( ? , ? , ? )" );
			pStmt.setInt(1,userVO.getNo());
			pStmt.setString(2,userVO.getId());
			pStmt.setString(3,userVO.getPwd());

			//3단계 결과값 확인하기
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

	// 회원정보가 담긴 메소드
	public List<UserVO> getUserList() {
		
		List<UserVO> arrayList = new ArrayList<UserVO>();
		
		Connection con = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		
		try {
			
			// 1단계 Connection
			con = this.connect();
						
			// 2단계 Statement
			pStmt = con.prepareStatement("SELECT no, id, pwd FROM users ORDER BY no");
			
			// 3단계 ResultSet 결과 처리
			rs = pStmt.executeQuery();
			
			// ==> select 각각의 회원정보 UserVO 로 Binding
			// ==> 각각의 회원정보를 갖는 UserVO를 ArrayList 에 저장
			while (rs.next()) {
				UserVO userVO = new UserVO();
				userVO.setNo(rs.getInt("no"));
				userVO.setPwd(rs.getString("pwd"));
				userVO.setId(rs.getString("id"));
				
				// ==> userVO 정보확인 :: console 확인
				System.out.println(userVO);
				
				// ==> arrayList에 UserVO 인스턴스 add
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
