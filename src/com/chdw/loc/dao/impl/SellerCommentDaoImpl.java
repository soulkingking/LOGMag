package com.chdw.loc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chdw.loc.bean.PagingBean;
import com.chdw.loc.dao.SellerCommentDao;
import com.chdw.loc.domain.SellerComment;
import com.chdw.loc.util.DBConnection;
import com.chdw.loc.util.StringHandler;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class SellerCommentDaoImpl implements SellerCommentDao {
	
	private DBConnection dbConn;
	
	public SellerCommentDaoImpl() throws Exception {
		dbConn = new DBConnection();
	}

	@Override
	public List<SellerComment> findAll(String condition) {
		List<SellerComment>  scs = new ArrayList<SellerComment>();
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
						.prepareStatement("select * from sellercomment_view " + condition + ";");
				rs = pstmt.executeQuery();
				while (rs.next()) {
					SellerComment sc = new SellerComment();
					sc.setSc_id(rs.getString(1));
					sc.setSc_eat(rs.getInt(2));
					sc.setSc_service(rs.getInt(3));
					sc.setSc_content(rs.getString(4));
					sc.setS_id(rs.getString(5));
					sc.setSeller_name(rs.getString(6));
					sc.setU_id(rs.getString(7));
					sc.setUser_alias(rs.getString(8));
					
					scs.add(sc);
				}
				return scs;
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBConnection.close(conn, pstmt, rs);
			}
		}
		return scs;
	}
	
	@Override
	public int add(SellerComment sc) {
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
						.prepareStatement("INSERT INTO tb_sellercomment VALUES (?,?,?,?,?,?);");
				pstmt.setString(1, StringHandler.createSerialId(System.currentTimeMillis()));
				pstmt.setInt(2, sc.getSc_eat());
				pstmt.setInt(3, sc.getSc_service());
				pstmt.setString(4, sc.getSc_content());
				pstmt.setString(5, sc.getS_id());
				pstmt.setString(6, sc.getU_id());

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
	public int update(SellerComment sc) {
		if (findAll(sc.getSc_id()).size() == 0) {
			return 2; // 表示商家评价表存在，不能更新
		}
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
						.prepareStatement("UPDATE tb_sellercomment SET sc_eat=?, sc_service=?, sc_content=?  WHERE sc_id=? ;");
				pstmt.setInt(1, sc.getSc_eat());
				pstmt.setInt(2, sc.getSc_service());
				pstmt.setString(3, sc.getSc_content());
				pstmt.setString(4, sc.getSc_id());

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
				pstmt = (PreparedStatement) conn.prepareStatement("select count(*) from sellercomment_view " + condition + ";");
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
	public PagingBean<SellerComment> getPageBean(int pageSize, int currPage,
			String queryCondition) {
		PagingBean<SellerComment> pagingBean = new PagingBean<SellerComment>();
		
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
						.prepareStatement("delete from tb_sellercomment where sc_id in(" + IDs + ");");
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
