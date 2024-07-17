package com.Commu_back.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Commu_back.mapper.BoardMapper;
import com.Commu_back.util.DataType;
import com.Commu_back.vo.BoardVO;
import com.Commu_back.vo.PagingVO;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class BoardService {

	private BoardMapper boardmapper;

	private DataType dataType;

	ObjectMapper objectmapper = new ObjectMapper();

	@Autowired
	public BoardService(BoardMapper boardmapper) {
		this.boardmapper = boardmapper;
	}

	// 게시판 카테고리 리스트 조회
	public Map<String, Object> findCategoryList(String board_category, String board_category_desc, String page) {

		Map<String, Object> categoryMap = new HashMap<>();
		categoryMap.put("list", boardmapper.selectCategoryList(board_category));
		categoryMap.put("paging",
				new PagingVO(boardmapper.selectCategoryCount(board_category_desc), 10, dataType.isDigit(page)));
		return categoryMap;
	}

	// 게시판 카테고리 추가
	@Transactional(rollbackFor = Exception.class)
	public int addCategory(String board_category, String board_category_desc, String board_role_no) {

		dataType = new DataType();
		dataType.isDigit(board_role_no);
		
		return dataType.isStatus() == true ? boardmapper.insertCategory(board_category, board_category_desc, dataType.getValue()) : 0;
	}

	// 게시판 카테고리 삭제
	@Transactional(rollbackFor = Exception.class)
	public int removeCategory(String board_category) {

		return boardmapper.deleteCategory(board_category);
	}

	// 게시판 카테고리 접근 권한 수정
	@Transactional(rollbackFor = Exception.class)
	public int modifyCategoryAuth(String board_category, String board_role_no) {

		dataType = new DataType();
		dataType.isDigit(board_role_no);

		return dataType.isStatus() == true ? boardmapper.updateCategoryAuth(board_category, dataType.getValue()) : 0;
	}

	// 카테고리 이름 조회
	public String findBoardDesc(String board_category) {

		return boardmapper.selectCategoryDesc(board_category);
	}

	// 게시글 조회수 추가
	@Transactional(rollbackFor = Exception.class)
	public int addBoardViews(int board_no) {

		return boardmapper.updateBoardViews(board_no);
	}

	// 게시글 리스트 조회
	public Map<String, Object> fineBoardList(String board_category, String target, String keyword, String page) {

		// 1. view에서 받아온 현재 페이지 값이 유효한지 검증 후 숫자형으로 변환 후
		// 페이징을 위한 해당 검색의 게시글 총합 개수 조회 및 페이징VO 생성
		PagingVO pagingVO = new PagingVO(boardmapper.selectBoardCount(board_category, target, keyword), 20,
				dataType.isDigit(page));

		// 3. DB 검색 후 결과 조회
		Map<String, Object> board_map = new HashMap<>();
		board_map.put("board_type", board_category);
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

	// 게시글 조회
	public Map<String, Object> fineBoard(String board_no) {

		return boardmapper.selectBoard(dataType.isDigit(board_no));
	}

	// 게시글 추가
	@Transactional(rollbackFor = Exception.class)
	public int addBoard(BoardVO boardVO, String user_id) {

		Map<String, Object> board_map = objectmapper.convertValue(boardVO, HashMap.class);

		board_map.put("user_id", user_id);

		return boardmapper.insertBoard(board_map);
	}

	// 게시글 삭제
	@Transactional(rollbackFor = Exception.class)
	public int removeBoard(String board_no, String user_id) {

		return boardmapper.deleteBoard(dataType.isDigit(board_no), user_id);
	}

}
