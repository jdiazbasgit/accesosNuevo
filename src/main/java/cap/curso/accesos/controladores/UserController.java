package cap.curso.accesos.controladores;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cap.curso.accesos.DTOs.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class UserController {

	@PostMapping("user")
	public User login(@RequestParam("user") String  username, @RequestParam("password") String pwd) {
		
		User user=null;
		if(username.equals("pepe"))
		{
			String token = getJWTToken(username,"ROLE_USER");
			 user = new User();
			user.setUser(username);
			user.setToken(token);
			user.setRol("ROLE_USER");
		}
		
		if(username.equals("admin")) {
		
		
		String token = getJWTToken(username,"ROLE_ADMIN");
		 user = new User();
		user.setUser(username);
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
