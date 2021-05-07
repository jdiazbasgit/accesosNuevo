package cap.curso.accesos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cap.curso.accesos.repositorios.UsuarioRepository;

@SpringBootTest
class AuitenticationTest {

	@Autowired
	private UsuarioRepository repo;
	@Test
	void pruebaPositiva() {
		assertNotNull(getRepo().findUserByUser("pepe"));
	}
	
	@Test
	void pruebaNegstiva() {
		assertNull(getRepo().findUserByUser("luis"));
	}
	public UsuarioRepository getRepo() {
		return repo;
	}
	public void setRepo(UsuarioRepository repo) {
		this.repo = repo;
	}

}
