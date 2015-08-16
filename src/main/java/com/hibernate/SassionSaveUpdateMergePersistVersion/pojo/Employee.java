package com.hibernate.SassionSaveUpdateMergePersistVersion.pojo;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * @author thanooj
 *
 */
@Entity(name="employee")
@Table(name = "employee", schema = "mydb")
public class Employee implements Serializable {

	private static final long serialVersionUID = -3055115665848357376L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "eIdNextValGen")
	@GenericGenerator(name = "eIdNextValGen", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "employeeIdGen") })
	@Column(name = "EID", unique = true, nullable = false)
	private Integer eId;
	
	private String eName;
	
	@Version
	@Column(name = "VERSION", unique = false, nullable = false)
	private Integer ver;
	
	public Employee() {
	}

	public Employee(String eName) {
		super();
		this.eName = eName;
	}

	public Integer geteId() {
		return eId;
	}

	public void seteId(Integer eId) {
		this.eId = eId;
	}

	public String geteName() {
		return eName;
	}

	public void seteName(String eName) {
		this.eName = eName;
	}

	public Integer getVer() {
		return ver;
	}

	public void setVer(Integer ver) {
		this.ver = ver;
	}

	@Override
	public String toString() {
		return "Employee [eId=" + eId + ", eName=" + eName + ", ver=" + ver + "]";
	}

}
