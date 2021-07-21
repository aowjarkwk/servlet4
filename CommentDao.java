package servlet4_board;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

public class CommentDao {
	public static ArrayList<CommentDto> Commentlist(String board_idx) throws SQLException{
		ArrayList<CommentDto> list = new ArrayList<CommentDto>();
		
			
			Connection conn = null; //DB연결 클래스
			ResultSet rs = null; //검색결과를 담는 클래스
			Statement stmt = null; //쿼리(SQL)을 전송하는 클래스
			
			conn = DBConnection.getConnection();
			//SQL 문자열 
			String query = "select * from board b inner join reply r on (b.board_idx = r.reply_board_idx) WHERE board_idx="+board_idx ;
			System.out.println( query );
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
			//next() 다음값이 있는지 T/F 리턴
			while( rs.next() ) {
				int reply_idx = rs.getInt("reply_idx");
				String reply_name = rs.getString("reply_name");
				String reply_content = rs.getString("reply_content");
				String reply_date = rs.getString("reply_date");
				int reply_board_idx = rs.getInt("reply_board_idx");
				
				CommentDto dto = new CommentDto(reply_idx,reply_name,reply_content,reply_date,reply_board_idx);
				list.add(dto);
				}
		
		return list;
	}
	public static int CommentAdd( HttpServletRequest request ) throws SQLException {
		
		Date date_now = new Date(System.currentTimeMillis()); // 현재시간을 가져와 Date형으로 저장한다
		Connection conn = null; //DB연결 클래스
		ResultSet rs = null; //검색결과를 담는 클래스
		Statement stmt = null; //쿼리(SQL)을 전송하는 클래스
		//String custno = request.getParameter("custno");
		String reply_name = request.getParameter("reply_name");
		String reply_content = request.getParameter("reply_content");
		String reply_board_idx = request.getParameter("reply_board_idx");
		String board_idx = request.getParameter("board_idx");
		Date reply_date = date_now;
		System.out.println("board_idx:"+board_idx);
		System.out.println("reply_board_idx:"+reply_board_idx);
		conn = DBConnection.getConnection();
		//SQL 문자열 
		//insert into users_table( custno, custname, phone, address, joindate, grade, city ) 
	    //  values ( 1001, '홍길동', '010-2222-3333', '한양', '20210719', 'A', '01' );
		
		String query = "INSERT INTO reply( reply_idx, reply_name, reply_content,  reply_date, reply_board_idx)" + 
				" VALUES ( reply_board_seq.nextval, "
				+ "'" + reply_name + "'" + ","
				+ "'" + reply_content    + "'" + ","
				+ "'" + reply_date + 	  "'" + "," 
				+ "'" + reply_board_idx + "'" + ")"; 
		//INSERT INTO users_table( custno, custname, phone, address, joindate, grade, city ) 
		//VALUES ( users_table_seq.nextval, '홍길동','010-1111-2222','강남구','2021-07-19','A','01');
		System.out.println( "query:" + query);
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery( query );
			conn.commit(); //실제 DB에 확정
			//dual테이블 
			//
			
//			
		} catch (SQLException e) {
			e.printStackTrace();
			return 2; //실패
		}
		return 1; //성공
	}
	public static int CommentdeleteAction(HttpServletRequest request) {
		Date date_now = new Date(System.currentTimeMillis()); // 현재시간을 가져와 Date형으로 저장한다
		Connection conn = null; //DB연결 클래스
		ResultSet rs = null; //검색결과를 담는 클래스
		Statement stmt = null; //쿼리(SQL)을 전송하는 클래스
		//String custno = request.getParameter("custno");
		String reply_name = request.getParameter("reply_name");
		String reply_content = request.getParameter("reply_content");
		String reply_board_idx = request.getParameter("reply_board_idx");
		String board_idx = request.getParameter("board_idx");
		Date reply_date = date_now;
		String reply_idx = request.getParameter("reply_idx");
		
		
		conn = DBConnection.getConnection();
		//SQL 문자열 
		//insert into users_table( custno, custname, phone, address, joindate, grade, city ) 
	    //  values ( 1001, '홍길동', '010-2222-3333', '한양', '20210719', 'A', '01' );


		String query = "DELETE FROM reply WHERE reply_idx=" + "'"+reply_idx+"'";
				
	System.out.println( "query:" + query);
	try {
	stmt = conn.createStatement();
	rs = stmt.executeQuery( query );
	conn.commit(); //실제 DB에 확정
	} catch (SQLException e) {
	e.printStackTrace();
	return 2; //실패
	}
	return 1; //성공
	}
}
