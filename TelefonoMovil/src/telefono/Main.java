package telefono;

public class Main {

	public static void main(String[] args) {
		/*crear un tlf móvil que cuando arranque me pida un pin
		 * el tlf movil tendrá una propiedad pin ya definida y me pedirá a 
		 * repetir el pin hasta 3 veces cuando falle
		 * si no fallo, me va a dar la bienvenida
		 * una vez me da la bienvenida, me va a mostrar un menu con 2 opciones: 
		 * 1. llamar y 2. ir a la agenda de contactos
		 * para poder llamar necesito contactos
		 * mi tlf movil estará formado por un pin y por una agenda de contactos
		 * la clase agenda está formada por contactos
		 * cuando le doy a ir a la agenda, puedo: añadir contacto, borrar contacto, llamar a contacto y le doy a llamar
		 * el usuario debe meter un nº de tlf y el tlf movil tendra que decir: estas llamando al nº 635..
		 * o si el nº se corresponde a un contacto, que diga el nombre del contacto
		 * clases; móvil, contacto, agenda
		 * propiedades del contacto: nombre, número
		 * propiedades del agenda:Set o List de tipo Contacto
		 * propiedades del móvil: pin, agenda
		 */
		
		Contacto contacto1 = new Contacto("Cata", 658756985);
		Contacto contacto2 = new Contacto("Bambi", 658256980);
		
		Agenda agenda = new Agenda();
//		agenda.mostrarContactos();
//		agenda.anadirContacto(contacto1);
//		agenda.anadirContacto(contacto2);
//		agenda.mostrarContacto();
//		agenda.eliminarContacto("Cata");
//		agenda.mostrarContacto();
		
		Telefono telefono = new Telefono(agenda);
		
		int intentos = 0;
		do {
			telefono.encender();
			intentos++;
			System.out.println(intentos);
//			telefono.comprobarPin(); // devuelve un boolean
		} while(!telefono.comprobarPin() && intentos < telefono.getMaxIntentos());//intentos < 3 && pinUsuario != PIN
		
		int opcionusuario;
		do {
			telefono.mostrarMenu();
			opcionusuario = telefono.leerInt();
			telefono.gestionarMenu(opcionusuario);
		} while(opcionusuario != 3);
		
		if(opcionusuario == 3) {
			System.exit(opcionusuario);
		}
		
	}

}
