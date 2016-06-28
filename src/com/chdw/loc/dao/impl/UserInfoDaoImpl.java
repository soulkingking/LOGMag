package com.chdw.loc.dao.impl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.chdw.loc.bean.PagingBean;
import com.chdw.loc.dao.UserInfoDao;
import com.chdw.loc.domain.UserInfo;
import com.chdw.loc.util.DBConnection;
import com.chdw.loc.util.GsonUtil;
import com.chdw.loc.util.StringHandler;
import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class UserInfoDaoImpl implements UserInfoDao {

	private DBConnection dbConn;

	public UserInfoDaoImpl() throws Exception {
		dbConn = new DBConnection();
	}

	@Override
	public List<UserInfo> findAll(String condition) {
		List<UserInfo>  userInfos = new ArrayList<UserInfo>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if (conn != null) {
			try {
				pstmt = (PreparedStatement) conn.prepareStatement("select * from tb_userinfo " + condition + ";");
				System.out.println("select * from tb_userinfo " + condition + ";");
				rs = pstmt.executeQuery();
				while (rs.next()) {
					UserInfo ui = new UserInfo();
					ui.setU_id(rs.getString(1));
					ui.setUser_icon(rs.getString(2));
					ui.setUser_alias(rs.getString(3));
					ui.setUser_sex(rs.getString(4));
					ui.setUser_age(rs.getInt(5));
					ui.setUser_signature(rs.getString(6));
					ui.setUsername(rs.getString(7));
					ui.setPassword(rs.getString(8));
					ui.setToken(rs.getString(9));

					userInfos.add(ui);
				}
				return userInfos;
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBConnection.close(conn, pstmt, rs);
			}
		}
		return userInfos;
	}


	@Override
	public int getTotalCount(String condition) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if (conn != null) {
			try {
				pstmt = (PreparedStatement) conn.prepareStatement("select count(*) from tb_userinfo " + condition + ";");
				rs = pstmt.executeQuery();
				if (rs.next()) {
					return rs.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBConnection.close(conn, pstmt, rs);
			}
		}
		return 0;
	}

	@Override
	public int add(UserInfo ui) {
		if (getTotalCount("where username='" + ui.getUsername() + "'") != 0) {
			return 2;
		}
		PreparedStatement pstmt = null;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if (conn != null) {
			try {
				pstmt = (PreparedStatement) conn
						.prepareStatement("insert into tb_userinfo values(?,?,?,?,?,?,?,?,?);");
				pstmt.setString(1, ui.getU_id());
				pstmt.setString(2, ui.getUser_icon());
				pstmt.setString(3, ui.getUser_alias());
				pstmt.setString(4, ui.getUser_sex());
				pstmt.setInt(5, ui.getUser_age());
				pstmt.setString(6, ui.getUser_signature());
				pstmt.setString(7, ui.getUsername());
				pstmt.setString(8, ui.getPassword());
				pstmt.setString(9, GsonUtil.parseJson(ui.getU_id(), ui.getUser_alias()));

				return pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBConnection.close(conn, pstmt, null);
			}
		}
		return 0;
	}

	@Override
	public int update(UserInfo ui) {
		PreparedStatement pstmt = null;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if (conn != null) {
			try {
				pstmt = (PreparedStatement) conn
						.prepareStatement("update tb_userinfo set user_icon=?, user_alias=?, user_sex=?, user_age=?, user_signature=?, username=?, pword=?, token=? where u_id=?;");

				pstmt.setString(1, ui.getUser_icon());
				pstmt.setString(2, ui.getUser_alias());
				pstmt.setString(3, ui.getUser_sex());
				pstmt.setInt(4, ui.getUser_age());
				pstmt.setString(5, ui.getUser_signature());
				pstmt.setString(6, ui.getUsername());
				pstmt.setString(7, ui.getPassword());
				pstmt.setString(8, find(ui.getU_id()));
				pstmt.setString(9, ui.getU_id());

				return pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBConnection.close(conn, pstmt, null);
			}
		}
		return 0;
	}

	@Override
	public PagingBean<UserInfo> getPageBean(int pageSize, int currPage,
			String queryCondition) {
		if (!queryCondition.equals("")) {
			queryCondition="where username="+queryCondition;
		}
		PagingBean<UserInfo> pagingBean = new PagingBean<UserInfo>();

		pagingBean.setPageSize(pageSize);	//设置页面大小
		pagingBean.setCurrPage(currPage);	//设置当前页数
		pagingBean.setTotalRows(getTotalCount(queryCondition));		//设置总记录数

		int allPages = pagingBean.getTotalRows() % pagingBean.getPageSize() == 0 ? pagingBean
				.getTotalRows() / pagingBean.getPageSize()
				: (pagingBean.getTotalRows() / pagingBean.getPageSize() + 1);
				pagingBean.setTotalPages(allPages);	//设置总页数

				queryCondition = queryCondition + " limit " + ((currPage - 1) * pageSize) + "," + pageSize;
				pagingBean.setList(findAll(queryCondition));	//设置需要分页显示的集合

				return pagingBean;
	}

	@Override
	public int delete(String IDs) {
		PreparedStatement pstmt = null;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if (conn != null) {
			try {
				pstmt = (PreparedStatement) conn
						.prepareStatement("delete from tb_userinfo where u_id in(" + IDs + ");");
				return pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (pstmt != null) {
						pstmt.close();
						pstmt = null;
					}
					if (conn != null) {
						conn.close();
						conn = null;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return 0;
	}

	public String find(String id) {
		Connection conn=null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			conn=DBConnection.getConnection();
			String sql="select * from tb_userinfo where u_id=?";
			pst=(PreparedStatement) conn.prepareStatement(sql);
			pst.setString(1, id);
			rs=pst.executeQuery();
			if(rs.next()){
				return rs.getString("token");
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
	public String validate(String username, String password) {
		CallableStatement cstmt = null;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if (conn != null) {
			try {
				cstmt = (CallableStatement) conn.prepareCall("call login_valid(?, ?, ?);");
				cstmt.setString(1, username);
				cstmt.setString(2, password);
				cstmt.registerOutParameter(3, Types.VARCHAR);
				cstmt.execute();
				return cstmt.getString(3);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (cstmt != null) {
						cstmt.close();
						cstmt = null;
					}
					if (conn != null) {
						conn.close();
						conn = null;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

}
