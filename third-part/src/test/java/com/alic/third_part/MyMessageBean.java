package com.alic.third_part;

import java.io.Serializable;

public class MyMessageBean implements Serializable {
	private static final long serialVersionUID = -975463546785677645L;
	private Long id;
	private String name;
	private Integer age;
	private Float height;
	
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
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Float getHeight() {
		return height;
	}
	public void setHeight(Float height) {
		this.height = height;
	}
}
