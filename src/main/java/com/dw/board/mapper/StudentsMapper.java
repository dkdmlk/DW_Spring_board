package com.dw.board.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import com.dw.board.vo.StudentsVO;

@Mapper
public interface StudentsMapper {
	//코멘트 단축키 : alt + shift + j
	
	
	/**
	 * @param vo
	 * @return 
	 * @author : In Seok
	 * @Date : 2022. 5. 18.
	 * comment : 학생저장
	 */
	public int insertStudent(StudentsVO vo);
	
	/**
	 * @return List<StudentsVO>
	 * @author : In Seok
	 * @Date : 2022. 5. 18.
	 * comment : 학생 전체 조회
	 */
	public List<StudentsVO> selectStudentsList();
	
	/**
	 * @return List<Map<String, Object>>
	 * @author : In Seok
	 * @Date : 2022. 5. 18.
	 * comment : 학생 전체 조회(Map)
	 */
	public List<Map<String, Object>> selectStudentMapList();
	
	/**
	 * @return
	 * @author : In Seok
	 * @Date : 2022. 5. 18.
	 * comment : 특정학생조회(Id)
	 */
	public StudentsVO selectStudents(int studentsId);
	
	/**
	 * @param studentsId
	 * @return
	 * @author : In Seok
	 * @Date : 2022. 5. 18.
	 * comment : 특정학생 제거
	 */
	public int deleteStudents(int studentsId);
	
	/**
	 * @param studentsId
	 * @param vo
	 * @return
	 * @author : In Seok
	 * @Date : 2022. 5. 18.
	 * comment : 특정학생 수정
	 */
	public int updateStudents(StudentsVO vo);
	
	/**
	 * @param vo
	 * @return
	 * @author : In Seok
	 * @Date : 2022. 5. 19.
	 * comment : 학생이름으로 학생정보 조회
	 */
	public StudentsVO selectStudentsOne(StudentsVO vo);
	
	/**
	 * @param studentsName
	 * @return
	 * @author : In Seok
	 * @Date : 2022. 6. 3.
	 * comment : 학생 검색 조회
	 */
	public List<StudentsVO> selectSearchStudent(String studentsName);
	
	public List<Map<String, Object>> selectControllerSearchStudent(String studentsName);
}
