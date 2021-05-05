package com.recrale.accesos.controladores;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.UriTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recrale.accesos.entidades.Calendario;
import com.recrale.accesos.entidades.Employee;
import com.recrale.accesos.entidades.Jornada;
import com.recrale.accesos.entidades.Status;
import com.recrale.accesos.repositorios.CalendarioRepository;
import com.recrale.accesos.repositorios.EmpleadoRepositoryInterface;
import com.recrale.accesos.repositorios.EstadosCrudRepository;
import com.recrale.accesos.repositorios.JornadaRepository;
import com.recrale.accesos.repositorios.UsuarioRepository;

@RestController
@RequestMapping("/api")

public class UserController {

	

	

	

	public UserController() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private EmpleadoRepositoryInterface empleadoRepositoryInterface;
	@Autowired
	private JornadaRepository jornadaRepository;
	@Autowired
	private EstadosCrudRepository estadosRepository;
	@Autowired
	private CalendarioRepository calendarioRepository;

	public static final String UNAUTHORIZED = "Usuario o clave incorrectos";

	public UsuarioRepository getUsuarioRepository() {
		return usuarioRepository;
	}

	public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	

	@CrossOrigin(origins = "*")
	@GetMapping("/jornadas/{id}")
	public Optional<Jornada> getJornadasById(@PathVariable int id) {

		return getJornadaRepository().findById(id);
	}

	@CrossOrigin(origins = "*")
	@GetMapping("/empleados/{id}")
	public Optional<Employee> getJEmpleadosById(@PathVariable int id) {

		return getEmpleadoRepositoryInterface().findById(id);
	}

	@CrossOrigin(origins = "*")
	@GetMapping("/calendario/{id}")
	public Optional<Calendario> getCalendarioById(@PathVariable int id) {

		return getCalendarioRepository().findById(id);
	}

	@CrossOrigin(origins = "*")
	@GetMapping("/calendarios")
	public List<Calendario> getCalendarios() {
		/*
		 * List<Calendario> calendarios=(List<Calendario>)
		 * getCalendarioRepository().findAll();
		 * 
		 * //calendarios.stream().forEach((e)->e.add(Link.of("/estados/"+e.getEstado().
		 * getId())));
		 * 
		 * for (Calendario calendario : calendarios) {
		 * calendario.add(Link.of("").toUri()) }
		 */
		return (List<Calendario>) getCalendarioRepository().findAll();
	}

	@CrossOrigin(origins = "*")
	@GetMapping(value = "/empleados", produces = "application/hal+json")
	public ResponseEntity<Iterable<Employee>> getEmpleados(HttpServletResponse response) {

		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/hal+json");

		return ResponseEntity.ok().headers(headers).body(getEmpleadoRepositoryInterface().findAll());
		// return (List<Employee>) getEmpleadoRepositoryInterface().findAll();
	}

	@CrossOrigin(origins = "*")
	@GetMapping("/estados/{id}")
	public EntityModel<Status> getEstadosById(@PathVariable int  id) {
		
		//programacion funcional
		  Optional<Status> estado=getEstadosRepository().findById(id);
		  
		  
		  estado.ifPresent(e->e.add(Link.of("api/estados/"+estado.get().getId()).
		  withSelfRel()));
		
		
		/*Programacion clásica
		 * Status estado=getEstadosRepository().findById(id).get();
		 * 
		 * if(estado!=null)
		 * estado.add(Link.of("api/estados/"+estado.getId()).withSelfRel());
		 */
		
		return EntityModel.of(estado.get());
	}

	@CrossOrigin(origins = "*")
	@PostMapping("/empleados")

	public Employee getEmpleados(@RequestBody Employee employee) {
		System.out.println(employee);
		return getEmpleadoRepositoryInterface().save(employee);
	}

	@CrossOrigin(origins = "*")
	@PostMapping("/jornadas")
	public Jornada getJornada(@RequestBody Jornada jornada) {

		return getJornadaRepository().save(jornada);
	}

	@CrossOrigin(origins = "*")
	@GetMapping("/prueba/{tipo}")
	public Object[][] getFechaAndEstado(@PathVariable int tipo) {
		return getCalendarioRepository().getFechaAndEstado(tipo);
	}

	@CrossOrigin(origins = "*")
	@GetMapping("/jornadas")
	public Iterable<Jornada> getJornadas() {

		return  getJornadaRepository().findAll();
	}
	

	@CrossOrigin(origins = "*")
	@GetMapping("/calendarios/{year}")
	public CollectionModel<Calendario> getCalendarioByYear(@PathVariable int year) throws NamingException {
		List<Calendario> salida =  getCalendarioRepository().getCalendarioByYear(year);
		for (Calendario calendario : salida) {
			
			calendario.add(Link.of("estado").withRel("mi_estado").withHref("/api/estados/"+calendario.getEstado().getId()));
			
			calendario.add(Link.of("/calendarios/"+year).withSelfRel());
			//calendario.add(linkTo(methodOn(UserController.class).
			//		getEstadosById(calendario.getEstado().getId())).withRel("estado").withType("GET"));
		}
		return CollectionModel.of(salida).add(Link.of("/api/calendarios/"+year).withSelfRel());
		//return CollectionModel.of(salida,linkTo(methodOn(UserController.class).getCalendarioByYear(year)).withSelfRel().withType("GET"));

	}

	@CrossOrigin(origins = "*")
	@GetMapping("/calendarios/{year}/{mes}")
	public Stream<Calendario> getCalendarioByYearAndMonth(@PathVariable int year, @PathVariable int mes) {
		Stream<Calendario> salida = getCalendarioRepository().getCalendarioByMonth(year, mes);
		

		return salida;

	}

	@CrossOrigin(origins = "*")
	@GetMapping("/calendarios/{year}/{mes}/{semana}")
	public Stream<Calendario> getCalendarioByYearAndMonthAndWeek(@PathVariable int year, @PathVariable int mes,
			@PathVariable int semana) {
		Stream<Calendario> salida = getCalendarioRepository().getCalendarioByMonthAndWeek(year, mes, semana);
		// List<Calendario> calendario = (List<Calendario>)
		// getCalendarioRepository().getCalendarioOrdenado();

		return salida;

	}

	public EmpleadoRepositoryInterface getEmpleadoRepositoryInterface() {
		return empleadoRepositoryInterface;
	}

	public void setEmpleadoRepositoryInterface(EmpleadoRepositoryInterface empleadoRepositoryInterface) {
		this.empleadoRepositoryInterface = empleadoRepositoryInterface;
	}

	public JornadaRepository getJornadaRepository() {
		return jornadaRepository;
	}

	public void setJornadaRepository(JornadaRepository jornadaRepository) {
		this.jornadaRepository = jornadaRepository;
	}

	public CalendarioRepository getCalendarioRepository() {
		return calendarioRepository;
	}

	public void setCalendarioRepository(CalendarioRepository calendarioRepository) {
		this.calendarioRepository = calendarioRepository;
	}

	public EstadosCrudRepository getEstadosRepository() {
		return estadosRepository;
	}

	public void setEstadosRepository(EstadosCrudRepository estadosRepository) {
		this.estadosRepository = estadosRepository;
	}

}
