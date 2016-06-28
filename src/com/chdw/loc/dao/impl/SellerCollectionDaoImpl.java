/**
 * 
 */
package com.chdw.loc.dao.impl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chdw.loc.bean.PagingBean;
import com.chdw.loc.dao.SellerCollectionDao;
import com.chdw.loc.domain.SellerCollection;
import com.chdw.loc.util.DBConnection;
import com.chdw.loc.util.StringHandler;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

/**
 * @author Gem
 *
 */
public class SellerCollectionDaoImpl implements SellerCollectionDao {
	
	private DBConnection dbConn;
	
	public SellerCollectionDaoImpl() throws Exception {
		dbConn = new DBConnection();
	}

	@Override
	public List<SellerCollection> findAll(String condition) {
		List<SellerCollection>  scs = new ArrayList<SellerCollection>();
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
				pstmt = (PreparedStatement) conn.prepareStatement("select * from sellercollection_view " + condition + ";");
				rs = pstmt.executeQuery();
				while (rs.next()) {
					SellerCollection sc = new SellerCollection();
					sc.setScoll_id(rs.getString(1));
					sc.setS_id(rs.getString(2));
					sc.setSeller_name(rs.getString(3));
					sc.setU_id(rs.getString(4));
					sc.setUser_alias(rs.getString(5));
					scs.add(sc);
				}
				return scs;
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBConnection.close(conn, pstmt, rs);
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.chdw.loc.dao.SellerCollectionDao#add(com.chdw.loc.domain.SellerCollection)
	 */
	@Override
	public int add(SellerCollection sc) {
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
						.prepareStatement("insert into tb_sellercollection values(?, ?, ?);");
				pstmt.setString(1, StringHandler.createSerialId(System.currentTimeMillis()));
				pstmt.setString(2, sc.getS_id());
				pstmt.setString(3, sc.getU_id());
				
				return pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBConnection.close(conn, pstmt, null);
			}
		}
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.chdw.loc.dao.SellerCollectionDao#update(com.chdw.loc.domain.SellerCollection)
	 */
	@Override
	public int update(SellerCollection sc) {
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.chdw.loc.dao.SellerCollectionDao#getTotalCount(java.lang.String)
	 */
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
				pstmt = (PreparedStatement) conn.prepareStatement("select count(*) from sellercollection_view " + condition + ";");
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

	/* (non-Javadoc)
	 * @see com.chdw.loc.dao.SellerCollectionDao#getPageBean(int, int, java.lang.String)
	 */
	@Override
	public PagingBean<SellerCollection> getPageBean(int pageSize, int currPage,
			String queryCondition) {
		PagingBean<SellerCollection> pagingBean = new PagingBean<SellerCollection>();
		
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
						.prepareStatement("delete from tb_sellercollection where scoll_id in(" + IDs + ");");
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
