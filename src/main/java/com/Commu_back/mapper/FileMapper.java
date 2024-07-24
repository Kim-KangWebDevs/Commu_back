package com.Commu_back.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.Commu_back.vo.FileVO;

@Mapper
public interface FileMapper {

	// 이미지 추가
	public int insertImage(FileVO fileVO);

	// 이미지 리스트 조회
	public List<FileVO> selectImageList(@Param("boardNo") int boardNo);

}
