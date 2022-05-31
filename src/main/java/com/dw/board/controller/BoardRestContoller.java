package com.dw.board.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dw.board.service.BoradService;
import com.dw.board.vo.BoardVO;
import com.dw.board.vo.StudentsVO;

@RestController
@RequestMapping("/api/v1") 
public class BoardRestContoller {
	@Autowired
	private BoradService boardservice;
	//게시판 저장(C)
	@CrossOrigin
	@PostMapping("/board")
	public int callSaveBoard(@RequestBody BoardVO vo) {
		return boardservice.insertBoard(vo);
	}
	//게시판 전체 조회(R)
	@CrossOrigin
	@GetMapping("/board")
	public List<Map<String, Object>> callBoardList(){
		return boardservice.getBoardAllList();
	}
	//게시물 삭제(D)
	@CrossOrigin
	@DeleteMapping("/board/boardId/{id}")
	public int callRemoveBoard(@PathVariable("id")int boardId) {
		return boardservice.getDeleteBoard(boardId);
	}
	
	
	//게시물 수정(U)
	@CrossOrigin
	@PatchMapping("/board/boardId/{id}")
	public int callUpdateBoard(@PathVariable("id")int boardId, @RequestBody BoardVO vo) {
		return boardservice.getUpdateBoard(boardId, vo);
	}
	
	
	//게시물 상세보기(R)
	@CrossOrigin
	@GetMapping("/board/boardId/{id}")
	public BoardVO callBoard(@PathVariable("id")int boardId) {
		return boardservice.getSelectBoard(boardId);
	}
	
	//게시물 카운트
	@CrossOrigin
	@PatchMapping("/board/views/boardId/{id}")
	public int callBoardViews(@PathVariable("id")int boardId) {
		System.out.println(boardId);
		return boardservice.getUpdateBoardViews(boardId);
	}
	
	//쿼리 스트링으로 검색한 작성자 게시판 리스트 조회
	@CrossOrigin
	@GetMapping("/board/search")
	public List<Map<String, Object>> callBoardSearch(@RequestParam("writer")String writer){
		return boardservice.getSearchBoardList(writer);
	}
	
	//학생수 ,게시글수 ,작성자 수 ,총 조회수 통계
	@CrossOrigin
	@GetMapping("/board/statisticsa")
	public Map<String, Object> callBoardStatisticsa(){
		return boardservice.getBoardStatisticsa();
	}
}






