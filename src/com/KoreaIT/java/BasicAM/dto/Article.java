package com.KoreaIT.java.BasicAM.dto;

public class Article {
	public int id;
	public String regDate;
	public String updateDate;
	public String title;
	public String body;
	public int hit;

	public Article(int id, String regDate, String updateDate, String title, String body) {
		this(id, regDate, updateDate, title, body, 0);
	}

	public Article(int id, String regDate, String updateDate, String title, String body, int hit) {
		this.id = id;
		this.regDate = regDate;
		this.updateDate = updateDate;
		this.title = title;
		this.body = body;
		this.hit = hit;
	}

	public void increaseHit() {
		this.hit++;
	}
}