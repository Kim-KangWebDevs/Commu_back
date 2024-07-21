package com.Commu_back.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Commu_back.mapper.BoardMapper;
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
	public Map<String, Object> fineBoardList(Map<String, Object> board_map) {

		List<Map<String, Object>> board_list = new ArrayList<>();
		int board_total = boardmapper.selectBoardCount(board_map);
		
		PagingVO pagingVO = new PagingVO(board_total, 20, (int) board_map.get("page"));
		board_map.remove("page");
		board_map.put("startRow", pagingVO.getStartRow());
		board_map.put("endRow", pagingVO.getEndRow());
		board_list = boardmapper.selectBoardList(board_map);
		
		board_map.clear();
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
	public int addBoard(Map<String, Object> board_map, String user_id) {

		board_map.put("user_id", user_id);

		return boardmapper.insertBoard(board_map);
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
