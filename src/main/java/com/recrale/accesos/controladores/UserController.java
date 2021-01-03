package com.recrale.accesos.controladores;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.recrale.accesos.dtos.UserDto;
import com.recrale.accesos.entidades.User;
import com.recrale.accesos.repositorios.UsuarioRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class UserController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public static String UNAUTHORIZED="Usuario o clave incorrectos";

	public UsuarioRepository getUsuarioRepository() {
		return usuarioRepository;
	}

	public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@PostMapping("user")
	public UserDto login(@RequestParam("username") String  username, @RequestParam("password") String pwd, HttpServletResponse response) throws IOException {
		
		UserDto userDto=new UserDto();
		User user=getUsuarioRepository().findByUserName(username);
		BCryptPasswordEncoder encoder= new BCryptPasswordEncoder();
		System.out.println(encoder.encode(pwd));
		if(user!=null && encoder.matches(pwd, user.getPassword())) {
			userDto.setUser(username);
			userDto.setToken(getJWTToken(username,user.getRol().getRol()));
			userDto.setRol(user.getRol().getRol());
			return userDto;
		}
		else {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			response.sendError(HttpServletResponse.SC_FORBIDDEN,UserController.UNAUTHORIZED);
			return null;
		}
		
		
		
	}

	private String getJWTToken(String username, String rol) {
		String secretKey = "recraleKey";
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
