package Entities;

public class User_admin {

	private int id;
	private int userId;
	private String title;
	private String content, image_code_qr;

	public String getImage_code_qr() {
		return image_code_qr;
	}

	public void setImage_code_qr(String image_code_qr) {
		this.image_code_qr = image_code_qr;
	}

	public User_admin(int userId, String title, String content, String image_code_qr, int like, int likeCount) {
		super();
		this.userId = userId;
		this.title = title; 
		this.content = content;
		this.image_code_qr = image_code_qr;
		this.like = like;
		this.likeCount = likeCount;
	}

	private int like;

	private int likeCount;

	public void incrementLikeCount() {
		likeCount++;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public User_admin(int id, int userId, String title, String content, int like) {
		this.id = id;
		this.userId = userId;
		this.title = title;
		this.content = content;
		this.like = like;
	}

	public User_admin(int userId, String title, String content, int like) {
		super();
		this.userId = userId;
		this.title = title;
		this.content = content;
		this.like = like;
	}

	public User_admin(String title, String content, int like) {
		super();
		this.title = title;
		this.content = content;
		this.like = like;
	}

	public User_admin() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "User_admin [id=" + id + ", userId=" + userId + ", title=" + title + ", content=" + content + ", like="
				+ like + ", likeCount=" + likeCount + "]";
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getLike() {
		return like;
	}

	public void setLike(int like) {
		this.like = like;
	}
}
