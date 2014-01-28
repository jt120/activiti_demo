/**
 * @author liuze
 *
 * Jan 27, 2014
 */
package com.jt.leave.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "JPA_ENTITY_FIELD")
public class LoanRequestBean {
	
	private int id;
	private String customerName;
	private double amount;
	
	public void newLoanRequest(String customerName, double amount) {
		this.customerName = customerName;
		this.amount = amount;
	}
	
	public LoanRequestBean() {}
	
	@Id
	@Column(name="ID_")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	

}
