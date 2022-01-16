package telefono;

import java.util.Optional;
import java.util.Scanner;

public class Telefono {
	
	Scanner scanner = new Scanner(System.in);
	
	private final int PIN = 1234;
	private Agenda agenda;
	private final int MAXINTENTOS = 3;

	public Telefono() {
	}
	
	public Telefono(Agenda agenda) {
		this.agenda = agenda;
	}

	public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}

	public int getPIN() {
		return PIN;
	}
	
	public int getMaxIntentos() {
		return MAXINTENTOS;
	}
	
	public void encender() {
		System.out.println("Introduce el PIN");
	}
	
	public boolean comprobarPin() {
		int pinUsuario = leerInt();
		if(pinUsuario == PIN) {
			System.err.println("Acceso correcto");
			return true;
		}else {
			System.err.println("Acceso denegado");
			return false;
		}
	}
	
	public void mostrarMenu() {
		System.out.println("1. Mostrar agenda \r\n 2. Llamar \r\n 3. Salir");
		
	}
	
	public void gestionarMenu(int opcionUsuario) {
		if(opcionUsuario == 1) {
			this.agenda.mostrarContactos();//aqui le digo cogéme la agenda de esta clase, del teléfono
			this.agenda.mostrarMenuAgenda();
			this.gestionarMenuAgenda(leerInt());
		}else if(opcionUsuario == 2) {		
			System.out.println("Introduce el número al que quieras llamar: ");
			llamar(Optional.empty(), Optional.of(leerInt()));
//			llamar(Optional.empty(), Optional.of(664327892));
//			llamar(Optional.of("ari"), Optional.empty());
		} else if(opcionUsuario == 3) {
			System.err.println("Bye");
		} else {
			System.err.println("Opción no válida.");
		}
	
	}
	public void gestionarMenuAgenda(int opcionAgenda) {
		switch(opcionAgenda) {
		case 1:// Añadir contacto
			this.agenda.pedirDatosContacto();
			this.agenda.mostrarContactos();
			break;
		case 2://eliminar contacto
			this.agenda.mostrarContactos();
			System.out.println("Introduce el nombre que quieres eliminar");
			String nombreContacto =this.agenda.leer();
			for(Contacto contacto : this.agenda.getAgenda()) {
				if(contacto.getNombre().equals(nombreContacto)){
					
					this.agenda.eliminarContacto(nombreContacto);
				}
			} 
			this.agenda.mostrarContactos();
			break;
		case 3://editar contacto
			System.out.println("Introduce el nombre del contacto a editar");
			String nombreContactoEditar = this.agenda.leer();
			System.out.println("Elije la opción a editar: \r\n 1. Nombre \r\n 2. Número \r\n 3. Ambos");
			int opcionEditar = leerInt();
		
			
			//primero busco el cotacto a editar
			//después en fct de la opción del usuario, le pido que introduzca el nuevo nombre o el nuevo número
			this.agenda.getAgenda().forEach(contacto -> {
				if(contacto.getNombre().equals(nombreContactoEditar)) {
					
					if(opcionEditar == 1) {
						System.out.println("Introduce el nuevo nombre");
						contacto.setNombre(this.agenda.leer());
					} else if(opcionEditar == 2) {
						System.out.println("Introduce el nuevo número");
						contacto.setNumero(leerInt());
					} else {
						System.out.println("Introduce el nuevo nombre y el nuevo número");
						contacto.setNombre(this.agenda.leer());
						contacto.setNumero(leerInt());
					}
				};
			});
			System.err.println(this.agenda);
			break;
		case 4://Llamar desde la agenda
			System.out.println("Elija el contacto a llamar");
			this.agenda.mostrarContactos();
			this.llamar(Optional.of(this.agenda.leer()), Optional.empty());
			break;
		}
	}
	
	private void llamar(Optional<String> nombre, Optional<Integer> numero) { // Este metodo va a recibir o un nombre (si llamas desde agenda )o un numero(si llamas desde teclado)
		
		if(nombre.isPresent()) {
			//estas llamando desdesde agenda --> Ya sabes que tienes ese contacto
			
			for(Contacto contacto : this.agenda.getAgenda()) {
				if(contacto.getNombre().equals(nombre)) {
					System.out.println("Se esta llamando a " + nombre.get());
				}else {
					System.err.println("El contacto no existe.");
				}
			}
		}
		
		if(numero.isPresent()) {
			
			//comprobar si el nº está en la agenda de contactos, debo mostrar el nombre de la agenda
			//si no está en la agenda, que muestre el nº
			//debo recorrer la agenda con un for each
			//si está, muestro el contacto al llamar
			boolean existeContacto = buscarContactoPorNumero(numero.get());
			if(!existeContacto) {
				System.out.println("Se esta llamando a  " + numero.get());
			}
		}
	
//		if(nombre.isEmpty()) {
//			nombre.get();
//		}
		// Mostrar por consola mensjae-> se esta llamando a------
	}
	
	public boolean buscarContactoPorNumero(int numero) {
		boolean existeContacto = false;
		for(Contacto contacto : this.agenda.getAgenda()) {
			if(contacto.getNumero() == numero) {
				System.err.println("Se esta llamando a " + contacto.getNombre());
				existeContacto = true;
			}
		}
		return existeContacto;
	}

	public int leerInt() {
		return scanner.nextInt();
	}

	@Override
	public String toString() {
		return "Telefono [PIN=" + PIN + ", agenda=" + agenda + "]";
	}

}
