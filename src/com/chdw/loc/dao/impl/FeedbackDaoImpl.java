package com.chdw.loc.dao.impl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chdw.loc.bean.PagingBean;
import com.chdw.loc.dao.FeedbackDao;
import com.chdw.loc.domain.Feedback;
import com.chdw.loc.util.DBConnection;
import com.chdw.loc.util.StringHandler;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class FeedbackDaoImpl implements FeedbackDao {

	private DBConnection dbConn;

	public FeedbackDaoImpl() throws Exception {
		dbConn = new DBConnection();
	}

	// 查询
	@Override
	public List<Feedback> findAll(String condition) {
		List<Feedback> feedbacks = new ArrayList<Feedback>();
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
				pstmt = (PreparedStatement) conn.prepareStatement("select * from feedback_view "
						+ condition + ";");
				rs = pstmt.executeQuery();
				while (rs.next()) {
					Feedback fb = new Feedback();
					fb.setFb_id(rs.getString(1));
					fb.setFb_content(rs.getString(2));
					fb.setFb_date(rs.getDate(3));
					fb.setU_id(rs.getString(4));
					fb.setUser_alias(rs.getString(5));

					feedbacks.add(fb);
				}
				return feedbacks;
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBConnection.close(conn, pstmt, rs);
			}
		}
		return feedbacks;
	}

	// 插入
	@Override
	public int add(Feedback fb) {

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
						.prepareStatement("insert into tb_feedback(fb_id, fb_content, u_id) values(?, ?, ?);");
				pstmt.setString(1, StringHandler.createSerialId(System
						.currentTimeMillis()));
				pstmt.setString(2, fb.getFb_content());
				pstmt.setString(3, fb.getU_id());

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
				pstmt = (PreparedStatement) conn
						.prepareStatement("select count(*) from feedback_view "
								+ condition + ";");
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
	public PagingBean<Feedback> getPageBean(int pageSize, int currPage,
			String queryCondition) {
		PagingBean<Feedback> pagingBean = new PagingBean<Feedback>();

		pagingBean.setPageSize(pageSize); // 设置页面大小
		pagingBean.setCurrPage(currPage); // 设置当前页数
		pagingBean.setTotalRows(getTotalCount(queryCondition)); // 设置总记录数

		int allPages = pagingBean.getTotalRows() % pagingBean.getPageSize() == 0 ? pagingBean
				.getTotalRows() / pagingBean.getPageSize()
				: (pagingBean.getTotalRows() / pagingBean.getPageSize() + 1);
		pagingBean.setTotalPages(allPages); // 设置总页数

		queryCondition = queryCondition + " limit "
				+ ((currPage - 1) * pageSize) + "," + pageSize;
		System.out.println("查询条件" + queryCondition);
		System.out.println("集合条件" + findAll(queryCondition).size());
		pagingBean.setList(findAll(queryCondition)); // 设置需要分页显示的集合

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
						.prepareStatement("delete from tb_feedback where fb_id in(" + IDs + ");");
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
