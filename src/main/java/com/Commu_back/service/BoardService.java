package com.Commu_back.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Commu_back.mapper.BoardMapper;
import com.Commu_back.vo.BoardVO;
import com.Commu_back.vo.PagingVO;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class BoardService {

	private final BoardMapper boardmapper;

	ObjectMapper objectmapper = new ObjectMapper();

	@Autowired
	public BoardService(BoardMapper boardmapper) {
		this.boardmapper = boardmapper;
	}

	// 카테고리 목록 조회
	public Map<String, Object> findCategoryList(String board_category_desc, int page) {

		PagingVO pagingVO = new PagingVO(boardmapper.selectCategoryCount(board_category_desc), 5, page);
		Map<String, Object> category_map = new HashMap<>();
		category_map.put("page", pagingVO);
		category_map.put("list", boardmapper.selectCategoryList(board_category_desc, pagingVO.getStartRow(), pagingVO.getEndRow()));

		return category_map;
	}

	// 카테고리 추가
	@Transactional(rollbackFor = Exception.class)
	public int addCategory(String board_category, String board_category_desc) {

		return boardmapper.insertCategory(board_category, board_category_desc);
	}

	// 카테고리 삭제
	@Transactional(rollbackFor = Exception.class)
	public int removeCategory(String board_category) {

		return boardmapper.deleteCategory(board_category);
	}

	// 카테고리 이름 조회
	public String findCategoryDesc(String board_category) {

		return boardmapper.selectCategoryDesc(board_category);
	}

	// 게시글 목록 조회
	public Map<String, Object> fineBoardList(String board_category, String target, String keyword, Integer page) {

		List<Map<String, Object>> board_list = new ArrayList<>();
		Map<String, Object> board_map = new HashMap<>();

		// 검색 대상 게시글 총 개수
		board_map.put("board_category", board_category);
		board_map.put("target", target);
		board_map.put("keyword", keyword);

		// 페이징 
		PagingVO pagingVO = new PagingVO(boardmapper.selectBoardCount(board_map), 20, page = page != null ? page : 1);
		board_map.put("startRow", pagingVO.getStartRow());
		board_map.put("endRow", pagingVO.getEndRow());
		
		// 게시글 목록 조회
		board_list = boardmapper.selectBoardList(board_map);

		// 맵에 담아 반환
		board_map = new HashMap<>();
		board_map.put("page", pagingVO);
		board_map.put("list", board_list);

		return board_map;

	}

	// 게시글 조회
	public Map<String, Object> fineBoard(int board_no) {

		return boardmapper.selectBoard(board_no);
	}

	// 게시글 추가 및 수정
	@Transactional(rollbackFor = Exception.class)
	public int addBoard(BoardVO boardVO) {

		if(boardVO.getBoard_no() == null)
			boardVO.setBoard_no(0);	
		
		return boardmapper.insertBoard(boardVO);
	}

	// 게시글 삭제
	@Transactional(rollbackFor = Exception.class)
	public int removeBoard(int board_no, String user_id) {

		return boardmapper.deleteBoard(board_no, user_id);
	}

	// 게시글 조회수 추가
	@Transactional(rollbackFor = Exception.class)
	public int addBoardViews(int board_no) {

		return boardmapper.updateBoardViews(board_no);
	}

}
