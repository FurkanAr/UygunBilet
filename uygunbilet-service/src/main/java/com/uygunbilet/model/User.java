package com.uygunbilet.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.uygunbilet.model.enums.UserGender;
import com.uygunbilet.model.enums.UserRole;
import com.uygunbilet.model.enums.UserType;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private Integer id;
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "email", nullable = false)
	private String email;
	@Column(name = "password", nullable = false)
	private String password;
	@Column(name = "phone", nullable = false)
	private String phone;
	@Column(name = "create_date")
	private LocalDateTime createDate;
	@Column(name = "type", nullable = false)
	@Enumerated(EnumType.STRING)
	private UserType type;
	@Column(name = "role")
	@Enumerated(EnumType.STRING)
	private UserRole role;
	@Column(name = "gender")
	@Enumerated(EnumType.STRING)
	private UserGender gender;
	@OneToMany(mappedBy = "user")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Ticket> ticketList;

	public User() {
		super();
	}

	public User(String name, String email, String password, String phone, UserType type, UserGender gender) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.type = type;
		this.gender = gender;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public UserGender getGender() {
		return gender;
	}

	public void setGender(UserGender gender) {
		this.gender = gender;
	}

	public List<Ticket> getTicketList() {
		return ticketList;
	}

	public void setTicketList(List<Ticket> ticketList) {
		this.ticketList = ticketList;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", phone=" + phone
				+ ", createDate=" + createDate + ", type=" + type + ", role=" + role + ", gender=" + gender
				+ ", ticketList=" + ticketList + "]";
	}

}
