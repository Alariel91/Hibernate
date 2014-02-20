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

public class tablaPrestamo extends JTable{
	private static final long serialVersionUID = 1L;
	private DefaultTableModel modeloDatos;

	public tablaPrestamo() {
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
	
		modeloDatos.addColumn(Constantes.NUMPEDIDO);
		modeloDatos.addColumn(Constantes.FECHASALIDA);
		modeloDatos.addColumn(Constantes.FECHADEVOLUCION);
		modeloDatos.addColumn(Constantes.SOCIO);
		modeloDatos.addColumn(Constantes.LIBRO);
	
		
		setModel(modeloDatos);
	}
	
	public void listar() {
		
		// Lista todos los objetos del tipo que se pasa como parámetro
		List<Prestamo> prestamos = Util.db.query(Prestamo.class);
		cargarFilas(prestamos);
	}
	
	private void cargarFilas(List<Prestamo> prestamo) {

		modeloDatos.setNumRows(0);
	
		for (Prestamo p : prestamo) {
			Object[] fila = new Object[]{
					p.getNumPedido(),
					p.getFechaSalida(),
					p.getFechaDevolucion(),
					p.getSocios(),
					p.getLibros()};
			
			modeloDatos.addRow(fila);
		}
	}
	public Prestamo getPrestamoSeleccionado() {
		
		int filaSeleccionada = 0;
		
		filaSeleccionada = getSelectedRow();
		if (filaSeleccionada == -1)
			return null;
		
		String numpedido = (String) getValueAt(filaSeleccionada, 0);
		Prestamo prestamo = new Prestamo();
		prestamo.setNumPedido(numpedido);

		ObjectSet<Prestamo> resultado = Util.db.queryByExample(prestamo);
		prestamo = resultado.next();
		
		return prestamo;
	}
	
public void listar(final String filtro, int campo) {
		
	Prestamo prestamo = null;
		List<Prestamo> prestamos = null;
		
		switch (campo) {
		case Constantes.CAMP_TODOS:
			prestamos = Util.db.query(new Predicate<Prestamo>() {
				@Override
				public boolean match(Prestamo prestamo) {
					
					if (prestamo.getNumPedido().contains(filtro) ||
							prestamo.getSocios().getNombre().contains(filtro))
						return true;	
					
					return false;
				}
			});
			break;
		case Constantes.CAMP_NUMPEDIDO:
			prestamo = new Prestamo();
			prestamo.setNumPedido(filtro);
			prestamos = Util.db.queryByExample(prestamo);
			break;
		
		default:
		}
		
		cargarFilas(prestamos);
	}
}
