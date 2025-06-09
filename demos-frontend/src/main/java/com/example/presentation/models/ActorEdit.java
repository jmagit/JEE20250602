package com.example.presentation.models;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.example.domain.entities.Actor;

@XmlRootElement(name="actor")
@XmlAccessorType(XmlAccessType.FIELD)
public class ActorEdit implements Serializable {
	private static final long serialVersionUID = 1L;

    @XmlAttribute(required=true) 
	private int actorId;
    @XmlElement(required=true) 
	private String firstName;
    @XmlElement(required=true) 
	private String lastName;

	public ActorEdit() { }
	public ActorEdit(int actorId, String firstName, String lastName) {
		super();
		this.actorId = actorId;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public int getActorId() {
		return actorId;
	}
	public void setActorId(int actorId) {
		this.actorId = actorId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	// Con proposito de depuración
	@Override
	public String toString() {
		return "ActorEdit [actorId=" + actorId + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	public static ActorEdit from(Actor source) {
		return new ActorEdit(
				source.getActorId(), 
				source.getFirstName(), 
				source.getLastName()
				);
	}
	public static Actor from(ActorEdit source) {
		return new Actor(
				source.getActorId(), 
				source.getFirstName(), 
				source.getLastName()
				);
	}

}
