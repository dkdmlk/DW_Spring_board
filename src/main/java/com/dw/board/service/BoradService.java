package com.dw.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dw.board.mapper.BoardMapper;
import com.dw.board.vo.BoardVO;

@Service
public class BoradService {
	@Autowired
	private BoardMapper boardmapper;
	
	@Transactional(rollbackFor = Exception.class)
	public int insertBoard(BoardVO vo) {
		return boardmapper.insertBoard(vo);
	}
	
	public List<BoardVO> getBoardAllList(){
		return boardmapper.boardAllList();
	}
}
