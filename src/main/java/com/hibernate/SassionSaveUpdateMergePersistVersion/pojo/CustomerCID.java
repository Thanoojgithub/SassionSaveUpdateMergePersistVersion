package com.hibernate.SassionSaveUpdateMergePersistVersion.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CustomerCID implements Serializable {

	private static final long serialVersionUID = 6209419288857419038L;

	public CustomerCID(Integer cId, String cName) {
		super();
		this.cId = cId;
		this.cName = cName;
	}

	@Column(name = "CID", unique = true, nullable = false)
	private Integer cId;

	@Column(name = "CNAME", nullable = false)
	private String cName;

	public Integer getcId() {
		return cId;
	}

	public void setcId(Integer cId) {
		this.cId = cId;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	@Override
	public String toString() {
		return "CustomerCID [cId=" + cId + ", cName=" + cName + ", hashCode()=" + hashCode() + "]";
	}

}
