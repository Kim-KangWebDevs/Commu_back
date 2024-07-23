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
	public int findReplyCount(int board_no) {

		return replymapper.selectReplyCount(board_no);

	}

	// 댓글 리스트 조회
	public Map<String, Object> findReplylist(int board_no, int page) {

		List<Map<String, Object>> reply_list = new ArrayList<>();
		Map<String, Object> reply_map = new HashMap<>();
		
		PagingVO pagingVO = new PagingVO(replymapper.selectReplyCount(board_no), 20, page);
		reply_map.put("startRow", pagingVO.getStartRow());
		reply_map.put("endRow", pagingVO.getEndRow());
		reply_list = replymapper.selectReplyList(reply_map);

		reply_map = new HashMap<>();
		reply_map.put("page", pagingVO);
		reply_map.put("list", reply_list);

		return reply_map;

	}

	// 댓글 추가 및 수정
	@Transactional(rollbackFor = Exception.class)
	public int addReply(ReplyVO replyVO) {

		if (replyVO.getReply_no() == null)
			replyVO.setReply_no(0);
		if (replyVO.getReply_group() == null)
			replyVO.setReply_group(0);

		return replymapper.insertReply(replyVO);

	}

	// 댓글 삭제
	@Transactional(rollbackFor = Exception.class)
	public int removeReply(String user_id, int reply_no) {

		return replymapper.deleteReply(user_id, reply_no);

	}

}
