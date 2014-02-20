package com.mony.bibliotecaDb4o.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;




import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.db4o.Db4o;
import com.db4o.Db4oEmbedded;
import com.db4o.cs.Db4oClientServer;
import com.mony.bibliotecaDb4o.base.Autor;
import com.mony.bibliotecaDb4o.base.Libro;
import com.mony.bibliotecaDb4o.base.Prestamo;
import com.mony.bibliotecaDb4o.base.Socio;
import com.mony.bibliotecaDb4o.beans.tablaSocio;
import com.mony.bibliotecaDb4o.beans.tablaAutor;
import com.mony.bibliotecaDb4o.beans.tablaLibro;
import com.mony.bibliotecaDb4o.beans.tablaPrestamo;
import com.mony.bibliotecaDb4o.util.Constantes;
import com.mony.bibliotecaDb4o.util.Ficheros;
import com.mony.bibliotecaDb4o.util.Util;
import com.mony.bibliotecaDb4o.util.Util.Accion;

import javax.swing.JComboBox;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;




public class Principal {

	private JFrame frmBiblioteca;
	private JLabel lbEstado;
	private JMenuBar menuBar;
	private JMenu mnArchivo;
	private JMenuItem mntmNewMenuItem;
	private JToolBar toolBar;
	private JTabbedPane tabbedPane;
	private JPanel tabSocios;
	private JPanel tabLibros;
	private JPanel tabPrestamos;
	private JButton btAnadir;
	private JButton btModificar;
	private JButton btEliminar;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane_3;
	private JPanel tabAutores;
	private JScrollPane scrollPane_4;

	private JButton btBuscar;
	private JTextField txtFiltro;
	private JMenuItem mntmNewMenuItem_1;
	private JMenuItem mntmNewMenuItem_2;

	private JMenuItem mntmNewMenuItem_3;


	private tablaSocio tablaSocio;
	private tablaAutor tablaAutor;
	private tablaLibro tablaLibro;
	private tablaPrestamo tablaPrestamo;
	private JComboBox<String> cbCampos;
	private JMenuItem mntmNewMenuItem_4;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.frmBiblioteca.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Principal() {
		initialize();
		inicializar();
	
	}
	
	private void inicializar() {
		
		conectar();
		tablaAutor.listar();
		tablaLibro.listar();
		tablaPrestamo.listar();
		tablaSocio.listar();
	}
	private void cargarDatos(){
		tablaAutor.listar();
		tablaLibro.listar();
		tablaPrestamo.listar();
		tablaSocio.listar();
	}
	
	private void conectar() {
		
		// Conecta con la Base de Datos (si el fichero no existe lo creará)
		//Util.db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), Constantes.DATABASE_FILENAME);
	
		Util.db = Db4oClientServer.openClient("localhost", 4444, "usuario", "contrasena");
	}
	private void cerrar() {
		
		Util.db.close();
		System.exit(0);
	}
	
	private void darDeAlta() {
		
		switch (tabbedPane.getSelectedIndex()) {
		case Constantes.SOCIO_:
			JSocio jSocio = new JSocio();
			if (jSocio.mostrarDialogo() == Accion.CANCELAR)
				return;
			
			Socio socio = jSocio.getSocio();
			Util.db.store(socio);
			
			
			tablaSocio.listar();
		break;
		case Constantes.LIBRO_:
			JLibro jLibro = new JLibro();
			if (jLibro.mostrarDialogo() == Accion.CANCELAR)
				return;
			
			Libro libro = jLibro.getLibro();
			Util.db.store(libro);
		
			
			tablaLibro.listar();
			break;
		case Constantes.AUTOR_:
			JAutor jAutor = new JAutor();
			if (jAutor.mostrarDialogo() == Accion.CANCELAR)
				return;
			
			Autor autor = jAutor.getAutor();
			Util.db.store(autor);
			
			
			tablaAutor.listar();
			break;
		case Constantes.PRESTAMO_:
			JPrestamo jPrestamo = new JPrestamo();
			if (jPrestamo.mostrarDialogo() == Accion.CANCELAR)
				return;
			
			Prestamo prestamo = jPrestamo.getPrestamo();
			Util.db.store(prestamo);
			
			
			tablaPrestamo.listar();
			break;
		default:
		}
	}
	
	private void modificar() {
		
		switch (tabbedPane.getSelectedIndex()) {
		case Constantes.SOCIO_:
			JSocio jSocio = new JSocio();
			jSocio.setSocio(tablaSocio.getSocioSeleccionado());
			if (jSocio.mostrarDialogo() == Accion.CANCELAR)
				return;
			
			Socio socio = jSocio.getSocio();
			Util.db.store(socio);
			
			
			tablaSocio.listar();
			break;
		case Constantes.LIBRO_:
			JLibro jLibro = new JLibro();
			jLibro.setLibro(tablaLibro.getLibroSeleccionado());
			if (jLibro.mostrarDialogo() == Accion.CANCELAR)
				return;
			
			Libro libro = jLibro.getLibro();
			Util.db.store(libro);
		
			tablaLibro.listar();
			break;
			
		case Constantes.AUTOR_:
			JAutor jAutor = new JAutor();
			jAutor.setAutor(tablaAutor.getAutorSeleccionado());
			if (jAutor.mostrarDialogo() == Accion.CANCELAR)
				return;
			
			Autor autor = jAutor.getAutor();
			Util.db.store(autor);
		
			tablaAutor.listar();
			break;
			
		case Constantes.PRESTAMO_:
			JPrestamo jPrestamo = new JPrestamo();
			jPrestamo.setPrestamo(tablaPrestamo.getPrestamoSeleccionado());
			if (jPrestamo.mostrarDialogo() == Accion.CANCELAR)
				return;
			
			Prestamo prestamo = jPrestamo.getPrestamo();
			Util.db.store(prestamo);
		
			tablaPrestamo.listar();
			break;
		default:
			}
		}
	
	private void eliminar() {
		
		switch (tabbedPane.getSelectedIndex()) {
		case Constantes.SOCIO_:
			Socio socio = tablaSocio.getSocioSeleccionado();
			Util.db.delete(socio);
			
			
			tablaSocio.listar();
			break;
		case Constantes.LIBRO_:
			Libro libro = tablaLibro.getLibroSeleccionado();
			Util.db.delete(libro);
	
			
			tablaLibro.listar();
			break;
		case Constantes.AUTOR_:
			Autor autor = tablaAutor.getAutorSeleccionado();
			Util.db.delete(autor);

			tablaAutor.listar();
			break;
		case Constantes.PRESTAMO_:
			Prestamo prestamo = tablaPrestamo.getPrestamoSeleccionado();
			Util.db.delete(prestamo);
	
			
			tablaPrestamo.listar();
			break;
		default:
		}
	}
	private void buscar() {
		switch (tabbedPane.getSelectedIndex()) {
		case Constantes.SOCIO_:
			int campo = cbCampos.getSelectedIndex();
			if(txtFiltro.getText().equals("")){
				tablaSocio.listar();
				return;
				}
			tablaSocio.listar(txtFiltro.getText(), campo);
			break;
		case Constantes.LIBRO_:
			campo = cbCampos.getSelectedIndex();
			if(txtFiltro.getText().equals("")){
				tablaLibro.listar();
				return;
				}
			tablaLibro.listar(txtFiltro.getText(), campo);
			break;
		case Constantes.AUTOR_:
			campo = cbCampos.getSelectedIndex();
			if(txtFiltro.getText().equals("")){
				tablaAutor.listar();
				return;
				}
			tablaAutor.listar(txtFiltro.getText(), campo);
			break;

		case Constantes.PRESTAMO_:
			campo = cbCampos.getSelectedIndex();
			if(txtFiltro.getText().equals("")){
				tablaPrestamo.listar();
				return;
				}
			tablaPrestamo.listar(txtFiltro.getText(), campo);
			break;

		default:
		}
		
	}
	private void cambiarPestana() {
		
		switch (tabbedPane.getSelectedIndex()) {
		case Constantes.SOCIO_:
			cbCampos.removeAllItems();
			cbCampos.addItem("<Todos>");
			cbCampos.addItem(Constantes.CODIGO);
			cbCampos.addItem(Constantes.NOMBRE_SOCIO);
			cbCampos.addItem(Constantes.APELLIDO_SOCIO);
			cbCampos.addItem(Constantes.DNI);
			break;
		case Constantes.LIBRO_:
			cbCampos.removeAllItems();
			cbCampos.addItem("<Todos>");
			cbCampos.addItem(Constantes.ISBN);
			cbCampos.addItem(Constantes.TITULO);
			cbCampos.addItem(Constantes.GENERO);
			
			break;
		case Constantes.AUTOR_:
			cbCampos.removeAllItems();
			cbCampos.addItem("<Todos>");
			cbCampos.addItem(Constantes.NOMBRE);
			cbCampos.addItem(Constantes.APELLIDOS);
	
			break;
		case Constantes.PRESTAMO_:
			cbCampos.removeAllItems();
			cbCampos.addItem("<Todos>");
			cbCampos.addItem(Constantes.NUMPEDIDO);
			
	
			break;
			
		default:
		}
		}
	private void exportarXML(){
		JFileChooser jfc = new JFileChooser();
		if (jfc.showSaveDialog(null) == JFileChooser.CANCEL_OPTION)
			return;
		String ruta=jfc.getSelectedFile().getAbsolutePath();
		
		List <Socio> socios = Util.db.query(Socio.class);
		
		Ficheros.exportarSociosXml(socios,ruta);
	}
	private void importarXML(){
		JFileChooser jfc = new JFileChooser();
		if (jfc.showOpenDialog(null) == JFileChooser.CANCEL_OPTION)
			return;
		String ruta=jfc.getSelectedFile().getAbsolutePath();
		Ficheros.importarXML(ruta);	
		cargarDatos();
	}
	
	private void deshacer(){
		Util.db.rollback();
		cargarDatos();
	}
	private void confirmar(){
		Util.db.commit();
		cargarDatos();
	}

	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBiblioteca = new JFrame();
		frmBiblioteca.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				cerrar();
			}
		});
		frmBiblioteca.setTitle("Biblioteca");
		frmBiblioteca.setBounds(100, 100, 580, 477);
		frmBiblioteca.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBiblioteca.getContentPane().add(getLbEstado(), BorderLayout.SOUTH);
		frmBiblioteca.getContentPane().add(getToolBar(), BorderLayout.NORTH);
		frmBiblioteca.getContentPane().add(getTabbedPane(), BorderLayout.CENTER);
		frmBiblioteca.setJMenuBar(getMenuBar());
	}

	public JLabel getLbEstado() {
		if (lbEstado == null) {
			lbEstado = new JLabel("Biblioteca");
		}
		return lbEstado;
	}
	public JMenuBar getMenuBar() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMnArchivo());
		}
		return menuBar;
	}
	public JMenu getMnArchivo() {
		if (mnArchivo == null) {
			mnArchivo = new JMenu("Archivo");
			mnArchivo.add(getMntmNewMenuItem_1());
			mnArchivo.add(getMntmNewMenuItem_2());
			mnArchivo.add(getMntmNewMenuItem_3());
			mnArchivo.add(getMntmNewMenuItem_4());
			mnArchivo.add(getMntmNewMenuItem());
		}
		return mnArchivo;
	}
	public JMenuItem getMntmNewMenuItem() {
		if (mntmNewMenuItem == null) {
			mntmNewMenuItem = new JMenuItem("Salir");
			mntmNewMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					 cerrar();
				}
			});
		}
		return mntmNewMenuItem;
	}
	public JToolBar getToolBar() {
		if (toolBar == null) {
			toolBar = new JToolBar();
			toolBar.add(getBtAnadir());
			toolBar.add(getBtModificar());
			toolBar.add(getBtEliminar());
			toolBar.add(getBtBuscar());
			toolBar.add(getTxtFiltro());
			toolBar.add(getCbCampos());
		}
		return toolBar;
	}
	public JTabbedPane getTabbedPane() {
		if (tabbedPane == null) {
			tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			tabbedPane.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					cambiarPestana();
				}
			});
			tabbedPane.addTab("Socios", null, getTabSocios(), null);
			tabbedPane.addTab("Libros", null, getTabLibros(), null);
			tabbedPane.addTab("Autores", null, getTabAutores(), null);
			tabbedPane.addTab("Prestamos", null, getTabPrestamos(), null);
		}
		return tabbedPane;
	}
	public JPanel getTabSocios() {
		if (tabSocios == null) {
			tabSocios = new JPanel();
			tabSocios.setLayout(new BorderLayout(0, 0));
			tabSocios.add(getScrollPane(), BorderLayout.CENTER);
		}
		return tabSocios;
	}
	public JPanel getTabLibros() {
		if (tabLibros == null) {
			tabLibros = new JPanel();
			tabLibros.setLayout(new BorderLayout(0, 0));
			tabLibros.add(getScrollPane_1(), BorderLayout.CENTER);
		}
		return tabLibros;
	}
	public JPanel getTabPrestamos() {
		if (tabPrestamos == null) {
			tabPrestamos = new JPanel();
			tabPrestamos.setLayout(new BorderLayout(0, 0));
			tabPrestamos.add(getScrollPane_3(), BorderLayout.CENTER);
			
		}
		return tabPrestamos;
	}
	public JButton getBtAnadir() {
		if (btAnadir == null) {
			btAnadir = new JButton("Añadir");
			btAnadir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					darDeAlta();
				}
			});
		}
		return btAnadir;
	}
	public JButton getBtModificar() {
		if (btModificar == null) {
			btModificar = new JButton("Modificar");
			btModificar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					modificar();
				}
			});
		}
		return btModificar;
	}
	public JButton getBtEliminar() {
		if (btEliminar == null) {
			btEliminar = new JButton("Eliminar");
			btEliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					eliminar();
				}
			});
		}
		return btEliminar;
	}
	public JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTablaSocio_());
			
		}
		return scrollPane;
	}
	public JScrollPane getScrollPane_1() {
		if (scrollPane_1 == null) {
			scrollPane_1 = new JScrollPane();
			scrollPane_1.setViewportView(getTablaLibro_());
			
		}
		return scrollPane_1;
	}
	private JScrollPane getScrollPane_3() {
		if (scrollPane_3 == null) {
			scrollPane_3 = new JScrollPane();
			scrollPane_3.setViewportView(getTablaPrestamo_());
		}
		return scrollPane_3;
	}
	private JPanel getTabAutores() {
		if (tabAutores == null) {
			tabAutores = new JPanel();
			tabAutores.setLayout(new BorderLayout(0, 0));
			tabAutores.add(getScrollPane_4(), BorderLayout.CENTER);
		}
		return tabAutores;
	}
	private JScrollPane getScrollPane_4() {
		if (scrollPane_4 == null) {
			scrollPane_4 = new JScrollPane();
			scrollPane_4.setViewportView(getTablaAutor_());
		}
		return scrollPane_4;
	}
	
	private JButton getBtBuscar() {
		if (btBuscar == null) {
			btBuscar = new JButton("Buscar");
			btBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					buscar();
				}
			});
		}
		return btBuscar;
	}
	private JTextField getTxtFiltro() {
		if (txtFiltro == null) {
			txtFiltro = new JTextField();
			txtFiltro.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent arg0) {
					buscar();
				}
			});
			txtFiltro.setColumns(10);
		}
		return txtFiltro;
	}
	private JMenuItem getMntmNewMenuItem_1() {
		if (mntmNewMenuItem_1 == null) {
			mntmNewMenuItem_1 = new JMenuItem("Exportar socios XML");
			mntmNewMenuItem_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					 exportarXML();
				}
			});
		}
		return mntmNewMenuItem_1;
	}
	private JMenuItem getMntmNewMenuItem_2() {
		if (mntmNewMenuItem_2 == null) {
			mntmNewMenuItem_2 = new JMenuItem("Importar socios XML");
			mntmNewMenuItem_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					 importarXML();
				}
			});
		}
		return mntmNewMenuItem_2;
	}
	private JMenuItem getMntmNewMenuItem_3() {
		if (mntmNewMenuItem_3 == null) {
			mntmNewMenuItem_3 = new JMenuItem("Deshacer");
			mntmNewMenuItem_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					deshacer();
				}
			});
		}
		return mntmNewMenuItem_3;
	}
	private tablaSocio getTablaSocio_() {
		if (tablaSocio == null) {
			tablaSocio = new tablaSocio();
		}
		return tablaSocio;
	}
	private tablaAutor getTablaAutor_() {
		if (tablaAutor == null) {
			tablaAutor = new tablaAutor();
		}
		return tablaAutor;
	}
	private tablaLibro getTablaLibro_() {
		if (tablaLibro == null) {
			tablaLibro = new tablaLibro();
		}
		return tablaLibro;
	}
	private tablaPrestamo getTablaPrestamo_() {
		if (tablaPrestamo == null) {
			tablaPrestamo = new tablaPrestamo();
		}
		return tablaPrestamo;
	}
	private JComboBox<String> getCbCampos() {
		if (cbCampos == null) {
			cbCampos = new JComboBox<String>();
		}
		return cbCampos;
	}
	private JMenuItem getMntmNewMenuItem_4() {
		if (mntmNewMenuItem_4 == null) {
			mntmNewMenuItem_4 = new JMenuItem("Confirmar");
			mntmNewMenuItem_4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					confirmar();
				}
			});
		}
		return mntmNewMenuItem_4;
	}
}
