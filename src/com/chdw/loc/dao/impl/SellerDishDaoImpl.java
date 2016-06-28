package com.chdw.loc.dao.impl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chdw.loc.bean.PagingBean;
import com.chdw.loc.dao.SellerDishDao;
import com.chdw.loc.domain.SellerDish;
import com.chdw.loc.util.DBConnection;
import com.chdw.loc.util.StringHandler;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class SellerDishDaoImpl implements SellerDishDao {
	
	private DBConnection dbConn;
	
	public SellerDishDaoImpl() throws Exception {
		dbConn = new DBConnection();
	}

	@Override
	public List<SellerDish> findAll(String condition) {
		List<SellerDish>  dishes = new ArrayList<SellerDish>();
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
						.prepareStatement("select * from sellerdish_view " + condition + ";");
				rs = pstmt.executeQuery();
				while (rs.next()) {
					SellerDish sd = new SellerDish();
					sd.setSd_id(rs.getString(1));
					sd.setSd_icon(rs.getString(2));
					sd.setSd_name(rs.getString(3));
					sd.setSd_saledCount(rs.getInt(4));
					sd.setSd_price(rs.getInt(5));
					sd.setSmt_id(rs.getString(6));
					sd.setSmt_name(rs.getString(7));
					sd.setS_id(rs.getString(8));
					sd.setSeller_name(rs.getString(9));
					
					dishes.add(sd);
				}
				return dishes;
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBConnection.close(conn, pstmt, rs);
			}
		}
		return dishes;
	}


	@Override
	public int add(SellerDish sd) {
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
						.prepareStatement("insert into tb_sellerdish values(?, ?, ?, ?, ?, ?);");
				pstmt.setString(1, StringHandler.createSerialId(System.currentTimeMillis()));
				pstmt.setString(2, sd.getSd_icon());
				pstmt.setString(3, sd.getSd_name());
				pstmt.setInt(4, sd.getSd_saledCount());
				pstmt.setInt(5, sd.getSd_price());
				pstmt.setString(6, sd.getSmt_id());
				
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
	public int update(SellerDish sd) {
//		if (find(sd.getSmt_id(), sd.getSd_name()) == null) {
//			return 2;	//返回2表示该商家的该类别的该菜肴不存在，不能更新
//		}
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
						.prepareStatement("update tb_sellerdish set sd_icon=?, sd_name=?, sd_salecount=?, sd_price=? where sd_id=?;");
				pstmt.setString(1, sd.getSd_icon());
				pstmt.setString(2, sd.getSd_name());
				pstmt.setInt(3, sd.getSd_saledCount());
				pstmt.setInt(4, sd.getSd_price());
				pstmt.setString(5, sd.getSd_id());
				
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
				pstmt = (PreparedStatement) conn.prepareStatement("select count(*) from sellerdish_view " + condition + ";");
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
	public PagingBean<SellerDish> getPageBean(int pageSize, int currPage, 
			String queryCondition) {
		PagingBean<SellerDish> pagingBean = new PagingBean<SellerDish>();
		
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
						.prepareStatement("delete from tb_sellerdish where sd_id in(" + IDs + ");");
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
