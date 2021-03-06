package com.dw.board.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dw.board.conf.WebSecurityCongfig;
import com.dw.board.mapper.StudentsMapper;
import com.dw.board.vo.StudentsVO;
import com.github.pagehelper.PageHelper;

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
	public List<StudentsVO> getStudentsList(int pageNum,int pageSize){
		PageHelper.startPage(pageNum, pageSize);
		return StudentsMapper.selectStudentsList();
	}
	//controller page용
	public List<Map<String, Object>> getStudentControllerMapList(int pageNum,int pageSize){
		PageHelper.startPage(pageNum, pageSize);
		return StudentsMapper.selectStudentMapList();
	}
	//controller serch page용
		public List<Map<String, Object>> getStudentControllerSearchList(String studentsName,int pageNum,int pageSize){
			PageHelper.startPage(pageNum, pageSize);
			return StudentsMapper.selectControllerSearchStudent(studentsName);
		}
	
	//학생 검색 조회
	public List<StudentsVO> getSearchStudent(String studentsName) {
		return StudentsMapper.selectSearchStudent(studentsName);
	}
	
	public List<StudentsVO> getSearchStudentPage(String studentsName,int pageNum,int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		return StudentsMapper.selectSearchStudent(studentsName);
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
	//(로그인시)가입된 학생인지 아닌지 여부 체크!
	public boolean isStudents(StudentsVO vo , HttpSession httpSession) {
		StudentsVO Student = StudentsMapper.selectStudentsOne(vo);
		if(Student == null) { //쿼리결과가 null 리턴
			return false;
		}
		String inputPassword = vo.getStudentsPassword();//HTML 에서 받아온 비밀번호
		String password = Student.getStudentsPassword();//DB에서 가져온 비밀번호
		if(!passwordEncoder.matches(inputPassword, password)) {//비밀번호 체크!
			return false;
		}
		//로그인 성공시 studentsId를 세션에 저장
		httpSession.setAttribute("studentsId", Student.getStudentsId());
		//로그인 성공시 studentsName를 세션에 저장
		httpSession.setAttribute("studentsName", Student.getStudentsName());
		return true;
	}
	
	
}
