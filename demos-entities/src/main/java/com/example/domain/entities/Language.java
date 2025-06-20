package com.example.domain.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.example.core.domain.entities.AbstractEntity;

import java.util.Date;
import java.util.List;
import java.util.Objects;


/**
 * The persistent class for the language database table.
 * 
 */
@Entity
@Table(name="language")
@NamedQuery(name="Language.findAll", query="SELECT l FROM Language l")
public class Language extends AbstractEntity<Language> implements Serializable {
	private static final long serialVersionUID = 1L;
    public static class Partial {}
    public static class Complete extends Partial {}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="language_id")
	private int languageId;

	@NotBlank
	@Size(max=20)
	private String name;

	@Column(name="last_update", insertable = false, updatable = false)
	private Date lastUpdate;

	//bi-directional many-to-one association to Film
	@OneToMany(mappedBy="language")
	private List<Film> films;

	//bi-directional many-to-one association to Film
	@OneToMany(mappedBy="languageVO")
	private List<Film> filmsVO;

	public Language() {
	}

	public Language(int languageId) {
		this.languageId = languageId;
	}

	public Language(int languageId, @NotBlank @Size(max = 20) String name) {
		this.languageId = languageId;
		this.name = name;
	}

	public int getLanguageId() {
		return this.languageId;
	}

	public void setLanguageId(int languageId) {
		this.languageId = languageId;
	}

	public Date getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Film> getFilms() {
		return this.films;
	}

	public void setFilms(List<Film> films) {
		this.films = films;
	}

	public Film addFilm(Film film) {
		getFilms().add(film);
		film.setLanguage(this);

		return film;
	}

	public Film removeFilm(Film film) {
		getFilms().remove(film);
		film.setLanguage(null);

		return film;
	}

	public List<Film> getFilmsVO() {
		return this.filmsVO;
	}

	public void setFilmsVO(List<Film> filmsVO) {
		this.filmsVO = filmsVO;
	}

	public Film addFilmsVO(Film filmsVO) {
		getFilmsVO().add(filmsVO);
		filmsVO.setLanguageVO(this);

		return filmsVO;
	}

	public Film removeFilmsVO(Film filmsVO) {
		getFilmsVO().remove(filmsVO);
		filmsVO.setLanguageVO(null);

		return filmsVO;
	}

	@Override
	public int hashCode() {
		return Objects.hash(languageId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj instanceof Language)
			return languageId == ((Language)obj).languageId;
		else
			return false;
	}
	
	@Override
	public String toString() {
		return "Language [languageId=" + languageId + ", name=" + name + "]";
	}

}