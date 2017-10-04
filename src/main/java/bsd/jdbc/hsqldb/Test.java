package bsd.jdbc.hsqldb;
import java.sql.SQLException;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import bsd.jdbc.hsqldb.modelo.dao.User;
import bsd.jdbc.hsqldb.modelo.dao.Usuario;

public class Test {

	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("modulo-spring.xml");

		User user = (User) context.getBean("userDao");
		try {
			Usuario usuario = user.getUsersbyCode(101);
			System.out.println(usuario);
			user.add(new Usuario(108, "cfghxdfgh", "gshf", "hjsgsshj", "8", "8"));
			List<Usuario> usuarios = user.getUsers();
			
			usuarios.stream().forEach(System.out::println);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		context.close();
	}

}
