package com.Commu_back.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.Commu_back.vo.FileVO;

@Mapper
public interface FileMapper {

	// 이미지 추가
	public int insertImage(Map<String, Object> image_map) throws Exception;

	// 이미지 리스트 조회
	public List<FileVO> selectImageList(@Param("board_category") String board_category, @Param("board_no") int board_no)
			throws Exception;

}
