package com.Commu_back.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Commu_back.mapper.ReplyMapper;
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
	public List<Map<String, Object>> findReplylist(int board_no, String reply_order, String reply_page) {

		Map<String, Object> reply_map = new HashMap<>();
		reply_map.put("board_no", board_no);
		reply_map.put("reply_order", reply_order);
		// 이자리 페이징 추가(startRow, endRow)

		return replymapper.selectReplyList(reply_map);

	}

	// 댓글 추가
	@Transactional(rollbackFor = Exception.class)
	public int addReply(ReplyVO replyVO, String user_id) {

		Map<String, Object> reply_map = objectMapper.convertValue(replyVO, HashMap.class);
		reply_map.put("user_id", user_id);

		return replymapper.insertReply(reply_map);

	}

	// 댓글 삭제
	@Transactional(rollbackFor = Exception.class)
	public int removeReply(String user_id, Integer reply_no) {

		return replymapper.deleteReply(user_id, reply_no);

	}

}
