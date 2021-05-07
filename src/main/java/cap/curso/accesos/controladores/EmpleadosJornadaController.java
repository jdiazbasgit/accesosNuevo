package cap.curso.accesos.controladores;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cap.curso.accesos.entidades.Empleado;
import cap.curso.accesos.entidades.Jornada;
import cap.curso.accesos.repositorios.EmpleadoRepositoryInterface;
import cap.curso.accesos.repositorios.JornadaRepository;
import cap.curso.accesos.repositorios.UsuarioRepository;

@RestController
@RequestMapping("/api")
public class EmpleadosJornadaController {

	public EmpleadosJornadaController() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private EmpleadoRepositoryInterface empleadoRepositoryInterface;
	@Autowired
	private JornadaRepository jornadaRepository;
//	@Autowired
//	private EstadosCrudRepository estadosRepository;
//	@Autowired
//	private CalendarioRepository calendarioRepository;

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
	public Optional<Empleado> getJEmpleadosById(@PathVariable int id) {

		return getEmpleadoRepositoryInterface().findById(id);
	}

//	@CrossOrigin(origins = "*")
//	@GetMapping("/calendario/{id}")
//	public Optional<Calendario> getCalendarioById(@PathVariable int id) {
//
//		return getCalendarioRepository().findById(id);
//	}

//	@CrossOrigin(origins = "*")
//	@GetMapping("/calendarios")
//	public List<Calendario> getJCalendariosById() {
//		/*
//		 * List<Calendario> calendarios=(List<Calendario>)
//		 * getCalendarioRepository().findAll();
//		 * 
//		 * //calendarios.stream().forEach((e)->e.add(Link.of("/estados/"+e.getEstado().
//		 * getId())));
//		 * 
//		 * for (Calendario calendario : calendarios) {
//		 * calendario.add(Link.of("").toUri()) }
//		 */
//		return (List<Calendario>) getCalendarioRepository().findAll();
//	}

	@CrossOrigin(origins = "*")
	@GetMapping(value = "/empleados", produces = "application/hal+json")
	public ResponseEntity<Iterable<Empleado>> getEmpleados(HttpServletResponse response) {

		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/hal+json");

		return ResponseEntity.ok().headers(headers).body(getEmpleadoRepositoryInterface().findAll());
		// return (List<Employee>) getEmpleadoRepositoryInterface().findAll();
	}

//	@CrossOrigin(origins = "*")
//	@GetMapping("/estados/{id}")
//	public Optional<Status> getEstadosById(@PathVariable int  id) {
//		
//		return getEstadosRepository().findById(id);
//	}

	@CrossOrigin(origins = "*")
	@PostMapping("/empleados")
	public Empleado getEmpleados(@RequestBody Empleado employee) {
		System.out.println(employee);
		return getEmpleadoRepositoryInterface().save(employee);
	}

	@CrossOrigin(origins = "*")
	@PostMapping("/jornadas")
	public Jornada getJornada(@RequestBody Jornada jornada) {

		return getJornadaRepository().save(jornada);
	}

//	@CrossOrigin(origins = "*")
//	@GetMapping("/prueba/{tipo}")
//	public Object[][] getFechaAndEstado(@PathVariable int tipo) {
//		return getCalendarioRepository().getFechaAndEstado(tipo);
//	}

	@CrossOrigin(origins = "*")
	@GetMapping("/jornadas")
	public List<Jornada> getJornadas() {

		return (List<Jornada>) getJornadaRepository().findAll();
	}

//	@CrossOrigin(origins = "*")
//	@GetMapping("/calendarios/{year}")
//	public List<Calendario> getCalendarioByYear(@PathVariable int year) throws NamingException {
//		List<Calendario> salida = getCalendarioRepository().getCalendarioByYear(year);
//		List<Calendario> calendario = (List<Calendario>)
//		 getCalendarioRepository().getCalendarioOrdenado();
//		
//		  for ( Calendario calendario2 : salida) { calendario2.getEstado();
//		  RepresentationModel.of(calendario2.getEstado()).add(Link.of("/api/estados/"+calendario2.
//		  getEstado().getId())); }
//		 
//		return salida;
//
//	}

//	@CrossOrigin(origins = "*")
//	@GetMapping("/calendarios/{year}/{mes}")
//	public List<Calendario> getCalendarioByYearAndMonth(@PathVariable int year, @PathVariable int mes) {
//		Stream<Calendario> salida = getCalendarioRepository().getCalendarioByMonth(year, mes);
//		List<Calendario> listSalida = salida.collect(Collectors.toList());
//		
//		for (Calendario c : listSalida) {
//			c.setDiaSemana(c.getFecha().get(Calendar.DAY_OF_WEEK));
//			c.setSemanaMes(c.getFecha().get(Calendar.WEEK_OF_MONTH));
//			if (c.getDiaSemana() == 1) {
//				c.setDiaSemana(7);
//			}
//			else {
//				c.setDiaSemana(c.getDiaSemana()-1);
//			}
//		}
//			
//		return listSalida;
//	}

//	@CrossOrigin(origins = "*")
//	@GetMapping("/empleadosactivos")
//	public List<Employee> getEmpleadosActivos() {
//		
////		List<EmployeeStatus> employeesStatuses = (List<EmployeeStatus>) empleadosEstadosRepository
////				.findAll();
////		
//		List<Employee> employees = ((List<Employee>) (empleadoRepositoryInterface
//				.findAll()))
//				.stream()
//				.filter(e -> e.getFecha_baja() == null)
//				.collect(Collectors.toList());
//
//		return employees;
//	}

//	@CrossOrigin(origins = "*")
//	@GetMapping("/empleadosinactivos")
//	public List<Employee> getEmpleadosInactivos() {
//		
//		List<Employee> employees = ((List<Employee>) (empleadoRepositoryInterface
//				.findAll()))
//				.stream()
//				.filter(e -> e.getFecha_baja() != null)
//				.collect(Collectors.toList());
//
//		return employees;
//
//	}

//	@CrossOrigin(origins = "*")
//	@GetMapping("/calendarios/{year}/{mes}/{semana}")
//	public Stream<Calendario> getCalendarioByYearAndMonthAndWeek(@PathVariable int year, @PathVariable int mes,
//			@PathVariable int semana) {
//		Stream<Calendario> salida = getCalendarioRepository().getCalendarioByMonthAndWeek(year, mes, semana);
//		// List<Calendario> calendario = (List<Calendario>)
//		// getCalendarioRepository().getCalendarioOrdenado();
//
//		return salida;
//
//	}

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

//	public CalendarioRepository getCalendarioRepository() {
//		return calendarioRepository;
//	}
//
//	public void setCalendarioRepository(CalendarioRepository calendarioRepository) {
//		this.calendarioRepository = calendarioRepository;
//	}
//
//	public EstadosCrudRepository getEstadosRepository() {
//		return estadosRepository;
//	}
//
//	public void setEstadosRepository(EstadosCrudRepository estadosRepository) {
//		this.estadosRepository = estadosRepository;
//	}

}
