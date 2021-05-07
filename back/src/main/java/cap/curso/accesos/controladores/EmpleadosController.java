package cap.curso.accesos.controladores;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cap.curso.accesos.entidades.Employee;
import cap.curso.accesos.repositorios.EmpleadoRepositoryInterface;

@RestController
@RequestMapping("/api")
public class EmpleadosController {
	
	public EmpleadosController() {}

	@Autowired
	private EmpleadoRepositoryInterface empleadoRepositoryInterface;
	
	public EmpleadoRepositoryInterface getEmpleadoRepositoryInterface() {
		return empleadoRepositoryInterface;
	}

	public void setEmpleadoRepositoryInterface(EmpleadoRepositoryInterface empleadoRepositoryInterface) {
		this.empleadoRepositoryInterface = empleadoRepositoryInterface;
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
	@GetMapping("/empleados/{id}")
	public Optional<Employee> getJEmpleadosById(@PathVariable int id) {

		return getEmpleadoRepositoryInterface().findById(id);
	}

}
