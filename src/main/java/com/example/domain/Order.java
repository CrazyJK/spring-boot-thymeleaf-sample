package com.example.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity(name = "tbl_order")
public class Order extends AbstractPersistable<Integer> {

	private String orderName;
	private String note;
	private int price;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User user;

	public Order() {

	}

	public Order(String orderName, String note, int price, User user) {
		this.orderName = orderName;
		this.note = note;
		this.price = price;
		this.user = user;
	}
	
	

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Order [orderName=" + orderName + ", note=" + note + ", price=" + price + ", user=" + user + "]";
	}

}
