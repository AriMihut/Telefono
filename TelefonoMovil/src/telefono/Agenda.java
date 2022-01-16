package telefono;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Agenda {	
	Set<Contacto> agenda;
	private Scanner scanner = new Scanner(System.in);
	private String nuevoNombre;
	private int nuevoNumero;
	private Contacto nuevoContacto;
	
	public Agenda() {
		//mi agenda va a hacer sitio para los contacto
		this.agenda = new HashSet<>();
		Contacto contacto3 = new Contacto("Alin", 26548985);
		Contacto contacto4 = new Contacto("Petri", 56487592);
		this.anadirContacto(contacto3);
		this.anadirContacto(contacto4);
		this.mostrarContactos();
	}

	public Set<Contacto> getAgenda() {
		return this.agenda;
	}

	public void setAgenda(Set<Contacto> agenda) {
		this.agenda = agenda;
	}
	
	public void mostrarMenuAgenda() {
		System.out.println(" 1. Añadir contacto \r\n 2. Eliminar contacto \r\n 3. Editar contcto \r\n 4. Llamar a contacto");
	}

	public void pedirDatosContacto() {
		System.out.println("Introduzca el nombre");
		String nombreContacto = leer();
		
		System.out.println("Introduzca el número de teléfono");
		int numTlf = leerInt();
		crearContacto(nombreContacto, numTlf);
	}

	private void crearContacto(String nombreContacto, int numTlf) {
		Contacto contacto = new Contacto(nombreContacto, numTlf);
		this.anadirContacto(contacto);
	}

	private int leerInt() {
		return scanner.nextInt();
	}

	public String leer() {
		return scanner.next();
	}

	public void anadirContacto(Contacto contacto) {
		this.agenda.add(contacto);//aqui añado un elemnto a un set, a mi agenda
	}
	
	public void eliminarContacto(String nombre) {
		//primero recorro los contactos y busco el contacto que coincida con el nombre que quiero borrar
		//y miro si el nombre coincide con el nombre que le paso por parámetro
//		agenda.stream().forEach(contacto -> {
//			if(contacto.getNombre().equals(nombre)) {
//				agenda.remove(contacto);
//			}
//		});
		//otra forma más corta para borrar un contacto, con agenda.removeIf ya recorro la agenda
		this.agenda.removeIf(contacto -> contacto.getNombre().equals(nombre));
	}
	
	public void mostrarContactos() {
		for(Contacto a : agenda) {
			System.err.println("Contacto = " + a.getNombre());
		}	
	}

	@Override
	public String toString() {
		return "Agenda [agenda=" + agenda + "]";
	}

}
