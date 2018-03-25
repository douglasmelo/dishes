package com.vanhack.dishes.model;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.vanhack.dishes.model.response.ProductResponse;

@Entity
@Table(name = "product")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Product extends DomainModel<Product> {

	private static final long serialVersionUID = -4689342134015705722L;

	public static final String STORE_ID = "storeId";
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String PRICE = "price";
	
	@Column(nullable=false, unique=true)
	private String uuid;
	
	@Column(nullable = false)
	private Long storeId;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String description;
	
	@Column(nullable = false)
	private BigDecimal price;

	public Long getStoreId() {
		return storeId;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	@PrePersist
	public void prePersist() {
	    if (Objects.isNull(uuid)) {
	        uuid = "PDT-" + UUID.randomUUID().toString();
	    }
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((storeId == null) ? 0 : storeId.hashCode());
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
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
		Product other = (Product) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (storeId == null) {
			if (other.storeId != null)
				return false;
		} else if (!storeId.equals(other.storeId))
			return false;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}

	public ProductResponse getProductResponse() {
		return new ProductResponseBuilder(this)
				.withUuid()
				.withStoreId()
				.withName()
				.withDescription()
				.withPrice()
				.build();
	}
	
	public class ProductResponseBuilder {

		private Product product;
		private ProductResponse productResponse;
		
		public ProductResponseBuilder(Product product) {
			this.product = product;
			this.productResponse = new ProductResponse();
		}
		
		public ProductResponseBuilder withUuid() {
			this.productResponse.setProductId(product.getUuid());
			return this;
		}
		
		public ProductResponseBuilder withStoreId() {
			this.productResponse.setStoreId(product.getStoreId());
			return this;
		}
		
		public ProductResponseBuilder withName() {
			this.productResponse.setName(product.getName());
			return this;
		}
		
		public ProductResponseBuilder withDescription() {
			this.productResponse.setDescription(product.getDescription());
			return this;
		}
		
		public ProductResponseBuilder withPrice() {
			this.productResponse.setPrice(product.getPrice());
			return this;
		}
		
		public ProductResponse build() {
			return this.productResponse;
		}
		
	}

}
