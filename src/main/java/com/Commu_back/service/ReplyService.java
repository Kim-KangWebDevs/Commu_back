package com.Commu_back.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Commu_back.mapper.ReplyMapper;
import com.Commu_back.vo.PagingVO;
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
	public Map<String, Object> findReplylist(Map<String, Object> reply_map) {

		List<Map<String, Object>> reply_list = new ArrayList<>();
		int reply_total = replymapper.selectReplyCount((int) reply_map.get("board_no"));

		PagingVO pagingVO = new PagingVO(reply_total, 20, (int) reply_map.get("page"));
		reply_map.remove("page");
		reply_map.put("startRow", pagingVO.getStartRow());
		reply_map.put("endRow", pagingVO.getEndRow());
		reply_list = replymapper.selectReplyList(reply_map);

		reply_map.clear();
		reply_map.put("page", pagingVO);
		reply_map.put("list", reply_list);

		return reply_map;

	}

	// 댓글 추가 및 수정
	@Transactional(rollbackFor = Exception.class)
	public int addReply(Map<String, Object> reply_map, String user_id) {

		if (((String) reply_map.get("reply_no")).chars().allMatch(Character::isDigit) == false)
			reply_map.put("reply_no", 0);

		reply_map.put("user_id", user_id);

		return replymapper.insertReply(reply_map);

	}

	// 댓글 삭제
	@Transactional(rollbackFor = Exception.class)
	public int removeReply(String user_id, int reply_no) {

		return replymapper.deleteReply(user_id, reply_no);

	}

}
