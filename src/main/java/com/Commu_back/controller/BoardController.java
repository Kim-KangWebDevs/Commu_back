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
	public ResponseEntity<Map<String, Object>> listCategory(@RequestParam("target") String board_category_desc,
			@RequestParam("page") int page) {

		log.info("카테고리 목록 조회");
		return ResponseEntity.ok(boardservice.findCategoryList(board_category_desc, page));
	}

	// 카테고리 추가
	@GetMapping("/addcategory.do")
	public ResponseEntity<Integer> addCategory(@RequestParam("id") String board_category,
			@RequestParam("name") String board_category_desc) {
		
		// 관리자 권한 확인 추가
		// *
		
		log.info("카테고리 추가");
		return ResponseEntity.ok(boardservice.addCategory(board_category, board_category_desc));
	}

	// 카테고리 삭제
	@GetMapping("/removecategory.do")
	public ResponseEntity<Integer> removeCategory(@RequestParam("id") String board_category) {

		// 관리자 권한 확인 추가
		// *
		
		log.info("카테고리 삭제");
		return ResponseEntity.ok(boardservice.removeCategory(board_category));
	}

	// 카테고리 이름 조회
	@GetMapping("/desccategory.do")
	public ResponseEntity<String> descCategory(@RequestParam("id") String board_category) {

		log.info("카테고리 이름 조회");
		return ResponseEntity.ok(boardservice.findCategoryDesc(board_category));
	}

	// 게시글 요청
	// 게시글 목록 조회
	@PostMapping("/boardlist.do")
	public ResponseEntity<Map<String, Object>> boardList(@RequestBody Map<String, Object> board_map) {

		log.info("게시글 목록 조회");
		return ResponseEntity.ok(boardservice.fineBoardList(board_map));

	}

	// 게시글 조회
	@GetMapping("/boarddetail.do")
	public ResponseEntity<Map<String, Object>> boardDetail(@RequestParam("no") int board_no) {

		log.info("게시글 조회");
		return ResponseEntity.ok(boardservice.fineBoard(board_no));

	}

	// 게시글 추가 및 수정
	@PostMapping("/boardwrite.do")
	public ResponseEntity<Integer> boardWrite(@RequestBody Map<String, Object> board_map) {

		// session.getUserId();
		String user_id = "user001";

		log.info("게시글 추가 및 수정");
		return ResponseEntity.ok(boardservice.addBoard(board_map, user_id));

	}

	// 게시글 삭제
	@PostMapping("/boardremove.do")
	public ResponseEntity<Integer> boardRemove(@RequestParam("no") int board_no) {

		// session.getUserId();
		String user_id = "user001";

		log.info("게시글 삭제");
		return ResponseEntity.ok(boardservice.removeBoard(board_no, user_id));

	}

	// 게시글 조회수 증가
	@GetMapping("/boardviews.do")
	public ResponseEntity<Integer> boardViews(@RequestParam("no") int board_no) {

		log.info("게시글 조회수 증가");
		return ResponseEntity.ok(boardservice.addBoardViews(board_no));
	}

}
