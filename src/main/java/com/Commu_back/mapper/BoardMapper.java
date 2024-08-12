package com.Commu_back.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.Commu_back.vo.BoardCategoryVO;

@Mapper
public interface BoardMapper {

	// 카테고리 총 개수 조회
	public int selectCategoryCount(@Param("boardCategoryDesc") String boardCategoryDesc);

	// 카테고리 목록 조회
	public List<BoardCategoryVO> selectCategoryList(@Param("boardCategoryDesc") String boardCategoryDesc,
			@Param("startRow") int startRow, @Param("endRow") int endRow);

	// 카테고리 추가
	public int insertCategory(@Param("boardCategory") String boardCategory,
			@Param("boardCategoryDesc") String boardCategoryDesc);

	// 카테고리 삭제
	public int deleteCategory(@Param("boardCategory") String boardCategory);

	// 카테고리 이름 조회
	public String selectCategoryDesc(@Param("boardCategory") String boardCategory);

	// 게시글 총 개수 조회
	public int selectBoardCount(Map<String, Object> boardMap);

	// 게시글 목록 조회
	public List<Map<String, Object>> selectBoardList(Map<String, Object> boardMap);

	// 게시글 조회
	public Map<String, Object> selectBoard(@Param("boardNo") int boardNo);

	// 게시글 추가 및 수정
	public int insertBoard(Map<String, Object> boardMap);

	// 게시글 삭제
	public int deleteBoard(@Param("boardNo") int boardNo, @Param("userId") String userId);

	// 게시글 조회수 증가
	public int updateBoardViews(@Param("boardNo") int boardNo);

}
