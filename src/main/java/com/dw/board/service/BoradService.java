package com.dw.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dw.board.mapper.BoardMapper;
import com.dw.board.vo.BoardVO;
import com.github.pagehelper.PageHelper;


@Service
public class BoradService {
	@Autowired
	private BoardMapper boardmapper;
	//게시글 수정
	@Transactional(rollbackFor = Exception.class)
	public int insertBoard(BoardVO vo) {
		return boardmapper.insertBoard(vo);
	}
	//게시물 가져오기
	public List<Map<String, Object>> getBoardAllList(int pageNum,int pageSize){
		PageHelper.startPage(pageNum, pageSize);
		
		return boardmapper.boardAllList();
	}
	//게시물삭제
	@Transactional(rollbackFor = Exception.class)
	public int getDeleteBoard(int boardId) {
		int row = boardmapper.deleteBoard(boardId);
		return row;
	}
	//게시물 업데이트
	@Transactional(rollbackFor = Exception.class)
	public int getUpdateBoard(int boardId, BoardVO vo) {
		vo.setBoardId(boardId);
		int row = boardmapper.updateBoard(vo);
		return row;
	}
	//게시물 조회
	public BoardVO getSelectBoard(int board_id) {
		return boardmapper.selectBoard(board_id);
	}
	//게시물 조회수 증가
	public int getUpdateBoardViews(int board_id) {
		//1. 게시판 번호를 이용해서 조회수를 select
		BoardVO vo = boardmapper.selectBoard(board_id);
		int views = vo.getCnt();
		++views;//2.조회수를 1증가 함.
		vo.setCnt(views);
		vo.setBoardId(board_id);
		//3. update
		int rows = boardmapper.updateBoardViews(vo);
		
		return rows;
	}
	//게시물 학생 조회
	public List<Map<String, Object>> getSearchBoardList(String studentsName,int pageNum,int pageSize){
		PageHelper.startPage(pageNum, pageSize);
		return boardmapper.selectSearchBoardList(studentsName);
	}
	
	//학생수 ,게시글수 ,작성자 수 ,총 조회수 통계
	public Map<String, Object> getBoardStatisticsa(){
		return boardmapper.selectBoardStatisticsa();
	}
	
}
