package com.uygunbiletservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.uygunbiletservice.model.enums.SeatType;
import com.uygunbiletservice.model.enums.UserGender;

@Entity
@Table(name = "passenger")
public class Passenger {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private Integer id;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "gender")
	@Enumerated(EnumType.STRING)
	private UserGender gender;
	@Column(name = "identity_number")
	private String identityNo;
	@Column(name = "seat_id")
	private Integer seatId;
	@Column(name = "seat_no")
	private Integer seatNo;
	@Column(name = "seat_type")
	@Enumerated(EnumType.STRING)
	private SeatType seatType;
	@ManyToOne
	@JoinColumn(name = "payment_id", referencedColumnName = "id")
	private Payment payment;

	public Passenger() {
		super();
	}

	public Passenger(String firstName, String lastName, UserGender gender, String identityNo, Integer seatId,
			Integer seatNo, SeatType seatType, Payment payment) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.identityNo = identityNo;
		this.seatId = seatId;
		this.seatNo = seatNo;
		this.seatType = seatType;
		this.payment = payment;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public UserGender getGender() {
		return gender;
	}

	public void setGender(UserGender gender) {
		this.gender = gender;
	}

	public String getIdentityNo() {
		return identityNo;
	}

	public void setIdentityNo(String identityNo) {
		this.identityNo = identityNo;
	}

	public Integer getSeatId() {
		return seatId;
	}

	public void setSeatId(Integer seatId) {
		this.seatId = seatId;
	}

	public Integer getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(Integer seatNo) {
		this.seatNo = seatNo;
	}

	public SeatType getSeatType() {
		return seatType;
	}

	public void setSeatType(SeatType seatType) {
		this.seatType = seatType;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	@Override
	public String toString() {
		return "Passenger [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender
				+ ", identityNo=" + identityNo + ", seatId=" + seatId + ", seatNo=" + seatNo + ", seatType=" + seatType
				+ ", payment=" + payment + "]";
	}
	
	

}
