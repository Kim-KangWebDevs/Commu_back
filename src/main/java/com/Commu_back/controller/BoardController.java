package com.Commu_back.controller;

import java.util.HashMap;
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

/*  
 * @Author kimtaeu 
*/
@Controller
@RequestMapping("/board/*")
public class BoardController {

	private BoardService boardservice;
	private static Logger log = LoggerFactory.getLogger(BoardController.class);

	// singleton for dependency injection into a BoardService class
	@Autowired
	public BoardController(BoardService boardservice) {
		this.boardservice = boardservice;
	}

	// page link methods
	/*
	 * link to board category page
	 */
	@GetMapping("/category")
	public String boardCategory() throws Exception {

		return "null";
	}

	/*
	 * link to search & detail page id param must be included *optional(no, keytype,
	 * keyword, page)
	 */
	@GetMapping("/search")
	public String boardSearch(@RequestParam("id") String board_id,
			@RequestParam(value = "no", required = false) Integer board_no,
			@RequestParam(value = "keytype", required = false) String keyword_type,
			@RequestParam(value = "keyword", required = false) String keyword,
			@RequestParam(value = "page", required = false) Integer curPage) throws Exception {

		String board_name = boardservice.findBoardName(board_id);

		if (board_name == "null") {
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

	/*
	 * link to board write page id parameter must be included
	 */
	@GetMapping("/write")
	public String boardWrite(@RequestParam("id") String board_id) throws Exception {

		return "/Board/WritePage";
	}

	// get response methods
	// get board's category list
	@GetMapping("/listcategory.do")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listCategory(@RequestParam("id") String board_id) throws Exception {

		Map<String, Object> categoryMap = new HashMap<String, Object>();
		categoryMap.put("list", boardservice.findBoardCategory(board_id));
		categoryMap.put
		return new ResponseEntity<>(categoryMap, HttpStatus.OK);
	}

	// add board's category
	@GetMapping("/addcategory.do")
	@ResponseBody
	public ResponseEntity<Integer> addCategory(@RequestParam("id") String board_id,
			@RequestParam("name") String board_name) throws Exception {

		return new ResponseEntity<>(1, HttpStatus.OK);
	}

	// delete board's category
	@GetMapping("/removecategory.do")
	@ResponseBody
	public ResponseEntity<Integer> removeCategory(@RequestParam("id") String board_id) throws Exception {

		return new ResponseEntity<>(1, HttpStatus.OK);
	}

	// get board's category by KR-leng
	@GetMapping("/translatecategory.do")
	@ResponseBody
	public ResponseEntity<String> translateCategory(@RequestParam("id") String board_id) throws Exception {

		return new ResponseEntity<>("1", HttpStatus.OK);
	}

	//
	@GetMapping("/boardlist.do")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> boardList(@RequestParam("id") String board_id,
			@RequestParam("keytype") String keyword_type, @RequestParam("keyword") String keyword,
			@RequestParam("page") String curPage) throws Exception {

		Map<String, Object> boardMap = new HashMap<String, Object>();
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	//
	@PostMapping("/boarddetail.do")
	@ResponseBody
	public ResponseEntity<BoardVO> boardDetail(@RequestParam("no") String board_no) throws Exception {

		BoardVO boardVO = new BoardVO();
		return new ResponseEntity<>(boardVO, HttpStatus.OK);
	}

	//
	@PostMapping("/boardwrite.do")
	public ResponseEntity<Integer> boardWrite(@RequestBody BoardVO boardVO) throws Exception {

		return new ResponseEntity<>(1, HttpStatus.OK);
	}

	//
	@GetMapping("/boardremove.do")
	@ResponseBody
	public ResponseEntity<Integer> boardRemove(@RequestParam("no") String board_no) throws Exception {

		return new ResponseEntity<>(1, HttpStatus.OK);
	}

}
