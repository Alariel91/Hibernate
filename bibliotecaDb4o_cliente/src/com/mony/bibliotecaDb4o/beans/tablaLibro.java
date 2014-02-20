package com.mony.bibliotecaDb4o.beans;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.db4o.ObjectSet;
import com.db4o.query.Predicate;
import com.mony.bibliotecaDb4o.base.Libro;
import com.mony.bibliotecaDb4o.base.Prestamo;
import com.mony.bibliotecaDb4o.util.Constantes;
import com.mony.bibliotecaDb4o.util.Util;

public class tablaLibro extends JTable{
	private static final long serialVersionUID = 1L;
	private DefaultTableModel modeloDatos;

	public tablaLibro() {
		super();
	
		inicializar();
	}
	
	private void inicializar() {

		modeloDatos = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int fila, int columna) {
				return false;
			}
		};
	
		modeloDatos.addColumn(Constantes.ISBN);
		modeloDatos.addColumn(Constantes.TITULO);
		modeloDatos.addColumn(Constantes.GENERO);
		modeloDatos.addColumn(Constantes.AUTOR);

	
		
		setModel(modeloDatos);
	}
	
	public void listar() {
		
		// Lista todos los objetos del tipo que se pasa como parámetro
		List<Libro> libros = Util.db.query(Libro.class);
		cargarFilas(libros);
	}
	
	private void cargarFilas(List<Libro> libro) {

		modeloDatos.setNumRows(0);
	
		for (Libro l : libro) {
			Object[] fila = new Object[]{
					l.getIsbn(),
					l.getTitulo(),
					l.getGenero(),
					l.getAutores().getNombre()};
			
			modeloDatos.addRow(fila);
		}
	}
	public Libro getLibroSeleccionado() {
		
		int filaSeleccionada = 0;
		
		filaSeleccionada = getSelectedRow();
		if (filaSeleccionada == -1)
			return null;
		
		String isbn = (String) getValueAt(filaSeleccionada, 0);
		Libro libro = new Libro();
		libro.setIsbn(isbn);

		ObjectSet<Libro> resultado = Util.db.queryByExample(libro);
		libro = resultado.next();
		
		return libro;
	}
	
public void listar(final String filtro, int campo) {
		
		Libro libro = null;
		List<Libro> libros = null;
		
		switch (campo) {
		case Constantes.CAM_TODOS:
			libros = Util.db.query(new Predicate<Libro>() {
				@Override
				public boolean match(Libro libro) {
					
					if (libro.getIsbn().contains(filtro) || libro.getTitulo().contains(filtro) ||
							libro.getGenero().contains(filtro) ||  libro.getAutores().getNombre().contains(filtro))
						return true;
				
					return false;
				}
			});
			break;
		case Constantes.CAM_ISBN:
			libro = new Libro();
			libro.setIsbn(filtro);
			libros = Util.db.queryByExample(libro);
			break;
		case Constantes.CAM_TITULO:
			libro = new Libro();
			libro.setTitulo(filtro);
			libros = Util.db.queryByExample(libro);
			break;
		case Constantes.CAM_GENERO:
			libro = new Libro();
			libro.setGenero(filtro);
			libros = Util.db.queryByExample(libro);
			break;
		default:
		}
		
		cargarFilas(libros);
	}
}
