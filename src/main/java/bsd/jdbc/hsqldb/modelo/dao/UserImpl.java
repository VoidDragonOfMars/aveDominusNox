package bsd.jdbc.hsqldb.modelo.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;





public class UserImpl extends  JdbcDaoSupport implements User {

	private DataSource dataSource;
	


	@Override
	public List<Usuario> getUsers()throws SQLException {
		String select = "SELECT ID, NOMBRE, APELLIDOS, EMAIL,LOGIN, CLAVE FROM USUARIOS";
		
		JdbcTemplate template = getJdbcTemplate();
		
		
		
		List<Map<String,Object>>rows = template.queryForList(select);
		
		List<Usuario> usuarios = new ArrayList<>();
		
		for(Map<String, Object> row : rows){
			Usuario usuario = new Usuario(
					(int)row.get("ID"), 
					(String)row.get("NOMBRE"), 
					(String)row.get("APELLIDOS"), 
					(String)row.get("EMAIL"), 
					(String)row.get("LOGIN"), 
					(String)row.get("CLAVE"));
			usuarios.add(usuario);
		}
		return usuarios;
	}
		
		
		
	

	@Override
	public Usuario getUsersbyCode(int id) {
		
		String select = "SELECT ID, NOMBRE, APELLIDOS, EMAIL,LOGIN, CLAVE FROM USUARIOS WHERE ID = ?";
		JdbcTemplate template = getJdbcTemplate();		
		Usuario usuario =(Usuario) template.queryForObject(select, new Object[]{id},new UsuarioMapper());
		
		return usuario;
	}

	@Override
	public void add(Usuario usuario){
		
		String insert = "INSERT INTO USUARIOS VALUES (?,?,?,?, ?, ?)";
		JdbcTemplate template = getJdbcTemplate();
		
		template.update(insert,new Object[]{usuario.getId(),usuario.getNombre(),usuario.getApellidos(),
				usuario.getEmail(),usuario.getLogin(),usuario.getContraseña()});
	
		

	}



}
