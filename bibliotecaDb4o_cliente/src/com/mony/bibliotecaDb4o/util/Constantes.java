package com.mony.bibliotecaDb4o.util;

import java.util.Date;
import java.util.List;



/**
 * Constantes varias
 * @author Santiago Faci
 * @version 1.0
 *
 */
public class Constantes {

	public static final String DATABASE_FILENAME = "biblioteca.db4o";
	
	public static final int SOCIO_ = 0;
	public static final int LIBRO_ = 1;
	public static final int AUTOR_ = 2;
	public static final int PRESTAMO_ = 3;
	

	//tabla autor
	public static final String NOMBRE = "Nombre";
	public static final String APELLIDOS = "Apellidos";
	public static final String LIBROS = "Libros";
	
	public static final int CA_TODOS = 0;
	public static final int CA_NOMBRE = 1;
	public static final int CA_APELLIDO = 2;
	
	//tabla libro
	public static final String ISBN = "Isbn";
	public static final String TITULO = "Titulo";
	public static final String GENERO = "Genero";
	public static final String AUTOR = "Autor";
	public static final String PRESTAMO = "Prestamo";
	
	public static final int CAM_TODOS = 0;
	public static final int CAM_ISBN = 1;
	public static final int CAM_TITULO = 2;
	public static final int CAM_GENERO= 3;
	//tabla prestamo
	public static final String NUMPEDIDO = "N pedido";
	public static final String FECHASALIDA = "Fecha Salida";
	public static final String FECHADEVOLUCION = "Fecha Devolucion";
	public static final String SOCIO = "Socio";
	public static final String LIBRO = "Libro";
	
	public static final int CAMP_TODOS = 0;
	public static final int CAMP_NUMPEDIDO = 1;
	
	
	
	//tabla socio
	
	public static final String CODIGO = "Codigo";
	public static final String NOMBRE_SOCIO = "Nombre";
	public static final String APELLIDO_SOCIO = "Apellido";
	public static final String DNI = "Dni";
	public static final String PRESTAMOS = "Prestamos";
	
	public static final int CAMPO_TODOS = 0;
	public static final int CAMPO_CODIGO= 1;
	public static final int CAMPO_NOMBRE_SOCIO = 2;
	public static final int CAMPO_APELLIDO_SOCIO= 3;
	public static final int CAMPO_DNI= 4;

	
}
