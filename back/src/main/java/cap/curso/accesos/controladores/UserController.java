package cap.curso.accesos.controladores;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cap.curso.accesos.DTOs.UserDto;
import cap.curso.accesos.entidades.LoginData;
import cap.curso.accesos.repositorios.UsuarioRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/api")

public class UserController {

	@Autowired
	private UsuarioRepository usuarioRepository;

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
	@PostMapping("/user")
	public UserDto login(@RequestBody LoginData logindata) {
		
		UserDto user=null;
		if(logindata.getUsername().equals("pepe"))
		{
			String token = getJWTToken(logindata.getUsername(),"ROLE_USER");
			 user = new UserDto();
			user.setUser(logindata.getUsername());
			user.setToken(token);
			user.setRol("ROLE_USER");
		}
		
		if(logindata.getUsername().equals("admin")) {
		
		
		String token = getJWTToken(logindata.getUsername(),"ROLE_ADMIN");
		 user = new UserDto();
		user.setUser(logindata.getUsername());
		user.setToken(token);	
		}
		return user;
		
	}

	private String getJWTToken(String username, String rol) {
		String secretKey = "cursocap";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList(rol);
		
		String token = Jwts
				.builder()
				.setId("cursoJWT")
				.setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512,
						secretKey.getBytes()).compact();

		return "Bearer " + token;
	}
	

}


