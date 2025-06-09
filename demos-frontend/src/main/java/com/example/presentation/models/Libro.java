package com.example.presentation.models;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Libro {
	private int id;
	private String titulo, autor;
	private int paginas;
	
	public Libro() {}
	public Libro(int id, String titulo, String autor, int paginas) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.paginas = paginas;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public int getPaginas() {
		return paginas;
	}
	public void setPaginas(int paginas) {
		this.paginas = paginas;
	}
	
}
