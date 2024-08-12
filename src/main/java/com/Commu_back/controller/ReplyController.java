package com.Commu_back.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
	@GetMapping("/listreply.do")
	public ResponseEntity<Map<String, Object>> replyList(@RequestParam("bno") Integer boardNo,
			@RequestParam(value = "p", required = false) Integer page, 
			@RequestParam(value = "order", required = false) String replyOrder) throws Exception {

		log.info("댓글 목록 조회");
		return ResponseEntity.ok(replyservice.findReplylist(boardNo, page, replyOrder));

	}

	// 댓글 추가 및 수정
	@PostMapping("/addreply.do")
	public ResponseEntity<Integer> writereply(@RequestBody ReplyVO replyVO) throws Exception {

		// session.getUserId();
		String userId = "user001";

		log.info("댓글 추가 및 수정");
		return ResponseEntity.ok(replyservice.addReply(replyVO, userId));

	}

	// 댓글 삭제
	@PostMapping("/removereply.do")
	public ResponseEntity<Integer> removeList(@RequestParam("rno") int replyNo) throws Exception {

		// session.getUserId();
		String userId = "user001";

		log.info("댓글 삭제");
		return ResponseEntity.ok(replyservice.removeReply(replyNo, userId));

	}
}
