package com.fmeal.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.chdw.loc.domain.UserInfo;
import com.chdw.loc.util.DBConnection;
import com.chdw.loc.util.GsonUtil;
import com.fmeal.dao.UserDao;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import sun.reflect.generics.tree.ReturnType;

public class UserDaoImpl implements UserDao {

	@Override
	public UserInfo login(String userName, String userPass) {
		Connection conn=null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			conn=DBConnection.getConnection();
			String sql="select * from tb_userinfo where username=? and pword=?";
			pst=(PreparedStatement) conn.prepareStatement(sql);
			pst.setString(1, userName);
			pst.setString(2, userPass);
			rs=pst.executeQuery();
			if(rs.next()){
				UserInfo userInfo=new UserInfo();
				userInfo.setU_id(rs.getString("u_id"));
				userInfo.setUser_icon(rs.getString("user_icon"));
				userInfo.setUser_alias(rs.getString("user_alias"));
				userInfo.setUser_sex(rs.getString("user_sex"));
				userInfo.setUser_age(rs.getInt("user_age"));
				userInfo.setUser_signature(rs.getString("user_signature"));
				userInfo.setUsername(rs.getString("username"));
				userInfo.setPassword(rs.getString("pword"));
				userInfo.setToken(rs.getString("token"));
				return userInfo;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.close(conn, pst, rs);
		}

		return null;
	}

	@Override
	public boolean updateUser(UserInfo user) {
		Connection conn=null;
		PreparedStatement pst = null;
		int i=-1;
		try {
			conn=DBConnection.getConnection();
			String sql="update tb_userinfo set user_alias=?, user_sex=?, user_age=?, user_signature=?, username=?, pword=?, token=? where u_id=?;";
			pst=(PreparedStatement) conn.prepareStatement(sql);
			pst.setString(1,user.getUser_alias());
			pst.setString(2, user.getUser_sex());
			pst.setInt(3, user.getUser_age());
			pst.setString(4, user.getUser_signature());
			pst.setString(5, user.getUsername());
			pst.setString(6, user.getPassword());
			pst.setString(7, user.getToken());
			pst.setString(8, user.getU_id());
			i=pst.executeUpdate();
			if (i!=-1) {
				return true;
			}else {
				return false;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.close(conn, pst, null);
		}

		return false;
	}

	@Override
	public Boolean findUser(String username) {
		Connection conn=null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			conn=DBConnection.getConnection();
			String sql="select * from tb_userinfo where username=?";
			pst=(PreparedStatement) conn.prepareStatement(sql);
			pst.setString(1, username);
			rs=pst.executeQuery();
			if(rs.next()){

				return new Boolean(true);
			}else {
				return new Boolean(false);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.close(conn, pst, rs);
		}
		return null;
	}

	@Override
	public UserInfo addUser(UserInfo userInfo) {
		Connection conn=null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		int i=0;
		try {
			conn=DBConnection.getConnection();
			String sql="insert into tb_userinfo values(?,?,?,?,?,?,?,?,?);";
			pst = (PreparedStatement) conn.prepareStatement(sql);
			pst.setString(1, userInfo.getU_id());
			pst.setString(2, userInfo.getUser_icon());
			pst.setString(3, userInfo.getUser_alias());
			pst.setString(4, userInfo.getUser_sex());
			pst.setInt(5, userInfo.getUser_age());
			pst.setString(6, userInfo.getUser_signature());
			pst.setString(7, userInfo.getUsername());
			pst.setString(8, userInfo.getPassword());
			pst.setString(9, userInfo.getToken());
			i=pst.executeUpdate();
			if (i!=0) {
				return login(userInfo.getUsername(), userInfo.getPassword());
			}else {
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.close(conn, pst, rs);
		}
		return null;
	}

	@Override
	public UserInfo updatePassword(UserInfo userInfo) {
		Connection conn=null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		int i=0;
		try {
			conn=DBConnection.getConnection();
			String sql="update tb_userinfo set pword=? where username=?;";
			pst = (PreparedStatement) conn.prepareStatement(sql);
			pst.setString(1, userInfo.getPassword());
			pst.setString(2, userInfo.getUsername());
			i=pst.executeUpdate();
			if (i!=0) {
				return login(userInfo.getUsername(), userInfo.getPassword());
			}else {
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.close(conn, pst, rs);
		}
		return null;
	}

	@Override
	public Boolean setUserIcon(String u_id, String path) {
		Connection conn=null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		int i=0;
		try {
			conn=DBConnection.getConnection();
			String sql="update tb_userinfo set user_icon=? where u_id=?;";
			pst = (PreparedStatement) conn.prepareStatement(sql);
			pst.setString(1, path);
			pst.setString(2, u_id);
			i=pst.executeUpdate();
			if (i!=0) {
				return new Boolean(true);
			}else {
				return new Boolean(false);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.close(conn, pst, rs);
		}
		return new Boolean(false);
	}

}
