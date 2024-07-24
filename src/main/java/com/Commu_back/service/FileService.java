package com.Commu_back.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.Commu_back.mapper.FileMapper;
import com.Commu_back.vo.FileVO;

@Service
public class FileService {

	private final String resourcesPath = "C:\\Commu_back\\resuorces\\";
	private final String imagePath = resourcesPath + "images\\";
	private final String defaultImage = imagePath + "default.png";

	private final FileMapper filemapper;

	@Autowired
	public FileService(FileMapper filemapper) {
		this.filemapper = filemapper;
	}

	// 서버에 이미지 추가
	public List<String> addImageFile(List<MultipartFile> imagelist) throws IOException{

		UUID uuid;
		String fileType, fileName;
		List<String> tempimagelist = new ArrayList<>();

		// 파일 디렉토리가 없을시 생성, 기본값 *imagePath 참조
		if (Files.notExists(Paths.get(imagePath))) {
			Files.createDirectories(Paths.get(imagePath));
		}

		// 파일 리스트에 있는 이미지 저장
		for (int i = 0; i < imagelist.size(); i++) {
			// 랜덤 ID값 생성
			uuid = UUID.randomUUID();

			// 파일 확장자 확인
			fileType = FilenameUtils.getExtension(imagelist.get(i).getOriginalFilename());

			// 파일명 생성
			fileName = uuid + "." + fileType;
			
			// 이미지를 저장소에 등록
			imagelist.get(i).transferTo(new File(imagePath + fileName));
			
			// 반환할 리스트에 파일 정보 입력 
			tempimagelist.add(i, fileName);
			
		}

		return tempimagelist;
	}

	// DB에 이미지 추가
	@Transactional(rollbackFor = Exception.class)
	public int addImage(FileVO fileVO) {

		return filemapper.insertImage(fileVO);
	}

	public byte[] findImageView(String imageSrc) throws IOException {

		String imageUrl = imagePath + imageSrc;
		Path getPath = Paths.get(imageUrl);

		// 파일이 존재하면 view로 이미지 반환, 실패시 기본 이미지 반환
		return IOUtils
				.toByteArray(Files.exists(getPath) ? new FileInputStream(imageUrl) : new FileInputStream(defaultImage));
	}

	// DB에서 이미지 조회
	public List<FileVO> findFileList(int boardNo) {

		return filemapper.selectImageList(boardNo);
	}
}
