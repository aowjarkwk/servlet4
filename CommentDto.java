package servlet4_board;

public class CommentDto {
	private int reply_idx;
	private String reply_name;
	private String reply_content;
	private String reply_date;
	private int reply_board_idx;
	public CommentDto() {
		super();
	}
	public CommentDto(int reply_idx, String reply_name, String reply_content, String reply_date, int reply_board_idx) {
		super();
		this.reply_idx = reply_idx;
		this.reply_name = reply_name;
		this.reply_content = reply_content;
		this.reply_date = reply_date;
		this.reply_board_idx = reply_board_idx;
	}
	public int getReply_idx() {
		return reply_idx;
	}
	public void setReply_idx(int reply_idx) {
		this.reply_idx = reply_idx;
	}
	public String getReply_name() {
		return reply_name;
	}
	public void setReply_name(String reply_name) {
		this.reply_name = reply_name;
	}
	public String getReply_content() {
		return reply_content;
	}
	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}
	public String getReply_date() {
		return reply_date;
	}
	public void setReply_date(String reply_date) {
		this.reply_date = reply_date;
	}
	public int getReply_board_idx() {
		return reply_board_idx;
	}
	public void setReply_board_idx(int reply_board_idx) {
		this.reply_board_idx = reply_board_idx;
	}
}
