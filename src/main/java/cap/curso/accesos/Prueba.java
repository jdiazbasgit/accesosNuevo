package cap.curso.accesos;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Prueba {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
System.out.println(new BCryptPasswordEncoder().encode("1234"));
	}

}