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
	@PostMapping("/replylist.do")
	public ResponseEntity<Map<String, Object>> replyList(@RequestBody Map<String, Object> reply_map) throws Exception {

		log.info("댓글 목록 조회");
		return ResponseEntity.ok(replyservice.findReplylist(reply_map));

	}

	// 댓글 추가 및 수정
	@PostMapping("/replywrite.do")
	public ResponseEntity<Integer> writereply(@RequestBody Map<String, Object> reply_map) throws Exception {

		// session.getUserId();
		String user_id = "user001";

		log.info("댓글 추가 및 수정");
		return ResponseEntity.ok(replyservice.addReply(reply_map, user_id));

	}

	// 댓글 삭제
	@PostMapping("/replyremove.do")
	public ResponseEntity<Integer> removeList(@RequestParam("rno") int reply_no) throws Exception {

		// session.getUserId();
		String user_id = "user001";

		log.info("댓글 삭제");
		return ResponseEntity.ok(replyservice.removeReply(user_id, reply_no));

	}
}
