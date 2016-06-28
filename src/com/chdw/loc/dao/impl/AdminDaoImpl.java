package com.chdw.loc.dao.impl;




import java.sql.ResultSet;
import java.sql.SQLException;

import com.chdw.loc.dao.AdminDao;
import com.chdw.loc.domain.Admin;
import com.chdw.loc.util.DBConnection;
import com.chdw.loc.util.Md5Encoder;
import com.chdw.loc.util.StringHandler;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class AdminDaoImpl implements AdminDao {

	private DBConnection dbConn;

	public AdminDaoImpl() throws Exception {
		dbConn = new DBConnection();
	}

	@Override
	public Admin find(String username) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = dbConn.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if (conn != null) {
			try {
				pstmt = (PreparedStatement) conn
						.prepareStatement("select * from tb_admin where username = ?;");
				pstmt.setString(1, username);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					Admin av = new Admin();
					av.setUsername(rs.getString("username"));
					av.setPassword(rs.getString("password"));
					return av;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBConnection.close(conn, pstmt, rs);
			}
		}
		return null;
	}

	@Override
	public int add(String username, String password) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbConn.getConnection();
			pstmt = (PreparedStatement) conn.prepareStatement("insert into tb_admin(username,password) values(?, ?);");
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(conn, pstmt, null);
		}
		return 0;
	}

	/**
	 * 验证用户名密码，用户登录验证
	 * 返回1表示验证通过
	 * 返回2表示用户不存在
	 */
	@Override
	public int validate(String inputUname, String inputPwd) {
		//当该用户名不存在时
		if (find(inputUname) == null) {
			return 2;
		}
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = dbConn.getConnection();
			pstmt = (PreparedStatement) conn.prepareStatement("select password from tb_admin where username=?;");
			pstmt.setString(1, inputUname);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (inputPwd.equals(rs.getString("password"))) {
					return 1;	//验证通过
				}else {
					return 3;	//用户名或密码错误
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(conn, pstmt, rs);
		}
		return 0;
	}

	@Override
	public int update(Admin uv) {
		//当该用户名不存在时
		if (find(uv.getUsername()) == null) {
			return 2;
		}
		String secret = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = dbConn.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		if (conn != null) {
			try {
				secret = Md5Encoder.encode(uv.getPassword());
				pstmt = (PreparedStatement) conn.prepareStatement("update tb_admin set pword=? where valid_id=?");
				pstmt.setString(1, secret);
				pstmt.setString(2, uv.getUsername());
				return pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBConnection.close(conn, pstmt, rs);
			}
		}
		return 0;
	}

}
