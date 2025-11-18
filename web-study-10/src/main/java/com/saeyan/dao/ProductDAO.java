package com.saeyan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.saeyan.dto.ProductVO;

import util.DBManager;

public class ProductDAO {

	private static ProductDAO instance = new ProductDAO();//싱글톤 패턴 디자인
	
	private ProductDAO() {}// 싱글톤이기때문에 프라이빗 생성자
	
	public static ProductDAO getInstance() {
		return instance;
	}
	
	
	//전체 데이타 가져오기!
	public List<ProductVO> selectAllProuducts() { // list 타입으로 반환할거기에 list타입을 사용
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from product order by code desc";
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO vo = null;
		
		try {
			//1. DB 연결
			con = DBManager.getConnection();
			
			//2. sql 구문 전송
			pstmt = con.prepareStatement(sql);
			
			//3. sql 구문 맵핑
			
			//4. sql 구문 실행
			rs = pstmt.executeQuery();
			
			//5. 가져온 데이타 VO클래스에 저장
			while(rs.next()) { 			// 더이상 나올 값이 없을때까지 반복
				vo = new ProductVO();
				vo.setCode(rs.getInt("code"));
				vo.setName(rs.getString("name"));
				vo.setPrice(rs.getInt("price"));
				vo.setPictureUrl(rs.getString("pictureurl"));
				vo.setDescription(rs.getString("description"));
				
				list.add(vo);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(con, pstmt, rs); //DBManager에 메서드 만들어서 호출하는 방식으로 리팩토링
		}
		
		return list;
		
	}//end selectAllProuducts

	//데이터 추가 -------------------------------------------------------
	public void insertProduct(ProductVO vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "insert into product(name, price, pictureurl, description)"
				+ "values(?, ?, ?, ?)";
		
		try {
			// 1. DB연결
			con = DBManager.getConnection();
			
			// 2. sql전송
			pstmt = con.prepareStatement(sql);
			
			// 3. sql 맵핑
			pstmt.setString(1, vo.getName());
			pstmt.setInt(2, vo.getPrice());
			pstmt.setString(3, vo.getPictureUrl());
			pstmt.setString(4, vo.getDescription());
			
			// 4. sql 실행
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(con, pstmt);
		}
	} //end insertProduct ---------------------------------------------------------

	// 전체 데이타 가져오기 --------------------------------------------------------------
	public ProductVO selectProductByCode(String code) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from product where code = ?";
		ProductVO vo = new ProductVO();
		
		try {
			// 1. DB연결
			con = DBManager.getConnection();
			
			// 2. sql전송
			pstmt = con.prepareStatement(sql);
			
			// 3. sql 맵핑
			pstmt.setInt(1, Integer.parseInt(code));
			
			// 4. sql 실행
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo.setCode(rs.getInt("code"));
				vo.setName(rs.getString("name"));
				vo.setPrice(rs.getInt("price"));
				vo.setPictureUrl(rs.getString("pictureurl"));
				vo.setDescription(rs.getString("description"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(con, pstmt, rs);
		}
		return vo;
	} //end selectProductByCode ------------------------------------------
	
	//업데이트
	public void updateProduct(ProductVO vo) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "update product set name=?, price=?, pictureurl=?, "
				+ " description=? where code = ?";
		
		try {
			// 1. DB연결
			con = DBManager.getConnection();
			
			// 2. sql전송
			pstmt = con.prepareStatement(sql);
			
			// 3. sql 맵핑
			pstmt.setString(1, vo.getName());
			pstmt.setInt(2, vo.getPrice());
			pstmt.setString(3, vo.getPictureUrl());
			pstmt.setString(4, vo.getDescription());
			pstmt.setInt(5, vo.getCode());
			
			// 4. sql 실행
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(con, pstmt);
		}
		
	}//end updateProduct -------------------------------------------------------
	
	
	//deleteProduct ---------------------------------------------------------------
	public void deleteProduct(int code) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "delete from product where code = ?";
		
		try {
			// 1. DB연결
			con = DBManager.getConnection();
			
			// 2. sql전송
			pstmt = con.prepareStatement(sql);
			
			// 3. sql 맵핑
			pstmt.setInt(1, code);
			
			// 4. sql 실행
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(con, pstmt);
		}
		
	}// end deletProduct ---------------------------------------------------------
	
	
}
