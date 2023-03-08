package com.example.service;

import com.example.domain.PboardLikeVO;
import com.example.domain.PboardVO;

public interface PboardService {
	public PboardVO read(String pcode);
	public void like(PboardLikeVO loveVO);
	public void dislike(PboardLikeVO disloveVO);
}
