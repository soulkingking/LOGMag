package com.chdw.loc.dao.impl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chdw.loc.bean.PagingBean;
import com.chdw.loc.dao.FoodRingDao;
import com.chdw.loc.domain.FoodRing;
import com.chdw.loc.util.DBConnection;
import com.chdw.loc.util.StringHandler;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class FoodRingDaoImpl implements FoodRingDao {
	
	private DBConnection dbConn;
	
	public FoodRingDaoImpl() throws Exception {
		dbConn = new DBConnection();
	}

	@Override
	public List<FoodRing> findAll(String condition) {
		List<FoodRing>  frs = new ArrayList<FoodRing>();
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
						.prepareStatement("select * from foodring_view " + condition + ";");
				rs = pstmt.executeQuery();
				while (rs.next()) {
					FoodRing fr = new FoodRing();
					fr.setFr_id(rs.getString(1));
					fr.setFr_icon(rs.getString(2));
					fr.setFr_name(rs.getString(3));
					fr.setFr_createtime(rs.getDate(4));
					fr.setFr_visible(rs.getBoolean(5));
					fr.setU_id(rs.getString(6));
					fr.setUser_alias(rs.getString(7));
					fr.setUser_icon(rs.getString(8));
					fr.setUser_signature(rs.getString(9));
					
					frs.add(fr);
				}
				return frs;
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBConnection.close(conn, pstmt, rs);
			}
		}
		return frs;
	}

	@Override
	public int add(FoodRing fr) {
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
						.prepareStatement("insert into tb_foodring(fr_id, fr_icon, fr_name, fr_visible, u_id) values(?, ?, ?, ?, ?);");
				pstmt.setString(1, fr.getFr_id());
				pstmt.setString(2, fr.getFr_icon());
				pstmt.setString(3, fr.getFr_name());
				pstmt.setBoolean(4, fr.isFr_visible());
				pstmt.setString(5, fr.getU_id());
				
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
	public int update(FoodRing fr) {
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
						.prepareStatement("update tb_FoodRing set fr_icon=?, fr_name=?,fr_visible=? where fr_id=?;");
				pstmt.setString(1, fr.getFr_icon());
				pstmt.setString(2, fr.getFr_name());
				pstmt.setBoolean(3, fr.isFr_visible());
				pstmt.setString(4, fr.getFr_id());
				
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
				pstmt = (PreparedStatement) conn.prepareStatement("select count(*) from foodring_view " + condition +";");
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
	public PagingBean<FoodRing> getPageBean(int pageSize, int currPage, 
			String queryCondition) {
		PagingBean<FoodRing> pagingBean = new PagingBean<FoodRing>();
		
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
						.prepareStatement("delete from tb_foodring where fr_id in(" + IDs + ");");
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
