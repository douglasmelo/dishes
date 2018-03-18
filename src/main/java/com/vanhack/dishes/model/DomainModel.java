package com.vanhack.dishes.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Embedded;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;

import com.vanhack.dishes.utils.TimeZones;
import com.vanhack.dishes.utils.ToStringBuilder;

@MappedSuperclass
public class DomainModel<T extends DomainModel<T>> implements Serializable {

	private static final long serialVersionUID = -6093367171678839160L;

	public static final String ID = "id";
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Embedded
	private DateModel dateModel;
	
	@Version
	private Integer version;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DateModel getDateModel() {
		return dateModel;
	}

	public void setDateModel(DateModel dateModel) {
		this.dateModel = dateModel;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
	
	@PrePersist
	public void initializeDates() {
	    LocalDateTime now = LocalDateTime.now(TimeZones.getDefault());
		dateModel = new DateModel(now, now);
	}

	@PreUpdate
	public void updateUpdatedAt() {
		dateModel.setUpdatedAt(LocalDateTime.now(TimeZones.getDefault()));
	}

	@Override
	public String toString() {
		return ToStringBuilder.toString(this);
	}
}
