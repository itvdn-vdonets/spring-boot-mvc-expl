package com.exmpl.demo.models;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@Length(min = 0, max = 1000)
	private String mark;
    
    private String model;
    
    private String description;
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String volFullName) {
		this.mark = volFullName;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String volLocation) {
		this.model = volLocation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String volEmail) {
		this.description = volEmail;
	}

	@Override
	public String toString() {
		return "Car{" +
				"id=" + id +
				", mark='" + mark + '\'' +
				", model='" + model + '\'' +
				", description='" + description + '\'' +
				'}';
	}
}