package org.sfaci.db4o_servidor;

import org.sfaci.db4o_servidor.util.Constantes;

import com.db4o.ObjectServer;
import com.db4o.cs.Db4oClientServer;

/**
 * Inicia un servidor db4o que puede ser utilizado por múltiples clientes
 * de forma concurrente a través de la red
 * @author Santi
 *
 */
public class Db4o_servidor {
	
	public static void main(String args[]) {
		
		new Db4o_servidor().iniciarServidor();
	}
	
	public void iniciarServidor() {
		
		synchronized (this) {
			final ObjectServer servidor = Db4oClientServer.openServer(Constantes.DB4O_FILENAME, Constantes.PUERTO);
			servidor.grantAccess(Constantes.USUARIO, Constantes.CONTRASENA);
			
			try {
				wait(Long.MAX_VALUE);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
