package cap.curso.accesos;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import cap.curso.accesos.entidades.Calendario;
import cap.curso.accesos.entidades.Empleado;
import cap.curso.accesos.servicios.UsuariosEstadoServiceInterface;
import cap.curso.accesos.entidades.Calendario;
import cap.curso.accesos.entidades.Empleado;

@SpringBootTest
public class TestFichajesM
{

	@Autowired
	private UsuariosEstadoServiceInterface usuariosEstadoService;
	
	@Test
	public void testTodoCorrecto()
	{
		Empleado empleado = new Empleado();
		empleado.setNombre("Pepe");
		empleado.setApellidos("Maroto");
		empleado.setDni("382928383N");
		empleado.setIdentificador("Empleado");
		Calendario calendario = new Calendario();
		calendario.setFecha(new Date(01, 03, 2016));
		assertNotNull(usuariosEstadoService.getDiasTrabajadosEmpleadoByYearByMes(empleado, calendario));
	}
	
	@Test
	public void testMesMal()
	{
		Empleado empleado = new Empleado();
		empleado.setNombre("Pepe");
		empleado.setApellidos("Maroto");
		empleado.setDni("382928383N");
		empleado.setIdentificador("Empleado");
		Calendario calendario = new Calendario();
		calendario.setFecha(new Date(01, 22, 2016));
		assertNull(usuariosEstadoService.getDiasTrabajadosEmpleadoByYearByMes(empleado,calendario));
	}

}
