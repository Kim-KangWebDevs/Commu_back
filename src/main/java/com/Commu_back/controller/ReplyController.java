package com.Commu_back.controller;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Commu_back.vo.ReplyVO;

@RequestMapping("/reply")
@Controller
public class ReplyController {

	// 댓글 목록 조회
	@PostMapping("/replylist.do")
	@ResponseBody
	public ResponseEntity<ArrayList<ReplyVO>> replyList(@RequestParam("no") String board_no,
			@RequestParam("order") String reply_order, @RequestParam("page") String reply_page) throws Exception {

		ArrayList<ReplyVO> replyList = new ArrayList<ReplyVO>();

		ReplyVO replyVO = new ReplyVO();

		return new ResponseEntity<>(replyList, HttpStatus.OK);

	}

	@PostMapping("/replywrite.do")
	@ResponseBody
	public ResponseEntity<Integer> writereply(String board_type, @RequestParam("rno") String board_no)
			throws Exception {

		return new ResponseEntity<>(1, HttpStatus.OK);

	}

	@PostMapping("/replyremove.do")
	@ResponseBody
	public ResponseEntity<Integer> removeList(@RequestParam("rno") String reply_no) throws Exception {

		return new ResponseEntity<>(1, HttpStatus.OK);

	}
}
