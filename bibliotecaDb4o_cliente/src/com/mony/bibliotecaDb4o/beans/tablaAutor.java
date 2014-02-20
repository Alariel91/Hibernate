package com.mony.bibliotecaDb4o.beans;


import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;





import com.db4o.ObjectSet;
import com.db4o.query.Predicate;
import com.mony.bibliotecaDb4o.base.Autor;
import com.mony.bibliotecaDb4o.base.Libro;
import com.mony.bibliotecaDb4o.util.Constantes;
import com.mony.bibliotecaDb4o.util.Util;

public class tablaAutor extends JTable {
	private static final long serialVersionUID = 1L;
	private DefaultTableModel modeloDatos;

	public tablaAutor() {
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
	
		modeloDatos.addColumn(Constantes.NOMBRE);
		modeloDatos.addColumn(Constantes.APELLIDOS);

	
		setModel(modeloDatos);
	}
	
	public void listar() {
		
		// Lista todos los objetos del tipo que se pasa como parámetro
		List<Autor> centros = Util.db.query(Autor.class);
		cargarFilas(centros);
	}
	
	private void cargarFilas(List<Autor> autor) {

		modeloDatos.setNumRows(0);
	
		for (Autor a : autor) {
			Object[] fila = new Object[]{
					a.getNombre(),
					a.getApellido()};
			
			modeloDatos.addRow(fila);
		}
	}
	public Autor getAutorSeleccionado() {
		
		int filaSeleccionada = 0;
		
		filaSeleccionada = getSelectedRow();
		if (filaSeleccionada == -1)
			return null;
		
		String nombre = (String) getValueAt(filaSeleccionada, 0);
		Autor autor = new Autor();
		autor.setNombre(nombre);

		ObjectSet<Autor> resultado = Util.db.queryByExample(autor);
		autor = resultado.next();
		
		return autor;
	}
	
	
	
public void listar(final String filtro, int campo) {
		
		Autor autor = null;
		List<Autor> autores = null;
		
		switch (campo) {
		case Constantes.CA_TODOS:
			autores = Util.db.query(new Predicate<Autor>() {
				@Override
				public boolean match(Autor autor) {
					
					if (autor.getNombre().contains(filtro) || autor.getApellido().contains(filtro))
						return true;
			
			
					return false;
				}
			});
			break;
		case Constantes.CA_NOMBRE:
			autor = new Autor();
			autor.setNombre(filtro);
			autores = Util.db.queryByExample(autor);
			break;
		case Constantes.CA_APELLIDO:
			autor = new Autor();
			autor.setApellido(filtro);
			autores = Util.db.queryByExample(autor);
			break;
		default:
		}
		
		cargarFilas(autores);
	}
}
