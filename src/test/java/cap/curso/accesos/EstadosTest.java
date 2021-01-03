package cap.curso.accesos;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cap.curso.accesos.entidades.Estado;
import cap.curso.accesos.exception.EstadoNotFoundException;
import cap.curso.accesos.servicios.EstadosServiceInterface;

@SpringBootTest
public class EstadosTest
{

	@Autowired
	private EstadosServiceInterface estadosService;

	public EstadosServiceInterface getEstadosService()
	{
		return estadosService;
	}

	public void setEstadosService(EstadosServiceInterface estadosService)
	{
		this.estadosService = estadosService;
	}

	@Test
	public void estadoFindByDescriptionOkTest()
	{
		Estado estado = getEstadosService().findByDescripcion("Laborable");
	}

	@Test(expected = EstadoNotFoundException.class)
	public void estadoFindByDescriptionNoOkTest()
	{
		Estado estado = getEstadosService().findByDescripcion("jajaNoExiste");
	}

}