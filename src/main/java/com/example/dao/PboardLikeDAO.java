package com.example.dao;

import com.example.domain.PboardLikeVO;

public interface PboardLikeDAO {
	public void like(PboardLikeVO likeVO);
	public void dislike(PboardLikeVO dislikeVO);
	public PboardLikeVO likeCount(String pcode, String unickname);
	
}
