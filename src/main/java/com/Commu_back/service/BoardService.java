package com.Commu_back.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Commu_back.mapper.BoardMapper;
import com.Commu_back.util.DataType;
import com.Commu_back.vo.BoardVO;
import com.Commu_back.vo.PagingVO;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class BoardService {

	private BoardMapper boardmapper;
	
	private DataType datatype;
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired
	public BoardService(BoardMapper boardmapper) {
		this.boardmapper = boardmapper;
	}

	public int addBoardViews(int board_no) throws Exception {

		return boardmapper.updateBoardViews(board_no);
	}
	
//	public List<Map<String, String>> findCategory(String board_id) throws Exception {
//
//		return boardmapper.selectCategoryList(board_id);
//	}

//	public int addCategory(String board_id, String board_name) throws Exception {
//
//		return boardmapper.insertCategory(board_id, board_name);
//	}
	
//	public int removeCategory(String board_id) throws Exception {
//
//		return boardmapper.deleteCategory(board_id);
//	}
	
	// 카테고리 이름 조회
	public String findBoardName(String board_id) throws Exception {

		return boardmapper.selectCategoryName(board_id);
	}

	public Map<String, Object> fineBoardList(String board_id, String target, String keyword, String page)
			throws Exception {
		
		// 1. view에서 받아온 현재 페이지 값이 유효한지 검증 후 숫자형으로 변환 후
		// 페이징을 위한 해당 검색의 게시글 총합 개수 조회 및 페이징VO 생성 
		PagingVO pagingVO = new PagingVO(boardmapper.selectBoardCount(board_id, target, keyword), 20, datatype.isDigit(page));
		
		// 3. DB 검색 후 결과 조회    
		Map<String, Object> board_map = new HashMap<String, Object>();
		board_map.put("board_type", board_id);
		board_map.put("keyword_type", target);
		board_map.put("keyword", keyword);
		board_map.put("startRow", pagingVO.getStartRow());
		board_map.put("endRow", pagingVO.getEndRow());		
		List<Map<String, Object>> board_list = boardmapper.selectBoardList(board_map);
		
		// 4. Map에 글 목록과 페이징 정보를 담아 반환 
		board_map = new HashMap<String, Object>();
		board_map.put("paging", pagingVO);
		board_map.put("list", board_list);
		
		return board_map;
		
	}

	public BoardVO fineBoard(int board_no) throws Exception {

		return boardmapper.selectBoard(board_no);
	}

	public int addBoard(BoardVO boardVO, String user_id) throws Exception {
	
		Map<String, Object> board_map = objectMapper.convertValue(boardVO, HashMap.class);
		
		board_map.put("user_id", user_id);
		
		return boardmapper.insertBoard(board_map);
	}

	public int removeBoard(String board_no, String user_id) throws Exception {

		return boardmapper.deleteBoard(datatype.isDigit(board_no), user_id);
	}

}
