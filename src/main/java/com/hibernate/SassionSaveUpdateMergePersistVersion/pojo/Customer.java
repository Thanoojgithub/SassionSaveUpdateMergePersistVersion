package com.hibernate.SassionSaveUpdateMergePersistVersion.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity(name = "customer")
@Table(name = "customer", schema = "mydb")
public class Customer implements Serializable {

	private static final long serialVersionUID = 6034013743921928008L;

	public Customer(CustomerCID customerCID, String location, Date dOB) {
		super();
		this.customerCID = customerCID;
		this.location = location;
		this.dOB = dOB;
	}

	@EmbeddedId
	private CustomerCID customerCID;

	@Version
	@Column(name = "VERSION", unique = false, nullable = false)
	private Integer ver;

	@Column(name = "LOCATION", unique = false, nullable = false)
	private String location;

	@Temporal(TemporalType.DATE)
	@Column(name = "DOB", unique = false, nullable = false)
	private Date dOB;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public CustomerCID getCustomerCID() {
		return customerCID;
	}

	public void setCustomerCID(CustomerCID customerCID) {
		this.customerCID = customerCID;
	}

	public Integer getVer() {
		return ver;
	}

	public void setVer(Integer ver) {
		this.ver = ver;
	}

	public Date getdOB() {
		return dOB;
	}

	public void setdOB(Date dOB) {
		this.dOB = dOB;
	}

	@Override
	public String toString() {
		return "Customer [customerCID=" + customerCID + ", ver=" + ver + ", location=" + location + ", dOB=" + dOB
				+ ", hashCode()=" + hashCode() + "]";
	}

}
