package com.dw.board.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dw.board.service.BoradService;
import com.dw.board.vo.BoardVO;
import com.dw.board.vo.StudentsVO;

@RestController
@RequestMapping("/api/v1") 
public class BoardRestContoller {
	@Autowired
	private BoradService boradservice;
	
	@CrossOrigin
	@PostMapping("/board")
	public int callSaveBoard(@RequestBody BoardVO vo) {
		return boradservice.insertBoard(vo);
	}
	
	@CrossOrigin
	@GetMapping("/board")
	public List<Map<String, Object>> callBoardList(){
		return boradservice.getBoardAllList();
	}
	
	
}






