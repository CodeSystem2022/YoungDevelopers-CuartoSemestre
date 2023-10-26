package utn.estudiantes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import utn.estudiantes.modelo.Estudiante;
import utn.estudiantes.servicio.EstudianteServicio;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class EstudiantesApplication implements CommandLineRunner {
	@Autowired
	private EstudianteServicio estudianteServicio;
	private static final Logger logger = LoggerFactory.getLogger(EstudiantesApplication.class);

	String nl = System.lineSeparator();

	public static void main(String[] args) {
		logger.info("Iniciando la aplicacion...");
		// Levantar la fabrica de Spring
		SpringApplication.run(EstudiantesApplication.class, args);
		logger.info("Aplicacion finalizada...");
	}
	@Override
	public void run(String... args) throws Exception {
		logger.info(nl+ "Ejecutando el metodo run de Spring..."+nl);
		var salir = false;
		var consola = new Scanner(System.in);
		while(!salir){
			mostrarMenu();
			salir = ejecutarOpciones(consola);
			logger.info(nl);
		}//fin ciclo while
	}
	private  void mostrarMenu(){
		//logger.info(nl);
		logger.info("""
    			******** Sistema de Estudiantes ******
    			1.Listar Estudiantes
    			2.Buscar Estudiante
    			3.Agregar Estudiante
    			4.Modificar Estudiante
    			5.Eliminar Estudiante
    			6.Salir
				Elija una opcion:""");
	}
	private boolean ejecutarOpciones(Scanner consola){
		var opcion = Integer.parseInt(consola.nextLine());
		var salir = false;
		switch (opcion){
			case 1->{//listar estudiantes
				logger.info(nl+"Listado de Estudiantes"+nl);
				List<Estudiante> estudiantes=estudianteServicio.listarEstudiantes();
				estudiantes.forEach((estudiante->logger.info(estudiante.toString()+nl)));
			}
			case 2->{//buscar estudiante por id
				logger.info("Digite el id estudiante a buscar:");
				var idEstudiante = Integer.parseInt(consola.nextLine());
				Estudiante estudiante=
						estudianteServicio.buscarEstudiantePorId(idEstudiante);
				if(estudiante !=null)
					logger.info("Estudiante encontrado:"+estudiante+nl);
				else
					logger.info("Estudiante no encontrado:"+estudiante+nl);
			}
			case 3->{//agregar estudiante
				logger.info("Agregar estudiante:"+nl);
				logger.info("Nombre:");
				var nombre = consola.nextLine();
				logger.info("Apellido:");
				var apellido = consola.nextLine();
				logger.info("Telefono:");
				var telefono = consola.nextLine();
				logger.info("Email:");
				var email = consola.nextLine();
				// Crear el objeto estudiante sin el id
				var estudiante = new Estudiante();
				estudiante.setNombre(nombre);
				estudiante.setApellido(apellido);
				estudiante.setTelefono(telefono);
				estudiante.setEmail(email);
				estudianteServicio.guardarEstudiante(estudiante);
				logger.info("Estudiante agregado: "+estudiante+nl);
			}
			case 4->{//MODIFICAR ESTUDIANTE
				logger.info("Modificar estudiante:"+nl);
				logger.info("Ingrese el id estudiante:");
				var idEstudiante = Integer.parseInt(consola.nextLine());
				//Buscamos el estudiante a modificar
				Estudiante estudiante=
						estudianteServicio.buscarEstudiantePorId(idEstudiante);
				if(estudiante != null){
					logger.info("Nombre:");
					var nombre = consola.nextLine();
					logger.info("Apellido:");
					var apellido = consola.nextLine();
					logger.info("Telefono:");
					var telefono = consola.nextLine();
					logger.info("Email:");
					var email = consola.nextLine();
					estudiante.setNombre(nombre);
					estudiante.setApellido(apellido);
					estudiante.setTelefono(telefono);
					estudiante.setEmail(email);
					estudianteServicio.guardarEstudiante(estudiante);
					logger.info("Estudiante modificado: "+estudiante+nl);
				}
				else
					logger.info("Estudiante No encontrado con el id:"+idEstudiante+nl);
			}
			case 5->{//eliminar estudiante
				logger.info("Eliminar estudiante:"+nl);
				logger.info("Digite el id estudiante:");
				var idEstudiante = Integer.parseInt(consola.nextLine());
				//buscamos el id estudiante a eliminar
				var estudiante = estudianteServicio.buscarEstudiantePorId(idEstudiante);
				if(estudiante !=null){
					estudianteServicio.eliminarEstudiante(estudiante);
					logger.info("Estudinate eliminado:"+estudiante+nl);
				}
				else
					logger.info("estudiante no encontrado:"+estudiante+nl);
			}
			case 6->{
				logger.info("Hasta pronto"+nl+nl);
				salir=true;
			}
			default -> logger.info("opcion no reconocida"+opcion+nl);
		}//fin switch
		return salir;
	}//fin metodo ejecutar opciones
}//fin clase EstudianteApplication

