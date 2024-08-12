package com.Commu_back.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Commu_back.mapper.ReplyMapper;
import com.Commu_back.vo.PagingVO;
import com.Commu_back.vo.ReplyVO;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ReplyService {

	private ReplyMapper replymapper;
	ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	public ReplyService(ReplyMapper replymapper) {
		this.replymapper = replymapper;
	}

	// 댓글 개수 조회
	public int findReplyCount(int boardNo) {

		return replymapper.selectReplyCount(boardNo);

	}

	// 댓글 리스트 조회
	public Map<String, Object> findReplylist(Integer boardNo, Integer page, String replyOrder) {

		List<Map<String, Object>> replyList = new ArrayList<>();
		Map<String, Object> replyMap = new HashMap<>();
		
		PagingVO pagingVO = new PagingVO(replymapper.selectReplyCount(boardNo), 20, page = page != null && page != 0 ? page : 1);
		replyMap.put("boardNo", boardNo);
		replyMap.put("startRow", pagingVO.getStartRow());
		replyMap.put("endRow", pagingVO.getEndRow());
		replyMap.put("replyOrder", replyOrder);
		replyList = replymapper.selectReplyList(replyMap);

		replyMap = new HashMap<>();
		replyMap.put("page", pagingVO);
		replyMap.put("list", replyList);

		return replyMap;

	}

	// 댓글 추가 및 수정
	@Transactional(rollbackFor = Exception.class)
	public int addReply(ReplyVO replyVO, String userId) {

		if (replyVO.getReplyNo() == null)
			replyVO.setReplyNo(0);
		if (replyVO.getReplyGroup() == null)
			replyVO.setReplyGroup(0);

		Map<String, Object> replyMap = objectMapper.convertValue(replyVO, Map.class);
		replyMap.put("userId", userId);
		
		return replymapper.insertReply(replyMap);

	}

	// 댓글 삭제
	@Transactional(rollbackFor = Exception.class)
	public int removeReply(int replyNo, String userId) {

		return replymapper.deleteReply(replyNo, userId);

	}

}
