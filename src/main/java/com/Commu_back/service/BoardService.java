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
	public Map<String, Object> findCategoryList(String boardCategoryDesc, int page) {

		PagingVO pagingVO = new PagingVO(boardmapper.selectCategoryCount(boardCategoryDesc), 5, page);
		Map<String, Object> categoryMap = new HashMap<>();
		categoryMap.put("page", pagingVO);
		categoryMap.put("list",
				boardmapper.selectCategoryList(boardCategoryDesc, pagingVO.getStartRow(), pagingVO.getEndRow()));

		return categoryMap;
	}

	// 카테고리 추가
	@Transactional(rollbackFor = Exception.class)
	public int addCategory(String boardCategory, String boardCategoryDesc) {

		return boardmapper.insertCategory(boardCategory, boardCategoryDesc);
	}

	// 카테고리 삭제
	@Transactional(rollbackFor = Exception.class)
	public int removeCategory(String boardCategory) {

		return boardmapper.deleteCategory(boardCategory);
	}

	// 카테고리 이름 조회
	public String findCategoryDesc(String boardCategory) {

		return boardmapper.selectCategoryDesc(boardCategory);
	}

	// 게시글 목록 조회
	public Map<String, Object> fineBoardList(String boardCategory, String target, String keyword, Integer page) {

		List<Map<String, Object>> boardList = new ArrayList<>();
		Map<String, Object> boardMap = new HashMap<>();

		// 검색 대상 게시글 총 개수
		boardMap.put("boardCategory", boardCategory);
		boardMap.put("target", target);
		boardMap.put("keyword", keyword);

		// 페이징
		PagingVO pagingVO = new PagingVO(boardmapper.selectBoardCount(boardMap), 20, page = page != null ? page : 1);
		boardMap.put("startRow", pagingVO.getStartRow());
		boardMap.put("endRow", pagingVO.getEndRow());

		// 게시글 목록 조회
		boardList = boardmapper.selectBoardList(boardMap);

		// 맵에 담아 반환
		boardMap = new HashMap<>();
		boardMap.put("page", pagingVO);
		boardMap.put("list", boardList);

		return boardMap;

	}

	// 게시글 조회
	public Map<String, Object> fineBoard(int boardNo) {

		return boardmapper.selectBoard(boardNo);
	}

	// 게시글 추가 및 수정
	@Transactional(rollbackFor = Exception.class)
	public int addBoard(BoardVO boardVO) {

		if (boardVO.getBoardNo() == null)
			boardVO.setBoardNo(0);

		return boardmapper.insertBoard(boardVO);
	}

	// 게시글 삭제
	@Transactional(rollbackFor = Exception.class)
	public int removeBoard(int boardNo, String userId) {

		return boardmapper.deleteBoard(boardNo, userId);
	}

	// 게시글 조회수 추가
	@Transactional(rollbackFor = Exception.class)
	public int addBoardViews(int boardNo) {

		return boardmapper.updateBoardViews(boardNo);
	}

}
