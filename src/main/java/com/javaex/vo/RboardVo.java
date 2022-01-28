package com.javaex.vo;

public class RboardVo {
	
	//필드
	private int rbNo;
	private String title;
	private String uName;
	private int hit;
	private String regDate;
	private int userNo;
	private String content;
	private int groupNo;
	private int orderNo;
	private int depth;

	
	//생성자
	public RboardVo() {}
	
	public RboardVo(int rbNo, String title, String uName, int hit, String regDate, int userNo, String content,
			int groupNo, int orderNo, int depth) {
		this.rbNo = rbNo;
		this.title = title;
		this.uName = uName;
		this.hit = hit;
		this.regDate = regDate;
		this.userNo = userNo;
		this.content = content;
		this.groupNo = groupNo;
		this.orderNo = orderNo;
		this.depth = depth;
	}

	
	//메소드 g,s
	public int getRbNo() {
		return rbNo;
	}

	public void setRbNo(int rbNo) {
		this.rbNo = rbNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getuName() {
		return uName;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(int groupNo) {
		this.groupNo = groupNo;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}
	
	//메소드 일반
	@Override
	public String toString() {
		return "RboardVo [rbNo=" + rbNo + ", title=" + title + ", uName=" + uName + ", hit=" + hit + ", regDate="
				+ regDate + ", userNo=" + userNo + ", content=" + content + ", groupNo=" + groupNo + ", orderNo="
				+ orderNo + ", depth=" + depth + "]";
	}
}
