package com.mony.bibliotecaDb4o.base;

import java.util.List;


public class Libro {

	private Integer id;
	private Autor autores;
	private String isbn;
	private String titulo;
	private String genero;
	private List<Prestamo> prestamos;

	
	public Libro() {
		
	}

	public Libro(Autor autores, String isbn, String titulo, String genero,
			List<Prestamo> prestamos) {
		this.autores = autores;
		this.isbn = isbn;
		this.titulo = titulo;
		this.genero = genero;
		this.prestamos = prestamos;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Autor getAutores() {
		return autores;
	}

	public void setAutores(Autor autores) {
		this.autores = autores;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public List<Prestamo> getPrestamos() {
		return prestamos;
	}

	public void setPrestamos(List<Prestamo> prestamos) {
		this.prestamos = prestamos;
	}
	@Override
	public String toString(){
		return titulo;
		
	}
	
	
}
