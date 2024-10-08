package com.Commu_back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Commu_back.service.AuthService;
import com.Commu_back.service.BoardService;
import com.Commu_back.service.ReplyService;
import com.Commu_back.service.UserService;
import com.Commu_back.vo.BoardVO;
import com.Commu_back.vo.ReplyVO;
import com.Commu_back.vo.TokenVO;
import com.Commu_back.vo.UserVO;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

	@Autowired
	private UserService userservice;
	@Autowired
	private AuthService authService;

	@Autowired
	private BoardService boardservice;

	@Autowired
	private ReplyService replyservice;

	// 1.유저추가
	@PostMapping("/adduser.do")
	public ResponseEntity<Integer> setUser(@RequestBody UserVO userVO) throws Exception {
		return ResponseEntity.ok(authService.setUser(userVO));
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
		return ResponseEntity.ok(boardservice.addBoard(boardVO, boardVO.getUserId()));
	}

	// 4. 댓글 추가 및 수정
	@PostMapping("/addreply.do")
	public ResponseEntity<Integer> writereply(@RequestBody ReplyVO replyVO) throws Exception {
		return ResponseEntity.ok(replyservice.addReply(replyVO, replyVO.getUserId()));
	}
	
	//로그인
	@PostMapping("/TESTlogin")
    public ResponseEntity<TokenVO> login(@RequestBody UserVO uservo, HttpServletRequest request) {
		System.out.println(uservo.getRoleName());
		System.out.println(request.isUserInRole("role_admin"));
		
        return ResponseEntity.ok(authService.login(uservo));
    }
}
