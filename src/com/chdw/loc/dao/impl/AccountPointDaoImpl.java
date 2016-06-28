package com.chdw.loc.dao.impl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chdw.loc.bean.PagingBean;
import com.chdw.loc.dao.AccountPointDao;
import com.chdw.loc.domain.AccountPoint;
import com.chdw.loc.util.DBConnection;
import com.chdw.loc.util.StringHandler;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class AccountPointDaoImpl implements AccountPointDao {
	
	private DBConnection dbConn;
	
	public AccountPointDaoImpl() throws Exception {
		dbConn = new DBConnection();
	}
	
	@Override
	public List<AccountPoint> findAll(String condition) {
		List<AccountPoint>  aps = new ArrayList<AccountPoint>();
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
						.prepareStatement("select * from accountpoint_view " + condition + ";");
				rs = pstmt.executeQuery();
				while (rs.next()) {
					AccountPoint ap = new AccountPoint();
					ap.setAp_id(rs.getString(1));
					ap.setAp_curpoint(rs.getInt(2));
					ap.setAp_maxpoint(rs.getInt(3));
					ap.setAp_maxWeekRank(rs.getInt(4));
					ap.setAp_maxMonthRank(rs.getInt(5));
					ap.setAp_maxRank(rs.getInt(6));
					ap.setAp_curWeekDif(rs.getInt(7));
					ap.setAp_curMonthDif(rs.getInt(8));
					ap.setU_id(rs.getString(9));
					ap.setUser_alias(rs.getString(10));
					ap.setUser_icon(rs.getString(11));
					
					aps.add(ap);
				}
				return aps;
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
					DBConnection.close(conn, pstmt, rs);
			}
		}
		return aps;
	}

	@Override
	public int add(AccountPoint ap) {
		PreparedStatement pstmt = null;
		Connection conn = null;
		try {
			conn = dbConn.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if (conn != null) {
			try {
				pstmt = (PreparedStatement) conn
						.prepareStatement("insert into tb_accountpoint values(?, ?, ?, ?, ?, ?, ?, ?, ?);");
				pstmt.setString(1, StringHandler.createSerialId(System.currentTimeMillis()));
				pstmt.setInt(2, ap.getAp_curpoint());
				pstmt.setInt(3, ap.getAp_maxpoint());
				pstmt.setInt(4, ap.getAp_maxWeekRank());
				pstmt.setInt(5, ap.getAp_maxMonthRank());
				pstmt.setInt(6, ap.getAp_maxRank());
				pstmt.setInt(7, ap.getAp_curWeekDif());
				pstmt.setInt(8, ap.getAp_curMonthDif());
				pstmt.setString(9, ap.getU_id());
				
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
	public int update(AccountPoint ap) {
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
						.prepareStatement("update tb_accountpoint set ap_curpoint=?, ap_maxpoint=?, ap_maxWeekRank=?, ap_maxMonthRank=?, ap_maxRank=?, ap_curWeekDif=?, ap_curMonthDif=? where ap_id=?;");
				pstmt.setInt(1, ap.getAp_curpoint());
				pstmt.setInt(2, ap.getAp_maxpoint());
				pstmt.setInt(3, ap.getAp_maxWeekRank());
				pstmt.setInt(4, ap.getAp_maxMonthRank());
				pstmt.setInt(5, ap.getAp_maxRank());
				pstmt.setInt(6, ap.getAp_curWeekDif());
				pstmt.setInt(7, ap.getAp_curMonthDif());
				pstmt.setString(8, ap.getU_id());
				
				return pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBConnection.close(conn, pstmt, rs);
			}
		}
		return 0;
	}

	@Override
	public int getTotalCount(String condition) {
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
				pstmt = (PreparedStatement) conn.prepareStatement("select count(*) from accountpoint_view " + condition + ";");
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
	public PagingBean<AccountPoint> getPageBean(int pageSize, int currPage,
			String queryCondition) {
		PagingBean<AccountPoint> pagingBean = new PagingBean<AccountPoint>();
		
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
			conn = dbConn.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if (conn != null) {
			try {
				pstmt = (PreparedStatement) conn
						.prepareStatement("delete from tb_accountpoint where ap_id in(" + IDs + ");");
				return pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBConnection.close(conn, pstmt, null);
			}
		}
		return 0;
	}

}
