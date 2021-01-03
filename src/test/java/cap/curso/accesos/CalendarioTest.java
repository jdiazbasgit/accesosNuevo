package cap.curso.accesos;

import java.sql.Date;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cap.curso.accesos.entidades.Calendario;
import cap.curso.accesos.entidades.Estado;
import cap.curso.accesos.exception.CalendarioAlreadyExistsException;
import cap.curso.accesos.exception.CalendarioNotFoundException;
import cap.curso.accesos.exception.EstadoNotFoundException;
import cap.curso.accesos.servicios.CalendarioServiceInterface;
import cap.curso.accesos.servicios.EstadosServiceInterface;

@SpringBootTest
public class CalendarioTest
{

	@Autowired
	private CalendarioServiceInterface calendarioService;

	@Autowired
	private EstadosServiceInterface estadosService;

	public CalendarioServiceInterface getCalendarioService()
	{
		return calendarioService;
	}

	public void setCalendarioService(CalendarioServiceInterface calendarioService)
	{
		this.calendarioService = calendarioService;
	}

	public EstadosServiceInterface getEstadosService()
	{
		return estadosService;
	}

	public void setEstadosService(EstadosServiceInterface estadosService)
	{
		this.estadosService = estadosService;
	}

	// @Test
	public void calendarioSaveOkTest() throws CalendarioAlreadyExistsException
	{
		GregorianCalendar gregorianCalendar = new GregorianCalendar(2020, 8, 15);
		Date fecha = new Date(gregorianCalendar.getTime().getTime());

		// insert into estados values (1, "Laborable");
		// insert into estados values (2, "Festivo");

		Estado estado = new Estado();
		estado.setDescripcion("Festivo");
		estado.setId(2);

		Calendario calendario = new Calendario();
		calendario.setFecha(fecha);
		calendario.setEstado(estado);

		Calendario result = calendarioService.save(calendario);

		Assert.assertNotEquals(result.getId(), null);
	}

	// @Test(expected = CalendarioAlreadyExistsException.class)
	public void calendarioSaveNoOkTest() throws CalendarioAlreadyExistsException
	{
		GregorianCalendar gregorianCalendar = new GregorianCalendar(2020, 8, 15);
		Date fecha = new Date(gregorianCalendar.getTime().getTime());

		Estado estado = new Estado();
		estado.setDescripcion("Laborable");
		estado.setId(1);

		// Este calendario ya debe de existir en la bd para que lance la excepcion el
		// servicio
		Calendario calendario = new Calendario();
		calendario.setId(2);
		calendario.setFecha(fecha);
		calendario.setEstado(estado);

		Calendario result = calendarioService.save(calendario);

		Assert.assertEquals(result, null);
	}

	// @Test
	public void calendarioUpdateOkTest() throws CalendarioNotFoundException
	{
		Estado estado = new Estado();
		estado.setDescripcion("Laborable");
		estado.setId(1);

		Calendario result = calendarioService.updateCalendarioEstado(8, estado);
	}

	// @Test(expected = CalendarioNotFoundException.class)
	public void calendarioUpdateNoOkTest() throws CalendarioNotFoundException
	{
		Estado estado = new Estado();
		estado.setDescripcion("Laborable");
		estado.setId(1);

		Calendario result = calendarioService.updateCalendarioEstado(232, estado);
	}

	// @Test
	public void calendarioGenerarOkTest() throws CalendarioAlreadyExistsException, EstadoNotFoundException
	{
		Integer anyo = 2020;

		Iterable<Calendario> lista = getCalendarioService().generaCalendarioAnyo(anyo);

	}

	@Test(expected = CalendarioAlreadyExistsException.class)
	public void calendarioGenerarNoOkTest() throws CalendarioAlreadyExistsException, EstadoNotFoundException
	{
		Integer anyo = 2020;

		Iterable<Calendario> lista = getCalendarioService().generaCalendarioAnyo(anyo);
	}

}
