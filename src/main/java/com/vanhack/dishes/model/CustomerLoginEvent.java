package com.vanhack.dishes.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.vanhack.dishes.utils.ToStringBuilder;

@Entity
@Table(name="customer_login_event")
public class CustomerLoginEvent extends DomainModel<CustomerLoginEvent> {

	private static final long serialVersionUID = 8908714279322499782L;

	@ManyToOne
	@JoinColumn(name = "customer_id", referencedColumnName = "id")
	private Customer customer;
	
	@Column(nullable = false)
	private String token;
	
	@Column
	private LocalDateTime logOutDate;
	
	@Column
	private LocalDateTime validAt;
	
	@Column(nullable = false)
	private Boolean logged;
	
	public CustomerLoginEvent() {}
	
	public CustomerLoginEvent(Customer customer, String token, LocalDateTime validAt) {
		setCustomer(customer);
		setToken(token);
		setLogged(Boolean.TRUE);
		setValidAt(validAt);
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public LocalDateTime getLogOutDate() {
		return logOutDate;
	}

	public void setLogOutDate(LocalDateTime logOutDate) {
		this.logOutDate = logOutDate;
	}

	public LocalDateTime getValidAt() {
		return validAt;
	}

	public void setValidAt(LocalDateTime validAt) {
		this.validAt = validAt;
	}

	public Boolean isLogged() {
		return logged;
	}

	public void setLogged(Boolean logged) {
		this.logged = logged;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((logOutDate == null) ? 0 : logOutDate.hashCode());
		result = prime * result + ((logged == null) ? 0 : logged.hashCode());
		result = prime * result + ((token == null) ? 0 : token.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerLoginEvent other = (CustomerLoginEvent) obj;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (logOutDate == null) {
			if (other.logOutDate != null)
				return false;
		} else if (!logOutDate.equals(other.logOutDate))
			return false;
		if (logged == null) {
			if (other.logged != null)
				return false;
		} else if (!logged.equals(other.logged))
			return false;
		if (token == null) {
			if (other.token != null)
				return false;
		} else if (!token.equals(other.token))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.toString(this, "customer");
	}
}