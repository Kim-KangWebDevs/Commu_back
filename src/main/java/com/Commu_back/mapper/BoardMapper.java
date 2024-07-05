package com.Commu_back.mapper;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.Commu_back.vo.BoardVO;

@Mapper
public interface BoardMapper {

	// 카테고리 목록 확인
	public ArrayList<Map<String, String>> selectCategoryList(String board_type) throws Exception;

	// 카테고리 추가
	public int insertCategory(@Param("board_type") String board_type, @Param("board_name") String board_name)
			throws Exception;

	// 카테고리 삭제
	public int deleteCategory(String board_type) throws Exception;

	// 카테고리 한글명 확인
	public String selectCategoryName(String board_type) throws Exception;

	// 게시글 목록 확인
	public ArrayList<BoardVO> selectBoardList(String board_type) throws Exception;

	// 게시글 확인
	public BoardVO selectBoard(@Param("board_type") String board_type, @Param("board_no") int board_no)
			throws Exception;

	// 게시글 추가
	public int insertBoard(BoardVO boardVO) throws Exception;

	// 게시글 삭제
	public int deleteBoard(@Param("board_type") String board_type, @Param("board_no") int board_no) throws Exception;

}
