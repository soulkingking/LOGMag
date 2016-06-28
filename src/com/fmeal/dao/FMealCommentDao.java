package com.fmeal.dao;

import java.util.List;

import com.chdw.loc.domain.SellerComment;

public interface FMealCommentDao {
	List<SellerComment> findComment(String s_id);

	boolean addComment(SellerComment comment);

}
