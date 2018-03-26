package com.vanhack.dishes.model.request;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class CustomerRequest extends Request {

	private static final long serialVersionUID = -1667909425363718813L;
	
	@ApiModelProperty(required = true, example = "jorge@gmail.com", position = 1)
	@NotBlank(message = "customer.email.not.blank", groups = {Save.class, Auth.class})
	@JsonProperty
	private String email;

	@ApiModelProperty(required = true, example = "Jorge", position = 2)
	@NotBlank(message = "customer.name.not.blank", groups = {Save.class})
	@JsonProperty
	private String name;
	
	@ApiModelProperty(required = false, example = "Jorge", position = 3)
	@NotBlank(message = "customer.address.not.blank", groups = {Save.class})
	@JsonProperty
	private String address;
	
	@ApiModelProperty(required = false, example = "Jorge", position = 4)
	@NotBlank(message = "customer.password.not.blank", groups = {Save.class, Auth.class})
	@JsonProperty
	private String password;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public interface Save { }
	
	public interface Auth { }
}
