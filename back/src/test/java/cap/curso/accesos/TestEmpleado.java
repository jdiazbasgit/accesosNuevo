package cap.curso.accesos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cap.curso.accesos.entidades.Empleado;
import cap.curso.accesos.entidades.Jornada;
import cap.curso.accesos.exception.EmpleadoNotFoundException;
import cap.curso.accesos.servicios.EmpleadoServiceInterface;
import cap.curso.accesos.servicios.JornadasServiceInterface;

@SpringBootTest

public class TestEmpleado
{

	@Autowired
	private EmpleadoServiceInterface jpaEmpleadoSI;

	@Autowired
	private JornadasServiceInterface jpaJornadaSI;
	
//	@Test
	public void crearEmpleado(){
		Empleado e = new Empleado();
		e.setNombre("Juanito");
		e.setApellidos("G");
		e.setDni("34249867D");
		e.setIdentificador("11111111");
		e.setFecha_alta(new Date(998888));
		getJpaEmpleadoSI().save(e);
	}
	
//	@Test
	public void testTodosEmpleados()
	{
		List<Empleado> e = (List<Empleado>) getJpaEmpleadoSI().findAll();
		for (Empleado emp : e)
		{
			System.out.println(emp.getNombre());
		}
	}
	
//	@Test
	public void testEmpleadoByID() {
		Empleado e = getJpaEmpleadoSI().findById(1);
		System.out.println(e.getNombre() + "@@@@@@@@@@@@@@@@@@@@@@@@@@");
	}
	
	@Test
	public void testModificarJornada() throws EmpleadoNotFoundException {
		Jornada jornadaNueva = new Jornada();
		jornadaNueva.setLunes("9.00-20.00");
		jornadaNueva.setMartes("9.00-20.00");
		jornadaNueva.setMiercoles("9.00-20.00");
		jornadaNueva.setJueves("9.00-20.00");
		jornadaNueva.setViernes("9.00-20.00");
		jornadaNueva.setDescripcion("Jornada completa");
		jpaJornadaSI.save(jornadaNueva);
		
		Empleado empleado = getJpaEmpleadoSI().findById(1);
		int jornada1_id = empleado.getJornada().getId();
		Empleado empleado1 = getJpaEmpleadoSI().modificarJornada(empleado.getId(), jornadaNueva);
		
		assertEquals(empleado.getId(), empleado1.getId());
		assertNotEquals(jornada1_id, empleado1.getJornada().getId());

	}

	public EmpleadoServiceInterface getJpaEmpleadoSI()
	{
		return jpaEmpleadoSI;
	}
	public void setJpaEmpleadoSI(EmpleadoServiceInterface jpaEmpleadoSI)
	{
		this.jpaEmpleadoSI = jpaEmpleadoSI;
	}

}
