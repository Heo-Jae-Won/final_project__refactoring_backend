package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.PboardDAO;
import com.example.dao.PboardLikeDAO;
import com.example.domain.PboardLikeVO;
import com.example.domain.PboardVO;

@Service
public class PboardServiceImpl implements PboardService {
	@Autowired
	PboardDAO pdao;

	@Autowired
	PboardLikeDAO pldao;

	@Transactional
	@Override
	public PboardVO read(String pcode) {
		pdao.updateViewcnt(pcode);
		return pdao.read(pcode);
	}

	@Transactional
	@Override
	public void like(PboardLikeVO loveVO) {
		pldao.like(loveVO);
		pdao.updateLove();
	}
	
	@Transactional
	@Override
	public void dislike(PboardLikeVO disloveVO) {
		pldao.dislike(disloveVO);
		pdao.updateLove();

	}

}
