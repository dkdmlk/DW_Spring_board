package com.dw.board.service;

import java.util.List;
import java.util.Map;

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
	
	public List<Map<String, Object>> getBoardAllList(){
		List<Map<String, Object>> list = boardmapper.boardAllList();
//		for(int i=0; i<list.size();++i) {
//			if(list.get(i).get("updateAt").equals(null)) {
//				list.get(i).replace("updateAt", "-");
//				return list;
//			}		
//		}
		return boardmapper.boardAllList();
	}
}
