package com.Commu_back.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Commu_back.service.FileService;
import com.Commu_back.vo.FileVO;

@RestController
public class FileController {

	private final static Logger log = LoggerFactory.getLogger(FileController.class);

	private final FileService fileservice;

	@Autowired
	public FileController(FileService fileservice) {
		this.fileservice = fileservice;
	}

	// 서버에 이미지 추가
	@PostMapping("/file/uploadimage.do")
	public ResponseEntity<List<String>> uploadImage(@RequestParam("imageList") List<MultipartFile> imageList)
			throws Exception {

		log.info("서버에 이미지 추가");
		return ResponseEntity.ok(fileservice.addImageFile(imageList));

	}

	// DB에 이미지 추가
	@PostMapping("/file/registimage.do")
	public ResponseEntity<Integer> registImage(@RequestBody FileVO fileVO) throws Exception {

		log.info("DB에 이미지 추가");
		return ResponseEntity.ok(fileservice.addImage(fileVO));

	}

	// 이미지 조회
	@GetMapping(value = "/imageview.do", produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<byte[]> imageDetail(@RequestParam("src") String imageSrc) throws Exception {

		log.info("이미지 조회");
		return ResponseEntity.ok(fileservice.findImageView(imageSrc));
	}

	// 게시글 이미지 리스트 조회
	@PostMapping("/file/getfilelist.do")
	public ResponseEntity<List<FileVO>> fileList(@RequestParam("bno") int boardNo) throws Exception {

		log.info("게시글 이미지 리스트 조회");
		return ResponseEntity.ok(fileservice.findFileList(boardNo));

	}
}
