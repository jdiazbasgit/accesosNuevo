package cap.curso.accesos;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cap.curso.accesos.entidades.Jornada;
import cap.curso.accesos.servicios.JornadasServiceInterface;

@SpringBootTest
public class TestJornadas
{

	@Autowired
	JornadasServiceInterface jpaJornadaServiceInterface;

	@Test
	public void testVerJornadas()
	{
		List<Jornada> jornadas = (List<Jornada>) getJpaJornadaServiceInterface().findAll();
		for (Jornada jornada : jornadas)
		{
			System.out.println(jornada.getId() + " - " + jornada.getDescripcion() + " - " + jornada.getLunes() + " - "
					+ jornada.getMartes() + " - " + jornada.getMiercoles() + " - " + jornada.getJueves() + " - "
					+ jornada.getViernes() + " - " + jornada.getEspecial());
		}
	}
	
	@Test
	public void testGuardarJornadas()
	{
		Jornada jornada = new Jornada();
		
		jornada.setDescripcion("Jornada Completa");
		jornada.setLunes("8:00-14:00&15:00-17:30");
		jornada.setMartes("8:00-14:00&15:00-17:30");
		jornada.setMiercoles("8:00-14:00&15:00-17:30");
		jornada.setJueves("8:00-14:00&15:00-17:30");
		jornada.setViernes("8:00-15:00");
		jornada.setEspecial(0);
		
		getJpaJornadaServiceInterface().save(jornada);
	}
	
	@Test
	public void testFindById()
	{
		Jornada jornada = getJpaJornadaServiceInterface().findById(1);
		
		System.out.println(jornada.getDescripcion());
	}
	
	

	public JornadasServiceInterface getJpaJornadaServiceInterface()
	{
		return jpaJornadaServiceInterface;
	}

	public void setJpaJornadaServiceInterface(JornadasServiceInterface jpaJornadaServiceInterface)
	{
		this.jpaJornadaServiceInterface = jpaJornadaServiceInterface;
	}

}
