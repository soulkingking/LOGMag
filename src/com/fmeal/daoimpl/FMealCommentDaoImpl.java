package com.fmeal.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chdw.loc.domain.SellerCollection;
import com.chdw.loc.domain.SellerComment;
import com.chdw.loc.util.DBConnection;
import com.chdw.loc.util.StringHandler;
import com.fmeal.dao.FMealCommentDao;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class FMealCommentDaoImpl implements FMealCommentDao {

	@Override
	public List<SellerComment> findComment(String s_id) {
		Connection conn=null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<SellerComment> sellerComments=new ArrayList<>();
		try {
			conn=DBConnection.getConnection();
			String sql="select * from view_comment where s_id=?";
			pst=(PreparedStatement) conn.prepareStatement(sql);
			pst.setString(1, s_id);
			rs=pst.executeQuery();
			while(rs.next()){
				SellerComment sellerComment=new SellerComment();
				sellerComment.setS_id(rs.getString("s_id"));
				sellerComment.setSc_eat(rs.getInt("sc_eat"));
				sellerComment.setSc_service(rs.getInt("sc_service"));
				sellerComment.setSc_content(rs.getString("sc_content"));
				sellerComment.setSeller_name(rs.getString("seller_name"));
				sellerComment.setTo_id(rs.getString("to_id"));
				sellerComment.setSc_id(rs.getString("sc_id"));
				sellerComment.setU_id(rs.getString("u_id"));
				sellerComment.setUser_alias(rs.getString("user_alias"));
				sellerComments.add(sellerComment);
			}
			return sellerComments;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.close(conn, pst, rs);
		}
		return sellerComments;
	}

	@Override
	public boolean addComment(SellerComment comment) {
		Connection conn=null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		int i=0;
		try {
			conn=DBConnection.getConnection();
			String sql="insert into tb_sellercomment values(?,?,?,?,?,?,?);";
			pst=(PreparedStatement) conn.prepareStatement(sql);
			pst.setString(1, StringHandler.createSerialId(System.currentTimeMillis()));
			pst.setInt(2, comment.getSc_eat());
			pst.setInt(3, comment.getSc_service());
			pst.setString(4, comment.getSc_content());
			pst.setString(5, comment.getS_id());
			pst.setString(6, comment.getU_id());
			pst.setString(7, comment.getTo_id());
			i=pst.executeUpdate();
			if (i!=0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.close(conn, pst, rs);
		}
		return false;
	}

}
