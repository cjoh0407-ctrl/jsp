package com.saeyan.controller.action;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.protocol.a.NativeConstants.IntegerDataType;
import com.saeyan.dao.BoardDAO;
import com.saeyan.dto.BoardVO;

public class BoardUpdateAcion implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//int num = Integer.parseInt(request.getParameter("num"));
		//String name = request.getParameter("name");
		//String pass = request.getParameter("pass");
		//String email = request.getParameter("email");
		//String title = request.getParameter("title");
		//String content = request.getParameter("content");
		
		BoardVO vo = new BoardVO();
		
		vo.setNum(Integer.parseInt(request.getParameter("num")));
		vo.setName(request.getParameter("name"));
		vo.setPass(request.getParameter("pass"));
		vo.setEmail(request.getParameter("email"));
		vo.setTitle(request.getParameter("title"));
		vo.setContent(request.getParameter("content"));
		
		
		BoardDAO dao = BoardDAO.getinstance();
		dao.updateBoard(vo);
		
		//String url = "BoardServlet?command=board_list";
		//response.sendRedirect(url);
		
		new BoardListAction().execute(request, response);
		
		
	}

}
