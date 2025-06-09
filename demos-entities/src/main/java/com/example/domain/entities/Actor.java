package com.example.domain.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.example.core.domain.entities.AbstractEntity;


/**
 * The persistent class for the actor database table.
 * 
 */
@Entity
@Table(name="actor")
@NamedQuery(name="Actor.findAll", query="SELECT a FROM Actor a")
public class Actor extends AbstractEntity<Actor> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="actor_id", unique=true, nullable=false)
	private int actorId;

	@Column(name="first_name", nullable=false, length=45)
	@NotBlank
	@Size(max=45, min=2)
//	@NIF
	private String firstName;

	@Column(name="last_name", nullable=false, length=45)
	@Size(max=45, min=2)
	@Pattern(regexp = "[A-Z]+", message = "Tiene que estar en mayusculas")
	private String lastName;

	@Column(name="last_update", insertable=false, updatable=false, nullable=false)
	@PastOrPresent
	private Date lastUpdate;

	//bi-directional many-to-one association to FilmActor
	@OneToMany(mappedBy="actor", fetch = FetchType.LAZY)
	private List<FilmActor> filmActors = new ArrayList<>();

	public Actor() {
	}
	
	public Actor(int actorId) {
		super();
		this.actorId = actorId;
	}

	public Actor(int actorId, String firstName, String lastName) {
		super();
		setActorId(actorId);
		setFirstName(firstName);
		setLastName(lastName);
	}


	public Actor(int actorId, String firstName, String lastName, Date lastUpdate) {
		this(actorId, firstName, lastName);
		setLastUpdate(lastUpdate);
	}

	public int getActorId() {
		return this.actorId;
	}

	public void setActorId(int actorId) {
		if (this.actorId == actorId) return;
//		onChange("ActorId", this.actorId, actorId);
		this.actorId = actorId;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		if (this.firstName == firstName) return;
//		onChange("FirstName", this.firstName, firstName);
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		if (this.lastName == lastName) return;
//		onChange("LastName", this.lastName, lastName);
		this.lastName = lastName;
	}

	public Date getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public List<FilmActor> getFilmActors() {
		return this.filmActors;
	}

	public void setFilmActors(List<FilmActor> filmActors) {
		this.filmActors = filmActors;
	}

	public FilmActor addFilmActor(FilmActor filmActor) {
		getFilmActors().add(filmActor);
		filmActor.setActor(this);

		return filmActor;
	}

	public FilmActor removeFilmActor(FilmActor filmActor) {
		getFilmActors().remove(filmActor);
		filmActor.setActor(null);

		return filmActor;
	}

	@Override
	public int hashCode() {
		return Objects.hash(actorId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Actor other = (Actor) obj;
		return actorId == other.actorId;
	}

	@Override
	public String toString() {
		return "Actor [actorId=" + actorId + ", firstName=" + firstName + ", lastName=" + lastName + ", lastUpdate="
				+ lastUpdate + "]";
	}

	public void jubilate() {
		
	}
	
	public void recibePremio(String premio) {
		
	}

}