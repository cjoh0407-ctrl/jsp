package com.saeyan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.saeyan.dto.BoardVO;

import util.DBManager;

public class BoardDAO {

	private static BoardDAO instance = new BoardDAO();
	private BoardDAO() {}
	
	public static BoardDAO getinstance() {
		return instance;
	}

	//전체 데이터 조회-----------------------------------------------------------------
	
	public List<BoardVO> selectAllBoards() {
		
		Connection con = null;	// DB 연결 객체를 선언(초기값 null)
		PreparedStatement pstmt  = null;	// SQL 실행을 위한 PreparedStatement 선언
		ResultSet rs = null;	// select 결과를 저장할 ResultSet 선언
		String sql = "select * from board order by num desc";	// 실행할 SQL문: 게시판 전체글을 번호 기준 내림차순 정렬

		List<BoardVO> list = new ArrayList<BoardVO>();	// 조회 결과를 저장할 리스트 생성

		/*
		 * board 테이블 구조 참고용
		 * num        int          PK(글번호)
		 * pass       varchar(30)  비밀번호
		 * name       varchar(30)  작성자
		 * email      varchar(30)  이메일
		 * title      varchar(50)  제목
		 * content    varchar(1000) 내용
		 * readcount  int          조회수(기본값 0)
		 * writedate  datetime     작성일
		 */

		try {
		    con = DBManager.getConnection(); // DB 연결 생성
		   
		    pstmt = con.prepareStatement(sql); // SQL 실행 준비
		    
		    rs = pstmt.executeQuery(); // SQL 실행하여 결과 가져오기
		    
		    // 결과 집합(rs)에 데이터가 있을 동안 반복
		    while(rs.next()) {
		      
		        BoardVO vo = new BoardVO();  // 한 행(row)의 데이터를 저장할 BoardVO 객체 생성
		      
		        int num = rs.getInt("num");  // 글번호(num) 조회
		        
		        String name = rs.getString("name"); // 작성자(name) 조회

		        /*
		        // 참고: 나머지 컬럼 조회 예시
		        rs.getString("pass");
		        rs.getString("email");
		        rs.getString("title");
		        rs.getString("content");
		        rs.getInt("readcount");
		        rs.getTimestamp("writedate");
		        */

		        // VO 객체에 값 저장
		        vo.setNum(num);
		        vo.setPass(rs.getString("pass"));
		        vo.setName(name);
		        vo.setEmail(rs.getString("email"));
		        vo.setTitle(rs.getString("title"));
		        vo.setContent(rs.getString("content"));
		        vo.setReadcount(rs.getInt("readcount"));
		        vo.setWritedate(rs.getTimestamp("writedate"));
		       
		        list.add(vo); // 완성된 VO를 리스트에 추가
		    }

		}catch(Exception e) { // SQL 또는 DB 연결 중 오류 발생 시 출력
		    e.printStackTrace();
		}finally {
		    DBManager.close(con, pstmt, rs);   // 사용한 자원(con, pstmt, rs) 정리(닫기)
		}

		return list;	// 최종적으로 게시글 목록 반환
	}//end selectAllBoards()-------------------------------------------------------------------------------

	//start insertBoard ----------------------------------------------------------------------------------------------
	public void insertBoard(BoardVO vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO Board (name, pass, email, title, content)"
				+ "VALUES(?, ?, ?, ?, ?)";
		
		try {
			con = DBManager.getConnection();
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPass());
			pstmt.setString(3, vo.getEmail());
			pstmt.setString(4, vo.getTitle());
			pstmt.setString(5, vo.getContent());
			
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(con, pstmt);
		}
	}//end insertboard--------------------------------------------------------

	//start selectOneByNum--------------------------------------------------------------
	public BoardVO selectOneByNum(int num) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from board where num = ?";
		
		BoardVO vo = new BoardVO();
		
		try {
			con = DBManager.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			
			if(rs.next()) {
				vo.setNum(rs.getInt("num"));
				vo.setPass(rs.getString("pass"));
				vo.setName(rs.getString("name"));
				vo.setEmail(rs.getString("email"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setReadcount(rs.getInt("readcount"));
				vo.setWritedate(rs.getTimestamp("writedate"));
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(con, pstmt, rs);
		}
		return vo;
	}

	
	
	
	
	
	
}

















