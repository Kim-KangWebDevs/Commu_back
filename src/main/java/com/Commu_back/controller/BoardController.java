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

import com.Commu_back.service.BoardService;
import com.Commu_back.vo.BoardVO;

@RestController
@RequestMapping("/board/*")
public class BoardController {

	private final static Logger log = LoggerFactory.getLogger(BoardController.class);

	private final BoardService boardservice;

	@Autowired
	public BoardController(BoardService boardservice) {
		this.boardservice = boardservice;
	}

	// 게시판 카테고리 요청
	// 카테고리 목록 조회
	@GetMapping("/listcategory.do")
	public ResponseEntity<Map<String, Object>> listCategory(
			@RequestParam(value = "target", required = false) String boardCategoryDesc,
			@RequestParam(value = "p", required = false) Integer page) {

		log.info("카테고리 목록 조회");
		return ResponseEntity.ok(boardservice.findCategoryList(boardCategoryDesc, page));
	}

	// 카테고리 추가
	@GetMapping("/addcategory.do")
	public ResponseEntity<Integer> addCategory(@RequestParam("id") String boardCategory,
			@RequestParam("name") String boardCategoryDesc) {

		// 관리자 권한 확인 추가
		// *

		log.info("카테고리 추가");
		return ResponseEntity.ok(boardservice.addCategory(boardCategory, boardCategoryDesc));
	}

	// 카테고리 삭제
	@GetMapping("/removecategory.do")
	public ResponseEntity<Integer> removeCategory(@RequestParam("id") String boardCategory) {

		// 관리자 권한 확인 추가
		// *

		log.info("카테고리 삭제");
		return ResponseEntity.ok(boardservice.removeCategory(boardCategory));
	}

	// 카테고리 이름 조회
	@GetMapping("/desccategory.do")
	public ResponseEntity<String> descCategory(@RequestParam("id") String boardCategory) {

		log.info("카테고리 이름 조회");
		return ResponseEntity.ok(boardservice.findCategoryDesc(boardCategory));
	}

	// 게시글 요청
	// 게시글 목록 조회
	@PostMapping("/listboard.do")
	public ResponseEntity<Map<String, Object>> boardList(@RequestParam("id") String boardCategory,
			@RequestParam(value = "target", required = false) String target,
			@RequestParam(value = "keyword", required = false) String keyword,
			@RequestParam(value = "p", required = false) Integer page) {

		log.info("게시글 목록 조회");
		return ResponseEntity.ok(boardservice.fineBoardList(boardCategory, target, keyword, page));

	}

	// 게시글 조회
	@GetMapping("/detailboard.do")
	public ResponseEntity<Map<String, Object>> boardDetail(@RequestParam("no") int boardNo) {

		log.info("게시글 조회");
		return ResponseEntity.ok(boardservice.fineBoard(boardNo));

	}

	// 게시글 추가 및 수정
	@PostMapping("/addboard.do")
	public ResponseEntity<Integer> boardWrite(@RequestBody BoardVO boardVO) {

		// session.getUserId();
		String userId = "user001";
		boardVO.setUserId(userId);

		log.info("게시글 추가 및 수정");
		return ResponseEntity.ok(boardservice.addBoard(boardVO));

	}

	// 게시글 삭제
	@PostMapping("/removeboard.do")
	public ResponseEntity<Integer> boardRemove(@RequestParam("no") int boardNo) {

		// session.getUserId();
		String userId = "user001";

		log.info("게시글 삭제");
		return ResponseEntity.ok(boardservice.removeBoard(boardNo, userId));

	}

	// 게시글 조회수 증가
	@GetMapping("/viewsboard.do")
	public ResponseEntity<Integer> boardViews(@RequestParam("no") int boardNo) {

		log.info("게시글 조회수 증가");
		return ResponseEntity.ok(boardservice.addBoardViews(boardNo));
	}

}
