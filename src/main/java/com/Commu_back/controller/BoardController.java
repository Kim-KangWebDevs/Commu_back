package com.Commu_back.controller;

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

	// create a singleton for dependency injection into a BoardService class
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
	 * link to search & detail page
	 * btype param must be included
	 * *optional(no, keytype, keyword, page)
	*/
	@GetMapping("/search")
	public String boardSearch(@RequestParam("btype") String board_type,
			@RequestParam(value = "no", required = false) Integer board_no,
			@RequestParam(value = "keytype", required = false) String keyword_type,
			@RequestParam(value = "keyword", required = false) String keyword,
			@RequestParam(value = "page", required = false) Integer curPage) throws Exception {
	
		
		
		return "null";
	}
	
	/* 
	 * link to board write page
	 * btype parameter must be included 
	*/
	@GetMapping("/write")
	public String boardWrite(@RequestParam("btype") String board_type) throws Exception {
		
		return "null";
	}
	
	// get response methods
	// add board's category 
	@GetMapping("/addcategory.do")
	@ResponseBody
	public ResponseEntity<String> addCategory(@RequestParam("btype") String board_type) throws Exception {
		
		return new ResponseEntity<>("1", HttpStatus.OK);
	}
	
	// delete board's category 
	@GetMapping("/removecategory.do")
	@ResponseBody
	public ResponseEntity<String> removeCategory(@RequestParam("btype") String board_type) throws Exception {
		
		return new ResponseEntity<>("1", HttpStatus.OK);
	}
	
	// get board's category by KR-leng 
	@GetMapping("/translatecategory.do")
	@ResponseBody
	public ResponseEntity<String> translateCategory(@RequestParam("btype") String board_type) throws Exception {
		
		return new ResponseEntity<>("1", HttpStatus.OK);
	}
	
	//
	@GetMapping("/boardlist.do")
	@ResponseBody
	public ResponseEntity<String> boardList(@RequestParam("btype") String board_type) throws Exception {
		
		return new ResponseEntity<>("1", HttpStatus.OK);
	}
	
	//
	@PostMapping("/boarddetail.do")
	@ResponseBody
	public ResponseEntity<String> boardDetail(@RequestParam("btype") String board_type) throws Exception {
		
		return new ResponseEntity<>("1", HttpStatus.OK);
	}
	
	//
	@PostMapping("/boardwrite.do")
	public ResponseEntity<String> boardWrite(@RequestBody BoardVO boardVO) throws Exception {
		
		return new ResponseEntity<>("1", HttpStatus.OK);
	}
	
	//
	@GetMapping("/boardremove.do")
	@ResponseBody
	public ResponseEntity<String> boardRemove(@RequestParam("btype") String board_type) throws Exception {
		
		return new ResponseEntity<>("1", HttpStatus.OK);
	}	
	
}
