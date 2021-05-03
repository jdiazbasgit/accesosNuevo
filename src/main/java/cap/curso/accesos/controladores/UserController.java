package cap.curso.accesos.controladores;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cap.curso.accesos.DTOs.User;
import cap.curso.accesos.repositorios.UsuarioRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class UserController {

	@Autowired
	private UsuarioRepository   usuarioRepository;
	@PostMapping("user")
	public Optional<User> login(@RequestParam("user") String  username, @RequestParam("password") String pwd, HttpServletResponse response) {
		
		User user=null;
		
		BCryptPasswordEncoder bcrypt= new BCryptPasswordEncoder();
		//String claveEncriptada=bcrypt.encode(pwd);
		System.out.println(bcrypt.matches( pwd,getUsuarioRepository().findUserByUser(username).getPassword()));
		if(username.equals(getUsuarioRepository().findUserByUser(username).getUser()))
		{
			if(bcrypt.matches( pwd,getUsuarioRepository().findUserByUser(username).getPassword())) {
			String token = getJWTToken(username,getUsuarioRepository().findUserByUser(username).getRol().getRol());
			 user = new User();
			user.setUser(username);
			user.setToken(token);
			user.setRol(getUsuarioRepository().findUserByUser(username).getRol().getRol());
			}
		}
		
		
		//if(user==null)
			//return response.SC_UNAUTHORIZED;
		return Optional.ofNullable(user);
		
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

	public UsuarioRepository getUsuarioRepository() {
		return usuarioRepository;
	}

	public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
}
