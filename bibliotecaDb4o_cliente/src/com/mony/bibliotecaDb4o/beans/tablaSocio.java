package com.mony.bibliotecaDb4o.beans;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.db4o.ObjectSet;
import com.db4o.query.Predicate;
import com.mony.bibliotecaDb4o.base.Prestamo;
import com.mony.bibliotecaDb4o.base.Socio;
import com.mony.bibliotecaDb4o.util.Constantes;
import com.mony.bibliotecaDb4o.util.Util;

public class tablaSocio extends JTable{
	private static final long serialVersionUID = 1L;
	private DefaultTableModel modeloDatos;

	public tablaSocio() {
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
	
		modeloDatos.addColumn(Constantes.CODIGO);
		modeloDatos.addColumn(Constantes.NOMBRE_SOCIO);
		modeloDatos.addColumn(Constantes.APELLIDO_SOCIO);
		modeloDatos.addColumn(Constantes.DNI);
	
		
		setModel(modeloDatos);
	}
	
	public void listar() {
		
		// Lista todos los objetos del tipo que se pasa como parámetro
		List<Socio> socios = Util.db.query(Socio.class);
		cargarFilas(socios);
	}
	
	private void cargarFilas(List<Socio> socio) {

		modeloDatos.setNumRows(0);
	
		for (Socio s : socio) {
			Object[] fila = new Object[]{
					s.getCodigo(),
					s.getNombre(),
					s.getApellido(),
					s.getDni()};
			
			modeloDatos.addRow(fila);
		}
	}
	public Socio getSocioSeleccionado() {
		
		int filaSeleccionada = 0;
		
		filaSeleccionada = getSelectedRow();
		if (filaSeleccionada == -1)
			return null;
		
		String codigo = (String) getValueAt(filaSeleccionada, 0);
		Socio socio = new Socio();
		socio.setCodigo(codigo);

		ObjectSet<Socio> resultado = Util.db.queryByExample(socio);
		socio = resultado.next();
		
		return socio;
	}
	
public void listar(final String filtro, int campo) {
		
		Socio socio = null;
		List<Socio> socios = null;
		
		switch (campo) {
		case Constantes.CAMPO_TODOS:
			socios = Util.db.query(new Predicate<Socio>() {
				@Override
				public boolean match(Socio socio) {
					
					if (socio.getCodigo().contains(filtro) || socio.getNombre().contains(filtro)||
							socio.getApellido().contains(filtro) || socio.getDni().contains(filtro))
						return true;
					return false;
				
				}
			});
			break;
		case Constantes.CAMPO_CODIGO:
			socio = new Socio();
			socio.setCodigo(filtro);
			socios = Util.db.queryByExample(socio);
			break;
		case Constantes.CAMPO_NOMBRE_SOCIO:
			socio = new Socio();
			socio.setNombre(filtro);
			socios = Util.db.queryByExample(socio);
			break;
		case Constantes.CAMPO_APELLIDO_SOCIO:
			socio = new Socio();
			socio.setApellido(filtro);
			socios = Util.db.queryByExample(socio);
			break;
		case Constantes.CAMPO_DNI:
			socio = new Socio();
			socio.setDni(filtro);
			socios = Util.db.queryByExample(socio);
			break;
		default:
		}
		
		cargarFilas(socios);
	}
}
