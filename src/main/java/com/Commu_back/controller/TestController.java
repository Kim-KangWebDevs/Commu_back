package com.Commu_back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Commu_back.service.BoardService;
import com.Commu_back.service.ReplyService;
import com.Commu_back.service.UserService;
import com.Commu_back.vo.BoardVO;
import com.Commu_back.vo.ReplyVO;
import com.Commu_back.vo.UserVO;

@RestController
@RequestMapping("/test")
public class TestController {

	@Autowired
	private UserService userservice;

	@Autowired
	private BoardService boardservice;

	@Autowired
	private ReplyService replyservice;

	// 1.유저추가
	@PostMapping("/adduser.do")
	public ResponseEntity<Integer> setUser(@RequestBody UserVO userVO) throws Exception {
		return ResponseEntity.ok(userservice.setUser(userVO));
	}

	// 2. 카테고리 추가
	@PostMapping("/addcategory.do")
	public ResponseEntity<Integer> addCategory(@RequestParam("id") String boardCategory,
			@RequestParam("name") String boardCategoryDesc) {
		return ResponseEntity.ok(boardservice.addCategory(boardCategory, boardCategoryDesc));
	}

	// 3. 게시글 추가
	@PostMapping("/addboard.do")
	public ResponseEntity<Integer> boardWrite(@RequestBody BoardVO boardVO) {
		return ResponseEntity.ok(boardservice.addBoard(boardVO));
	}

	// 댓글 추가 및 수정
	@PostMapping("/addreply.do")
	public ResponseEntity<Integer> writereply(@RequestBody ReplyVO replyVO) throws Exception {
		return ResponseEntity.ok(replyservice.addReply(replyVO));
	}
}
