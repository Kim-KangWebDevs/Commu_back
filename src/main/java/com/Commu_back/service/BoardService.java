package com.Commu_back.service;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Commu_back.mapper.BoardMapper;

@Service
public class BoardService {

	private BoardMapper boardmapper;

	@Autowired
	public BoardService(BoardMapper boardmapper) {
		this.boardmapper = boardmapper;
	}

	// 카테고리 이름 조회
	public String findBoardName(String board_id) throws Exception {

		return boardmapper.selectCategoryName(board_id);
	}

	public int addBoardViews(int board_no) throws Exception {

		return boardmapper.updateBoardViews(board_no);
	}

	public ArrayList<Map<String, String>> findBoardCategory(String board_id) throws Exception {

		return boardmapper.selectCategoryList(board_id);
	}
	
	

}
