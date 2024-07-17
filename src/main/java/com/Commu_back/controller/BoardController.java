package com.Commu_back.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Commu_back.service.BoardService;
import com.Commu_back.vo.BoardVO;

@Controller
@RequestMapping("/board/*")
public class BoardController {

	private BoardService boardservice;
	private static Logger log = LoggerFactory.getLogger(BoardController.class);

	// BoardService 싱글톤 생성 
	@Autowired
	public BoardController(BoardService boardservice) {
		this.boardservice = boardservice;
	}

	// 페이지 연결 메소드
	// 게시판 카테고리 페이지
	@GetMapping("/category")
	public String boardCategory(@RequestParam("id") String board_category) {

		return "/Board/CategoryPage";
	}

	// 검색 페이지(게시글, 게시글 목록 포함)
	@GetMapping("/search")
	public String boardSearch(@RequestParam("id") String board_category,
			@RequestParam(value = "no", required = false) Integer board_no,
			@RequestParam(value = "target", required = false) String target,
			@RequestParam(value = "keyword", required = false) String keyword,
			@RequestParam(value = "page", required = false) String page) {

		String board_category_desc = boardservice.findBoardDesc(board_category);

		if (board_category_desc == "null") {

			log.info("Linked to MainPage");
			return "redirect:/";

		}
		
		if (board_no != null) {

			boardservice.addBoardViews(board_no);

			log.info("Linked to DetailPage");
			return "/Board/DetailPage";
		}

		log.info("Linked to SearchPage");
		return "Board/SearchPage";
	}

	// 게시글 작성 및 수정 페이지
	@GetMapping("/write")
	public String boardWrite(@RequestParam("id") String board_category) {

		return "/Board/WritePage";
	}

	// 데이터 응답 페이지
	// 게시판 카테고리 리스트 조회
	@GetMapping("/listcategory.do")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listCategory(@RequestParam("id") String board_category,
			@RequestParam("target") String board_category_desc, @RequestParam("page") String page) {

		return new ResponseEntity<>(boardservice.findCategoryList(board_category, board_category_desc, page),
				HttpStatus.OK);
	}

	// 게시판 카테고리 추가
	@GetMapping("/addcategory.do")
	@ResponseBody
	public ResponseEntity<Integer> addCategory(@RequestParam("id") String board_category,
			@RequestParam("name") String board_category_desc, @RequestParam("auth") String board_role_no) {

		return new ResponseEntity<>(boardservice.addCategory(board_category, board_category_desc, board_role_no), HttpStatus.OK);
	}

	// 게시판 카테고리 삭제
	@GetMapping("/removecategory.do")
	@ResponseBody
	public ResponseEntity<Integer> removeCategory(@RequestParam("id") String board_category) {

		return new ResponseEntity<>(boardservice.removeCategory(board_category), HttpStatus.OK);
	}

	// 게시판 카테고리 접근 권한 변경
	@GetMapping("/modifycategoryauth.do")
	@ResponseBody
	public ResponseEntity<Integer> modifyCategoryAuth(@RequestParam("id") String board_category, @RequestParam("auth") String board_role_no) {

		return new ResponseEntity<>(boardservice.modifyCategoryAuth(board_category, board_role_no), HttpStatus.OK);
	}
	
	// 게시판 카테고리 이름 조회
	@GetMapping("/desccategory.do")
	@ResponseBody
	public ResponseEntity<String> descCategory(@RequestParam("id") String board_category) {

		return new ResponseEntity<>(boardservice.findBoardDesc(board_category), HttpStatus.OK);
	}

	// 게시글 리스트 조회
	@GetMapping("/boardlist.do")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> boardList(@RequestParam("id") String board_category,
			@RequestParam("target") String target, @RequestParam("keyword") String keyword,
			@RequestParam("page") String page) {

		return new ResponseEntity<>(boardservice.fineBoardList(board_category, target, keyword, page), HttpStatus.OK);

	}

	// 게시글 조회
	@PostMapping("/boarddetail.do")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> boardDetail(@RequestParam("no") String board_no) {

		return new ResponseEntity<>(boardservice.fineBoard(board_no), HttpStatus.OK);

	}

	// 게시글 추가 및 수정
	@PostMapping("/boardwrite.do")
	public ResponseEntity<Integer> boardWrite(@RequestBody BoardVO boardVO) {

		// session.getUserId();
		String user_id = "user001";

		return new ResponseEntity<>(boardservice.addBoard(boardVO, user_id), HttpStatus.OK);

	}

	// 게시글 삭제
	@GetMapping("/boardremove.do")
	@ResponseBody
	public ResponseEntity<Integer> boardRemove(@RequestParam("no") String board_no) {

		// session.getUserId();
		String user_id = "user001";

		return new ResponseEntity<>(boardservice.removeBoard(board_no, user_id), HttpStatus.OK);

	}

}
