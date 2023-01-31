package com.uygunbiletservice.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mail")
public class Mail {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private Integer id;
	@Column(name = "user_name")
	private String userName;
	@Column(name = "email")
	private String email;
	@Column(name = "company")
	private String company;
	@Column(name = "title")
	private String title;
	@Column(name = "content")
	private String content;
	@Column(name = "create_date")
	private LocalDateTime createDate;

	public Mail() {
		super();
	}

	public Mail(String userName, String email, String company, String title, String content) {
		super();
		this.userName = userName;
		this.email = email;
		this.company = company;
		this.title = title;
		this.content = content;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
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

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "Mail [mailId=" +id + ", userName=" + userName + ", email=" + email + ", company=" + company
				+ ", title=" + title + ", content=" + content + ", createDate=" + createDate + "]";
	}

}
