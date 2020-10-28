package jw.common.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jw.common.util.DBUtil;

/*
 *	각각의 DAO의 공통, 일반적 사항을 추상화한, 공유 목적의 추상 DAO
 */
public abstract class AbstractDao {

	///Field
	///Constructor	
	public AbstractDao() {
		
	}
	
	///Method
	// ==> DBUtil.getConnection() 이용 Connection return
	public Connection connect() {
		Connection con = null;
		try {
			con = DBUtil.getConnection();
		} catch (RuntimeException ex) {
			throw ex;
		}
		return con;
	}
	
	// ==> Connection / Statement / ResultSet close()
	public void close (Connection con, PreparedStatement pStmt, ResultSet rs) { // select 일 때 
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		this.close(con, pStmt);
	}
	
	// ==> Connection / Statement close()
	public void close (Connection con, PreparedStatement pStmt) { // DML 일 때
		if (pStmt != null) {
			try {
				pStmt.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
		if (con != null) {
			try {
				con.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
