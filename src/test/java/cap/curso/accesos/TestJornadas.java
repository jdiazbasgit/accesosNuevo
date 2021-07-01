package cap.curso.accesos;

import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cap.curso.accesos.entidades.Jornada;
import cap.curso.accesos.repositorios.JornadaRepository;
import cap.curso.accesos.servicios.JornadasServiceInterface;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestJornadas
{

	@Autowired
	private JornadaRepository repository;
	@Test
	public void testVerJornadas()
	{
		assertNotNull(getRepository().findAll());
	}
	
	
	public JornadaRepository getRepository() {
		return repository;
	}

	public void setRepository(JornadaRepository repository) {
		this.repository = repository;
	}
	
	

	

}
