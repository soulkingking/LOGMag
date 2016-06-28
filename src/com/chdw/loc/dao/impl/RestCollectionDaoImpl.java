/**
 * 
 */
package com.chdw.loc.dao.impl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chdw.loc.bean.PagingBean;
import com.chdw.loc.dao.RestCollectionDao;
import com.chdw.loc.domain.RestCollection;
import com.chdw.loc.util.DBConnection;
import com.chdw.loc.util.StringHandler;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

/**
 * @author Gem
 *
 */
public class RestCollectionDaoImpl implements RestCollectionDao {
	
	private DBConnection dbConn;
	
	public RestCollectionDaoImpl() throws Exception {
		dbConn = new DBConnection();
	}

	@Override
	public List<RestCollection> findAll(String condition) {
		List<RestCollection>  scs = new ArrayList<RestCollection>();
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
				pstmt = (PreparedStatement) conn.prepareStatement("select * from restcollection_view " + condition + ";");
				rs = pstmt.executeQuery();
				while (rs.next()) {
					RestCollection rc = new RestCollection();
					rc.setRcoll_id(rs.getString(1));
					rc.setR_id(rs.getString(2));
					rc.setRest_name(rs.getString(3));
					rc.setU_id(rs.getString(4));
					rc.setUser_alias(rs.getString(5));
					scs.add(rc);
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

	@Override
	public int add(RestCollection rc) {
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
						.prepareStatement("insert into tb_restcollection values(?, ?, ?);");
				pstmt.setString(1, StringHandler.createSerialId(System.currentTimeMillis()));
				pstmt.setString(2, rc.getU_id());
				pstmt.setString(3, rc.getR_id());
				
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
	public int update(RestCollection rc) {
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
				pstmt = (PreparedStatement) conn.prepareStatement("select count(*) from restcollection_view " + condition + ";");
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
	public PagingBean<RestCollection> getPageBean(int pageSize, int currPage,
			String queryCondition) {
		PagingBean<RestCollection> pagingBean = new PagingBean<RestCollection>();
		
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
						.prepareStatement("delete from tb_restcollection where rcoll_id in(" + IDs + ");");
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
