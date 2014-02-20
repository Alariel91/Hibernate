package com.mony.bibliotecaDb4o.base;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Prestamo  {

	private Integer id;
	private Socio socios;
	private String numPedido;
	private Date fechaSalida;
	private Date fechaDevolucion;
	private Set<Libro> libros;

	
	public Prestamo() {
		
	}

	public Prestamo(Socio socios, String numPedido, Date fechaSalida,
			Date fechaDevolucion, Set<Libro> libros) {
		this.socios = socios;
		this.numPedido = numPedido;
		this.fechaSalida = fechaSalida;
		this.fechaDevolucion = fechaDevolucion;
		this.libros = libros;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Socio getSocios() {
		return socios;
	}

	public void setSocios(Socio socios) {
		this.socios = socios;
	}

	public String getNumPedido() {
		return numPedido;
	}

	public void setNumPedido(String numPedido) {
		this.numPedido = numPedido;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public Date getFechaDevolucion() {
		return fechaDevolucion;
	}

	public void setFechaDevolucion(Date fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}

	public Set<Libro> getLibros() {
		return libros;
	}

	public void setLibros(HashSet<Libro> libros) {
		this.libros = libros;
	}

	

	
}
