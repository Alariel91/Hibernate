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




import com.mony.bibliotecaDb4o.base.Socio;
import com.mony.bibliotecaDb4o.util.Util.Accion;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JSocio extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private String codigo;
	private String nombre;
	private String apellido;
	private String dni;
	
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtDni;
	private boolean nuevo=true;
	private Socio socio;
	private Accion accion;
	/*
	 * Getters and Setters
	 */
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.txtCodigo.setText(codigo);
	}
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
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.txtDni.setText(dni);
	}

	public Accion mostrarDialogo() {
		setVisible(true);
		
		return accion;
	}
	
	/*
	 * Devuelve un socio.
	 */
	public Socio getSocio() {
		if(nuevo)
		 socio = new Socio();
		
		socio.setCodigo(codigo);
		socio.setNombre(nombre);
		socio.setApellido(apellido);
		socio.setDni(dni);
		
		return socio;
	}
	public void setSocio(Socio socio) {
		
		nuevo=false;
		this.socio=socio;
		
		txtCodigo.setText(socio.getCodigo());
		txtNombre.setText(socio.getNombre());
		txtApellido.setText(socio.getApellido());
		txtDni.setText(socio.getDni());
		

	}
	
	/**
	 * Se invoca cuando el usuario ha pulsado en Aceptar. Recoge y valida la información de las cajas de texto
	 * y cierra la ventana
	 */
	private void aceptar() {
		
		if (txtCodigo.getText().equals(""))
			return;
		
		codigo = txtCodigo.getText();
		nombre = txtNombre.getText();
		apellido = txtApellido.getText();
		dni =  txtDni.getText();
		
		accion = Accion.ACEPTAR;
		setVisible(false);
	}
	
	/**
	 * Invocado cuando el usuario cancela. Simplemente cierra la ventana
	 */
	private void cancelar() {
		
		codigo = txtCodigo.getText();
		nombre = txtNombre.getText();
		apellido = txtApellido.getText();
		dni =  txtDni.getText();
		
		accion = Accion.CANCELAR;
		setVisible(false);
	}
	

	/**
	 * Create the dialog.
	 */
	public JSocio() {
		this.setModal(true);
		setBounds(100, 100, 294, 231);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(94, 22, 86, 20);
		contentPanel.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Codigo");
		lblNewLabel.setBounds(32, 25, 46, 14);
		contentPanel.add(lblNewLabel);
		
		JLabel lblFechaNac = new JLabel("Nombre");
		lblFechaNac.setBounds(32, 53, 58, 14);
		contentPanel.add(lblFechaNac);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(94, 53, 86, 20);
		contentPanel.add(txtNombre);
		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(94, 85, 86, 20);
		contentPanel.add(txtApellido);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(32, 88, 58, 14);
		contentPanel.add(lblApellido);
		
		JLabel lblDni = new JLabel("Dni");
		lblDni.setBounds(32, 122, 58, 14);
		contentPanel.add(lblDni);
		
		txtDni = new JTextField();
		txtDni.setColumns(10);
		txtDni.setBounds(94, 116, 86, 20);
		contentPanel.add(txtDni);
	
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
