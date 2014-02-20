package com.mony.bibliotecaDb4o.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JTextField;
import javax.swing.JLabel;








import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JScrollPane;
import javax.swing.JList;







import com.mony.bibliotecaDb4o.base.Libro;
import com.mony.bibliotecaDb4o.base.Prestamo;
import com.mony.bibliotecaDb4o.base.Socio;
import com.mony.bibliotecaDb4o.util.Util;
import com.mony.bibliotecaDb4o.util.Util.Accion;
import com.toedter.calendar.JDateChooser;

public class JPrestamo extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	private String numprestamo;
	private Date fechaSalida;
	private Date fechaDevolucion;

	private JTextField txtNumPrestamo;
	private JDateChooser dateSalida;
	private JDateChooser dateDevolucion;
	
	private Accion accion;
	
	private JList<Socio> listSocios;
	private JList<Libro> listLibros;
	
	private DefaultListModel<Socio> modeloListaSocios;
	private DefaultListModel<Libro> modeloListaLibros;
	//solo se puede elegir un socio
	private Socio socioSeleccionado;
	//se puede elegir varios libros para el prestamo
	private Set<Libro> librosSeleccionados;
	boolean nuevo=true;
	private Prestamo prestamo;
	/*
	 * Getters and Setters
	 */

	public String getNumprestamo() {
		return numprestamo;
	}

	public void setNumprestamo(String numprestamo) {
		this.txtNumPrestamo.setText(numprestamo);
	}
	

	public Socio getSocioSeleccionado() {
		return socioSeleccionado;
	}

	public void setSocioSeleccionado(Socio socioSeleccionado) {
		this.listSocios.setSelectedValue(socioSeleccionado, true);
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.dateSalida.setDate(fechaSalida);
	}

	public Date getFechaDevolucion() {
		return fechaDevolucion;
	}

	public void setFechaDevolucion(Date fechaDevolucion) {
		this.dateDevolucion.setDate(fechaDevolucion);
	}

	public Set<Libro> getLibrosSeleccionados() {
		return librosSeleccionados;
	}

	public void setLibroSeleccionado(Set<Libro> l) {
	
	}

	public Accion mostrarDialogo() {
		setVisible(true);
		
		return accion;
	}
	
	public void setPrestamo(Prestamo prestamo) {
		
		nuevo=false;
		this.prestamo=prestamo;
		
		txtNumPrestamo.setText(prestamo.getNumPedido());
		dateSalida.setDate(prestamo.getFechaSalida());;
		dateDevolucion.setDate(prestamo.getFechaDevolucion());
		
		this.listSocios.setSelectedValue(prestamo.getSocios(), true);
		/*
		List <Libro> libros = Util.db.query(Libro.class);
		
			int[] indices = new int[prestamo.getLibros().size()];
			int i = 0;
			int posicion = 0;
			for (Libro libro : libros) {
				modeloListaLibros.addElement(libro);
				if(prestamo.getLibros().contains(libro)) {
					indices[posicion++] = i;
				}
				i++;
			}
			
			listLibros.setSelectedIndices(indices);
		*/
	}
	
	/*
	 * Devuelve un autor.
	 */
	public Prestamo getPrestamo() {
		if(nuevo)
			prestamo = new Prestamo();
	
		prestamo.setNumPedido(numprestamo);
		prestamo.setFechaSalida(fechaSalida);
		prestamo.setFechaDevolucion(fechaDevolucion);
		prestamo.setSocios(socioSeleccionado);
		prestamo.setLibros(new HashSet<Libro>(librosSeleccionados));
		
		return prestamo;
	}
	/**
	 * Se invoca cuando el usuario ha pulsado en Aceptar. Recoge y valida la información de las cajas de texto
	 * y cierra la ventana
	 */
	private void aceptar() {
		
		if (txtNumPrestamo.getText().equals(""))
			return;
		
	
		numprestamo = txtNumPrestamo.getText();
		fechaSalida = dateSalida.getDate();
		fechaDevolucion = dateDevolucion.getDate();
	
		socioSeleccionado = listSocios.getSelectedValue();
		librosSeleccionados = 
				new HashSet<Libro>(listLibros.getSelectedValuesList());
		accion = Accion.ACEPTAR;
		setVisible(false);
	}
	
	/**
	 * Invocado cuando el usuario cancela. Simplemente cierra la ventana
	 */
	private void cancelar() {

		numprestamo = txtNumPrestamo.getText();
		fechaSalida = dateSalida.getDate();
		fechaDevolucion = dateDevolucion.getDate();
	
		socioSeleccionado = listSocios.getSelectedValue();
		librosSeleccionados = 
				new HashSet<Libro>(listLibros.getSelectedValuesList());
		
		accion = Accion.CANCELAR;
		setVisible(false);
	}
	private void inicializar() {
		
		modeloListaSocios = new DefaultListModel<Socio>();
		listSocios.setModel(modeloListaSocios);
		modeloListaLibros = new DefaultListModel<Libro>();
		listLibros.setModel(modeloListaLibros);		
		
		cargarSocios();
		cargarLibros();
	}
	
	private void cargarSocios() {
		List <Socio> socios = Util.db.query(Socio.class);
		
		for (Socio socio : socios) {
			modeloListaSocios.addElement(socio);
		}
	}
	
	private void cargarLibros() {
		
		List <Libro> libros = Util.db.query(Libro.class);
			
			for (Libro libro : libros) {
				
				modeloListaLibros.addElement(libro);
			}
	}
	
	/**
	 * Create the dialog.
	 */
	public JPrestamo() {
		this.setModal(true);
		setBounds(100, 100, 300, 476);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblnum_pedido = new JLabel("Num_prestamo");
		lblnum_pedido.setBounds(10, 23, 92, 14);
		contentPanel.add(lblnum_pedido);
		
		txtNumPrestamo = new JTextField();
		txtNumPrestamo.setColumns(10);
		txtNumPrestamo.setBounds(106, 23, 92, 20);
		contentPanel.add(txtNumPrestamo);
		
		JLabel lblApellido = new JLabel("fecha_salida");
		lblApellido.setBounds(10, 58, 92, 14);
		contentPanel.add(lblApellido);
		
		JLabel lblFechadevolucion = new JLabel("fecha_devolucion");
		lblFechadevolucion.setBounds(10, 89, 92, 14);
		contentPanel.add(lblFechadevolucion);
		
		JLabel lblEligeElSocio = new JLabel("Elige el socio:");
		lblEligeElSocio.setBounds(10, 133, 92, 14);
		contentPanel.add(lblEligeElSocio);
		
		JLabel lblEligeElLibro = new JLabel("Elige el libro:");
		lblEligeElLibro.setBounds(10, 270, 92, 14);
		contentPanel.add(lblEligeElLibro);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 294, 264, 100);
		contentPanel.add(scrollPane);
		
		listLibros = new JList();
		scrollPane.setViewportView(listLibros);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 158, 264, 100);
		contentPanel.add(scrollPane_1);
		
		listSocios = new JList();
		scrollPane_1.setViewportView(listSocios);
		
		dateSalida = new JDateChooser();
		dateSalida.setBounds(106, 52, 95, 20);
		contentPanel.add(dateSalida);
		
		dateDevolucion = new JDateChooser();
		dateDevolucion.setBounds(106, 89, 95, 20);
		contentPanel.add(dateDevolucion);
	
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						aceptar();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cancelar();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		inicializar();
	}
	
}
