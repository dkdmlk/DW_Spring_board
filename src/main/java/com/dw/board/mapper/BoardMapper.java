package com.dw.board.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.dw.board.vo.BoardVO;

@Mapper
public interface BoardMapper {
	/**
	 * @param vo
	 * @return
	 * @author : In Seok
	 * @Date : 2022. 5. 24.
	 * comment : 게시물 만들기
	 */
	public int insertBoard(BoardVO vo);
	//게시글 리스트 가져오기
	/**
	 * @return
	 * @author : In Seok
	 * @Date : 2022. 5. 24.
	 * comment : 게시물 리스트 가져오기
	 */
	public List<Map<String, Object>> boardAllList();
	
	/**
	 * @param board_id
	 * @return
	 * @author : In Seok
	 * @Date : 2022. 5. 26.
	 * comment : 게시물 지우기
	 */
	public int deleteBoard(int board_id);
	
	/**
	 * @param vo
	 * @return
	 * @author : In Seok
	 * @Date : 2022. 5. 26.
	 * comment : 게시물 수정
	 */
	public int updateBoard(BoardVO vo);
	
	/**
	 * @param vo
	 * @return
	 * @author : In Seok
	 * @Date : 2022. 5. 26.
	 * comment : 게시물 가져오기
	 */
	public BoardVO selectBoard(int board_id); 
	
	/**
	 * @param vo
	 * @return
	 * @author : In Seok
	 * @Date : 2022. 5. 27.
	 * comment : 게시물 조회수
	 */
	public int updateBoardViews(BoardVO vo);
	
	public List<Map<String, Object>> selectSearchBoardList(String studentsName);
	
}
