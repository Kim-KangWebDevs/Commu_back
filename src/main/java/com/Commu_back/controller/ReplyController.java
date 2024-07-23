package com.Commu_back.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Commu_back.service.ReplyService;
import com.Commu_back.vo.ReplyVO;

@RestController
@RequestMapping("/reply")
public class ReplyController {

	private final static Logger log = LoggerFactory.getLogger(ReplyController.class);

	private final ReplyService replyservice;

	@Autowired
	public ReplyController(ReplyService replyservice) {
		this.replyservice = replyservice;
	}

	// 댓글 목록 조회
	@PostMapping("/listreply.do")
	public ResponseEntity<Map<String, Object>> replyList(@RequestParam("board_no") int board_no,
			@RequestParam("page") int page) throws Exception {

		log.info("댓글 목록 조회");
		return ResponseEntity.ok(replyservice.findReplylist(board_no, page));

	}

	// 댓글 추가 및 수정
	@PostMapping("/addreply.do")
	public ResponseEntity<Integer> writereply(@RequestBody ReplyVO replyVO) throws Exception {

		// session.getUserId();
		String user_id = "user001";
		replyVO.setUser_id(user_id);

		log.info("댓글 추가 및 수정");
		return ResponseEntity.ok(replyservice.addReply(replyVO));

	}

	// 댓글 삭제
	@PostMapping("/removereply.do")
	public ResponseEntity<Integer> removeList(@RequestParam("rno") int reply_no) throws Exception {

		// session.getUserId();
		String user_id = "user001";

		log.info("댓글 삭제");
		return ResponseEntity.ok(replyservice.removeReply(user_id, reply_no));

	}
}
