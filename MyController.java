package servlet4_board;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//URL패턴 경로지정 - 끝문자가 .do로 끝나는 경로에 대해서 지정
//예) http://localhost:8081/servlet4_board/
//예) http://localhost:8081/servlet4_board/list.do
//예) http://localhost:8081/servlet4_board/write.do
//http://localhost:8081/servlet4_board/list.jsp 안됨.
//@WebServlet("*.do")
@WebServlet(urlPatterns= {"/", "*.do"})
public class MyController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}
	void doProcess(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String requestURI = request.getRequestURI();
		
		int cmdIndex = requestURI.lastIndexOf("/")+1;
		String command = requestURI.substring(cmdIndex); 
		if(command.equals("")||command.equals("/")) {
			response.sendRedirect("List");
		}else if(command.equals("List")) {
		
			ArrayList<WriteDto> list = null;
			
			try {
				list = WriteDao.list();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			request.setAttribute("list", list);
			
			RequestDispatcher dp = request.getRequestDispatcher("/List.jsp");
			dp.forward(request, response);
			
		}else if(command.equals("write")) {
			RequestDispatcher dp = request.getRequestDispatcher("/Write.jsp");
			dp.forward(request, response);
		}else if(command.equals("content")) {
			RequestDispatcher dp = request.getRequestDispatcher("/content.jsp");
			dp.forward(request, response);
		}else if(command.equals("WriteAction")) {
			request.setCharacterEncoding("utf-8");
			//아이디,이름으로 로그인 처리를 해줌.
			
			int result = 0;//1 : 로그인 성공, 0:아이디가 없음 , 2:이름없음
			
			try {
				result = WriteDao.writing(request);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(result == 1) { //성공
				response.sendRedirect("content");
			}else if(result ==0 ||result==2) { //실패
				response.sendRedirect("write");
			}
			
			
			
			
	}
		else if(command.equals("content")) {
			String board_idx = request.getParameter("board_idx");
			System.out.println("dd");
			WriteDto dto = null;
			try {
				dto = WriteDao.Info(board_idx);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("dto", dto);
			
			RequestDispatcher dp = request.getRequestDispatcher("/content.jsp");
			dp.forward(request, response);
		}
}//end of doProcess
}//end of class
