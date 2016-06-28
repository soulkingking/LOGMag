package com.chdw.loc.dao.impl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chdw.loc.bean.PagingBean;
import com.chdw.loc.dao.RestCommentDao;
import com.chdw.loc.domain.RestComment;
import com.chdw.loc.util.DBConnection;
import com.chdw.loc.util.StringHandler;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class RestCommentDaoImpl implements RestCommentDao {
	
	private DBConnection dbConn;
	
	public RestCommentDaoImpl() throws Exception {
		dbConn = new DBConnection();
	}
	
	@Override
	public List<RestComment> findAll(String condition) {
		List<RestComment>  rcs = new ArrayList<RestComment>();
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
						.prepareStatement("select * from restcomment_view " + condition + ";");
				rs = pstmt.executeQuery();
				while (rs.next()) {
					RestComment rc = new RestComment();
					rc.setRc_id(rs.getString(1));
					rc.setRc_eat(rs.getInt(2));
					rc.setRc_service(rs.getInt(3));
					rc.setRc_env(rs.getInt(4));
					rc.setRc_content(rs.getString(5));
					rc.setR_id(rs.getString(6));
					rc.setRest_name(rs.getString(7));
					rc.setU_id(rs.getString(8));
					rc.setUser_alias(rs.getString(9));
					
					rcs.add(rc);
				}
				return rcs;
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBConnection.close(conn, pstmt, rs);
			}
		}
		return rcs;
	}

	@Override
	public int add(RestComment rc) {
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
						.prepareStatement("INSERT INTO tb_restcomment VALUES (?,?,?,?,?,?,?);");
				pstmt.setString(1, StringHandler.createSerialId(System.currentTimeMillis()));
				pstmt.setInt(2, rc.getRc_eat());
				pstmt.setInt(3, rc.getRc_service());
				pstmt.setInt(4, rc.getRc_env());
				pstmt.setString(5, rc.getRc_content());
				pstmt.setString(6, rc.getR_id());
				pstmt.setString(7, rc.getU_id());

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
	public int update(RestComment rc) {
		if (findAll(rc.getRc_id()).size() == 0) {
			return 2; // 表示餐厅评价不存在，不能更新
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
						.prepareStatement("UPDATE tb_restcomment SET rc_eat=?, rc_service=?, rc_env=?, rc_content=?  WHERE rc_id=? ;");
				pstmt.setInt(1, rc.getRc_eat());
				pstmt.setInt(2, rc.getRc_service());
				pstmt.setInt(3, rc.getRc_env());
				pstmt.setString(4, rc.getRc_content());
				pstmt.setString(5, rc.getRc_id());

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
				pstmt = (PreparedStatement) conn.prepareStatement("select count(*) from restcomment_view " + condition + ";");
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
	public PagingBean<RestComment> getPageBean(int pageSize, int currPage,
			String queryCondition) {
		PagingBean<RestComment> pagingBean = new PagingBean<RestComment>();
		
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
						.prepareStatement("delete from tb_restcomment where rc_id in(" + IDs + ");");
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
