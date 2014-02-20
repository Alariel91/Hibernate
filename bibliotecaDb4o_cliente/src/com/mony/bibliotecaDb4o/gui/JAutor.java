package com.mony.bibliotecaDb4o.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JTextField;
import javax.swing.JLabel;






import com.mony.bibliotecaDb4o.base.Autor;
import com.mony.bibliotecaDb4o.base.Socio;
import com.mony.bibliotecaDb4o.util.Util.Accion;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JAutor extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private String codigo;
	private String nombre;
	private String apellido;
	private String dni;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private boolean nuevo=true;
	private Autor autor;

	private Accion accion;
	/*
	 * Getters and Setters
	 */

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.txtNombre.setText(nombre);
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.txtApellido.setText(apellido);
	}
	

	public Accion mostrarDialogo() {
		setVisible(true);
		
		return accion;
	}
	
	/*
	 * Devuelve un autor.
	 */
	public Autor getAutor() {
		if(nuevo)
		autor = new Autor();
	
		autor.setNombre(nombre);
		autor.setApellido(apellido);
	
		
		return autor;
	}
	
	public void setAutor(Autor autor) {
		
		nuevo=false;
		this.autor=autor;
		

		txtNombre.setText(autor.getNombre());
		txtApellido.setText(autor.getApellido());
		
		

	}
	/**
	 * Se invoca cuando el usuario ha pulsado en Aceptar. Recoge y valida la información de las cajas de texto
	 * y cierra la ventana
	 */
	private void aceptar() {
		
		if (txtNombre.getText().equals(""))
			return;
		
	
		nombre = txtNombre.getText();
		apellido = txtApellido.getText();
	
		
		accion = Accion.ACEPTAR;
		setVisible(false);
	}
	
	/**
	 * Invocado cuando el usuario cancela. Simplemente cierra la ventana
	 */
	private void cancelar() {
		

		nombre = txtNombre.getText();
		apellido = txtApellido.getText();
		
		
		accion = Accion.CANCELAR;
		setVisible(false);
	}
	

	/**
	 * Create the dialog.
	 */
	public JAutor() {
		this.setModal(true);
		setBounds(100, 100, 294, 171);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblFechaNac = new JLabel("Nombre");
		lblFechaNac.setBounds(44, 23, 58, 14);
		contentPanel.add(lblFechaNac);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(106, 23, 86, 20);
		contentPanel.add(txtNombre);
		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(106, 55, 86, 20);
		contentPanel.add(txtApellido);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(44, 58, 58, 14);
		contentPanel.add(lblApellido);
	
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
	}
}
