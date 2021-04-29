package com.recrale.accesos.controladores;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.recrale.accesos.Year;
import com.recrale.accesos.entidades.Calendario;
import com.recrale.accesos.entidades.Employee;
import com.recrale.accesos.entidades.EmployeeStatus;
import com.recrale.accesos.entidades.Jornada;
import com.recrale.accesos.repositorios.CalendarioRepository;
import com.recrale.accesos.repositorios.EmpleadoRepositoryInterface;
import com.recrale.accesos.repositorios.JornadaRepository;
import com.recrale.accesos.repositorios.UsuarioRepository;

@RestController
@RequestMapping("/api")

public class UserController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private EmpleadoRepositoryInterface empleadoRepositoryInterface;
	@Autowired
	private JornadaRepository jornadaRepository;

	@Autowired
	private CalendarioRepository calendarioRepository;

	public static final String UNAUTHORIZED = "Usuario o clave incorrectos";

	public UsuarioRepository getUsuarioRepository() {
		return usuarioRepository;
	}

	public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	public UserController() {
		// TODO Auto-generated constructor stub
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
	public List<Calendario> getJCalendariosById() {

		return (List<Calendario>) getCalendarioRepository().findAll();
	}

	@CrossOrigin(origins = "*")
	@GetMapping("/empleados")
	public List<Employee> getEmpleados() {

		return (List<Employee>) getEmpleadoRepositoryInterface().findAll();
	}

	@CrossOrigin(origins = "*")
	@PostMapping("/empleados")
	public Employee getEmpleados(@RequestBody Employee employee) {

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
	public List<Jornada> getJornadas() {

		return (List<Jornada>) getJornadaRepository().findAll();
	}

	@CrossOrigin(origins = "*")
	@GetMapping("/calendarios/{year}")
	public Stream<Calendario> getCalendarioByYear(@PathVariable int year) {
		Stream<Calendario> salida = getCalendarioRepository().getCalendarioByYear(year);
		// List<Calendario> calendario = (List<Calendario>)
		// getCalendarioRepository().getCalendarioOrdenado();

		return salida;

	}
	
	@CrossOrigin(origins = "*")
	@GetMapping("/calendarios/{year}/{mes}")
	public Stream<Calendario> getCalendarioByYearAndMonth(@PathVariable int year, @PathVariable int mes) {
		Stream<Calendario> salida = getCalendarioRepository().getCalendarioByMonth(year, mes);
		// List<Calendario> calendario = (List<Calendario>)
		// getCalendarioRepository().getCalendarioOrdenado();

		return salida;

	}
	
	@CrossOrigin(origins = "*")
	@GetMapping("/calendarios/{year}/{mes}/{semana}")
	public Stream<Calendario> getCalendarioByYearAndMonthAndWeek(@PathVariable int year, @PathVariable int mes, @PathVariable int semana) {
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

}
