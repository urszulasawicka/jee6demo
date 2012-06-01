package com.example.jeedemo.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

@Entity
@NamedQuery(name = "resource.assigned", query = "Select r from Resource r where r.assigned = false")
public class Resource {
	
	private Long id;
	private String name = new String("Archiwum");
	private int yearFrom;
	private int yearTo;
	private String content = new String("Zasoby");
	private Date introductionDate = new Date();
	private Boolean assigned = false;
	
	public Resource(){
	}
	
	public Resource(Long id, String name, int yearFrom, int yearTo, String content, Date introductionDate, Boolean assigned){
		this.id = id;
		this.name = name;
		this.yearFrom = yearFrom;
		this.yearTo = yearTo;
		this.content = content;
		this.introductionDate = introductionDate;
		this.assigned = assigned;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getYearFrom() {
		return yearFrom;
	}
	public void setYearFrom(int yearFrom) {
		this.yearFrom = yearFrom;
	}

	public int getYearTo() {
		return yearTo;
	}
	public void setYearTo(int yearTo) {
		this.yearTo = yearTo;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Boolean getAssigned() {
		return assigned;
	}
	public void setAssigned(Boolean assigned) {
		this.assigned = assigned;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getIntroductionDate() {
		return introductionDate;
	}
	public void setIntroductionDate(Date introductionDate) {
		this.introductionDate = introductionDate;
	}

}
