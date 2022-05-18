package com.dw.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dw.board.mapper.StudentsMapper;
import com.dw.board.vo.StudentsVO;

@Service
public class StudentsService {
	@Autowired
	private StudentsMapper StudentsMapper;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	//학생 생성
	@Transactional(rollbackFor = Exception.class) 
	public int setStudents(StudentsVO VO) {
		//학생 비밀번호 암호화
		String password = VO.getStudentsPassword();
		password = passwordEncoder.encode(password);
		VO.setStudentsPassword(password);
		return StudentsMapper.insertStudent(VO);
	}
	//학생 조회
	public List<StudentsVO> getStudentsList(){
		return StudentsMapper.selectStudentsList();
	}
	//학생 조회(Map)
	public List<Map<String, Object>> getStudentMapList(){
		return StudentsMapper.selectStudentMapList();
	}
	//특정학생조회(Id)
	public StudentsVO getselectStudents(int studentsId) {
		return StudentsMapper.selectStudents(studentsId);
	}
	//특정학생제거
	@Transactional(rollbackFor = Exception.class)
	public int getDeleteStudents(int studentsId) {
		int rows = StudentsMapper.deleteStudents(studentsId);
		return rows;
	}
	//특정학생수정
	@Transactional(rollbackFor = Exception.class)
	public int getupdateStudents(int studentsId, StudentsVO vo) {
		vo.setStudentsId(studentsId);
		int rows = StudentsMapper.updateStudents(vo);
		return rows;
	}
	
}
