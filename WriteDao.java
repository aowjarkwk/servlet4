package servlet4_board;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;





public class WriteDao {

//글쓰기 처리

public static int writing( HttpServletRequest request ) throws SQLException {
	Date date_now = new Date(System.currentTimeMillis()); // 현재시간을 가져와 Date형으로 저장한다
	Connection conn = null; //DB연결 클래스
	ResultSet rs = null; //검색결과를 담는 클래스
	Statement stmt = null; //쿼리(SQL)을 전송하는 클래스
	//String custno = request.getParameter("custno");
	String board_name = request.getParameter("board_name");
	String board_title = request.getParameter("board_title");
	String board_content = request.getParameter("board_content");
	Date board_date = date_now;
	
	conn = DBConnection.getConnection();
	//SQL 문자열 
	//insert into users_table( custno, custname, phone, address, joindate, grade, city ) 
    //  values ( 1001, '홍길동', '010-2222-3333', '한양', '20210719', 'A', '01' );
	
	String query = "INSERT INTO board( board_idx, board_name, board_title, board_content, board_date)" + 
			" VALUES ( board_seq.nextval, "
			+ "'" + board_name + "'" + ","
			+ "'" + board_title    + "'" + ","
			+ "'" + board_content + "'" + ","
			+ "'" + board_date + "'" + ")"; 
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
	public static int Update( HttpServletRequest request ) throws SQLException {
		Date date_now = new Date(System.currentTimeMillis()); // 현재시간을 가져와 Date형으로 저장한다
		Connection conn = null; //DB연결 클래스
		ResultSet rs = null; //검색결과를 담는 클래스
		Statement stmt = null; //쿼리(SQL)을 전송하는 클래스
		//String custno = request.getParameter("custno");
		String board_name = request.getParameter("board_name");
		System.out.println(board_name);
		String board_title = request.getParameter("board_title");
		String board_content = request.getParameter("board_content");
		String board_idx = request.getParameter("board_idx");
		Date board_date = date_now;
		
		conn = DBConnection.getConnection();
		//SQL 문자열 
		//insert into users_table( custno, custname, phone, address, joindate, grade, city ) 
	    //  values ( 1001, '홍길동', '010-2222-3333', '한양', '20210719', 'A', '01' );
		
		String query = "UPDATE board SET board_name=" + "'" + board_name +"'" + "," +
				"board_title=" + "'" + board_title +"'" + "," +
				"board_content=" + "'" + board_content +"'" + "," +
				"board_date=" + "'" + board_date +"'" +
				"WHERE board_idx=" + board_idx;
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
}//end of writing
public static int deleteAction(HttpServletRequest request) {
	Date date_now = new Date(System.currentTimeMillis()); // 현재시간을 가져와 Date형으로 저장한다
	Connection conn = null; //DB연결 클래스
	ResultSet rs = null; //검색결과를 담는 클래스
	Statement stmt = null; //쿼리(SQL)을 전송하는 클래스
	//String custno = request.getParameter("custno");
	String board_name = request.getParameter("board_name");
	System.out.println(board_name);
	String board_title = request.getParameter("board_title");
	String board_content = request.getParameter("board_content");
	String board_idx = request.getParameter("board_idx");
	Date board_date = date_now;
	
	conn = DBConnection.getConnection();
	//SQL 문자열 
	//insert into users_table( custno, custname, phone, address, joindate, grade, city ) 
    //  values ( 1001, '홍길동', '010-2222-3333', '한양', '20210719', 'A', '01' );


	String query = "DELETE FROM board WHERE board_idx=" + "'"+board_idx+"'";
			
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
public static ArrayList<WriteDto> list() throws SQLException{
	ArrayList<WriteDto> list = new ArrayList<WriteDto>();
	
		
		Connection conn = null; //DB연결 클래스
		ResultSet rs = null; //검색결과를 담는 클래스
		Statement stmt = null; //쿼리(SQL)을 전송하는 클래스
		
		conn = DBConnection.getConnection();
		//SQL 문자열 
		String query = "SELECT * FROM board" ;
		System.out.println( query );
		stmt = conn.createStatement();
		rs = stmt.executeQuery(query);
		
		//next() 다음값이 있는지 T/F 리턴
		while( rs.next() ) {
			
			int board_idx = rs.getInt("board_idx");
			String board_name = rs.getString("board_name");
			String board_title = rs.getString("board_title");
			String board_content = rs.getString("board_content");
			String board_date = rs.getString("board_date");
			int board_hit = rs.getInt("board_hit");
			
			WriteDto dto = new WriteDto(board_idx,board_name,board_title,board_content,board_date,board_hit);
			list.add(dto);
			}
	
	return list;
}

//글정보 조회
public static WriteDto Info(String board_idx) throws SQLException {
	WriteDto dto = new WriteDto();
	
		Connection conn = null; //DB연결 클래스
		ResultSet rs = null; //검색결과를 담는 클래스
		Statement stmt = null; //쿼리(SQL)을 전송하는 클래스
		conn = DBConnection.getConnection();
		//SQL 문자열 
		String query = "SELECT * FROM board WHERE board_idx=" + board_idx;
		System.out.println( query );
		stmt = conn.createStatement();
		rs = stmt.executeQuery(query);
		
		//next() 다음값이 있는지 T/F 리턴
		while( rs.next() ) {
			dto.setBoard_idx(rs.getInt("Board_idx"));
			dto.setBoard_name(rs.getString("Board_name"));
			dto.setBoard_title(rs.getString("Board_title"));
			dto.setBoard_date(rs.getString("Board_date"));
			dto.setBoard_content(rs.getString("Board_content"));
			dto.setBoard_hit(rs.getInt("Board_hit"));
			}


	return dto;
}

}

