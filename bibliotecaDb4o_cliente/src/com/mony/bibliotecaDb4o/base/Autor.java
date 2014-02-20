package com.mony.bibliotecaDb4o.base;

import java.util.List;

public class Autor  {

	private Integer id;
	private String nombre;
	private String apellido;
	private List<Libro> libros;

	public Autor() {
	}

	public Autor(String nombre, String apellido, List<Libro> libros) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.libros = libros;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public List<Libro> getLibros() {
		return libros;
	}

	public void setLibros(List<Libro> libros) {
		this.libros = libros;
	}

	@Override
	public String toString(){
		return nombre+" " +apellido;
		
	}
}
