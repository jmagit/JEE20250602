package com.example.core.contracts.domain.repositories;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="pagina")
@XmlAccessorType(XmlAccessType.FIELD)
public class PageModel<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	@XmlAttribute(required=true) 
	int number;
    @XmlAttribute(required=true) 
	int size;
    @XmlElement(required=true) 
	List<T> content;

	public PageModel() {
		this.number = 0;
		this.size = 20;
		try {
			this.content = ArrayList.class.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
   
	public PageModel(int number, int size, List<T> content) {
		super();
		this.number = number;
		this.size = size;
		this.content = content;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	
}
