package com.mony.bibliotecaDb4o.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;


import javax.swing.JTextField;
import javax.swing.JLabel;








import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JScrollPane;
import javax.swing.JList;

import com.mony.bibliotecaDb4o.base.Autor;
import com.mony.bibliotecaDb4o.base.Libro;
import com.mony.bibliotecaDb4o.base.Socio;
import com.mony.bibliotecaDb4o.util.Util;
import com.mony.bibliotecaDb4o.util.Util.Accion;



public class JLibro extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	private String isbn;
	private String titulo;
	private String genero;
	
	private JTextField txtIsbn;
	private JTextField txtTitulo;
	private JTextField txtGenero;

	private Accion accion;
	
	private JList<Autor> listAutores;
	private DefaultListModel<Autor> modeloListaAutores;
	private Autor autorSeleccionado;

	private boolean nuevo=true;
	private Libro libro;
	/*
	 * Getters and Setters
	 */

	public Autor getAutorSeleccionado() {
		return autorSeleccionado;
	}

	public void setAutorSeleccionado(Autor autorSeleccionado) {
		this.autorSeleccionado = autorSeleccionado;
	}

	public Accion mostrarDialogo() {
		setVisible(true);
		
		return accion;
	}
	
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.txtIsbn.setText(isbn);
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.txtTitulo.setText(titulo);
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.txtGenero.setText(genero);
	}

	/*
	 * Devuelve un autor.
	 */
	public Libro getLibro() {
		if(nuevo)
		 libro = new Libro();
	
		libro.setIsbn(isbn);
		libro.setTitulo(titulo);
		libro.setGenero(genero);
		libro.setAutores(autorSeleccionado);
	
		return libro;
	}
	
	public void setLibro(Libro libro) {
		
		nuevo=false;
		this.libro=libro;
		
		txtIsbn.setText(libro.getIsbn());
		txtTitulo.setText(libro.getTitulo());
		txtGenero.setText(libro.getGenero());
		listAutores.setSelectedValue(libro.getAutores(), true);
		

	}
	/**
	 * Se invoca cuando el usuario ha pulsado en Aceptar. Recoge y valida la información de las cajas de texto
	 * y cierra la ventana
	 */
	private void aceptar() {
		
		if (txtIsbn.getText().equals(""))
			return;
		
	
		isbn = txtIsbn.getText();
		titulo = txtTitulo.getText();
		genero= txtGenero.getText();
		autorSeleccionado = listAutores.getSelectedValue();
		accion = Accion.ACEPTAR;
		setVisible(false);
	}
	
	/**
	 * Invocado cuando el usuario cancela. Simplemente cierra la ventana
	 */
	private void cancelar() {

		isbn = txtIsbn.getText();
		titulo = txtTitulo.getText();
		genero= txtGenero.getText();
		autorSeleccionado = listAutores.getSelectedValue();
		
		accion = Accion.CANCELAR;
		setVisible(false);
	}
	private void inicializar() {
		
		modeloListaAutores= new DefaultListModel<Autor>();
		listAutores.setModel(modeloListaAutores);
	
		cargarAutores();
	}
	
	private void cargarAutores() {
		
		List <Autor> autores = Util.db.query(Autor.class);
		
		for (Autor autor : autores) {
			modeloListaAutores.addElement(autor);
		}
	}
	
	
	/**
	 * Create the dialog.
	 */
	public JLibro() {
		this.setModal(true);
		
		setBounds(100, 100, 296, 401);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblFechaNac = new JLabel("Isbn");
		lblFechaNac.setBounds(44, 23, 58, 14);
		contentPanel.add(lblFechaNac);
		
		txtIsbn = new JTextField();
		txtIsbn.setColumns(10);
		txtIsbn.setBounds(106, 23, 86, 20);
		contentPanel.add(txtIsbn);
		
		txtTitulo = new JTextField();
		txtTitulo.setColumns(10);
		txtTitulo.setBounds(106, 55, 86, 20);
		contentPanel.add(txtTitulo);
		
		JLabel lblApellido = new JLabel("Titulo");
		lblApellido.setBounds(44, 58, 58, 14);
		contentPanel.add(lblApellido);
		
		JLabel lblGenero = new JLabel("Genero");
		lblGenero.setBounds(44, 89, 58, 14);
		contentPanel.add(lblGenero);
		
		txtGenero = new JTextField();
		txtGenero.setColumns(10);
		txtGenero.setBounds(106, 86, 86, 20);
		contentPanel.add(txtGenero);
		
		JLabel lblAutor = new JLabel("Autor:");
		lblAutor.setBounds(44, 123, 58, 14);
		contentPanel.add(lblAutor);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(44, 148, 201, 171);
		contentPanel.add(scrollPane);
		
		listAutores = new JList<Autor>();
		listAutores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(listAutores);
	
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
