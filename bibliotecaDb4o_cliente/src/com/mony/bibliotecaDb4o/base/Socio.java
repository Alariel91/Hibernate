package com.mony.bibliotecaDb4o.base;

import java.util.List;

public class Socio {

	private Integer id;
	private String codigo;
	private String nombre;
	private String apellido;
	private String dni;
	private List<Prestamo> prestamos;

	public Socio() {
	}

	public Socio(String codigo, String nombre, String apellido, String dni,
			List<Prestamo> prestamos) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.prestamos = prestamos;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
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

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public List<Prestamo> getPrestamos() {
		return prestamos;
	}

	public void setPrestamos(List<Prestamo> prestamos) {
		this.prestamos = prestamos;
	}

	@Override
	public String toString(){
		return nombre;
		
	}
}
