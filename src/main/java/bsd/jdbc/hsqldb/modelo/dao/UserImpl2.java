package bsd.jdbc.hsqldb.modelo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;





public class UserImpl2 extends  SimpleJdbcDaoSupport implements User {

	private DataSource dataSource;
	


	@Override
	public List<Usuario> getUsers()throws SQLException {
		String select = "SELECT ID, NOMBRE, APELLIDOS, EMAIL,LOGIN, CLAVE FROM USUARIOS";
		
		SimpleJdbcTemplate template = getSimpleJdbcTemplate();
		
		
		
		
		List<Usuario> usuarios = template.query(select, ParameterizedBeanPropertyRowMapper.newInstance(Usuario.class));
		

		return usuarios;
	}
		
		
		
	

	@Override
	public Usuario getUsersbyCode(int id) {
		
		
		SimpleJdbcTemplate template = getSimpleJdbcTemplate();
	
		
		String select = "SELECT ID, NOMBRE, APELLIDOS, EMAIL,LOGIN, CLAVE FROM USUARIOS WHERE ID = ?";
			
		/*Usuario usuario =(Usuario) template.queryForObject(select, new UsuarioParametrizadoMapper(),id);*/
		Usuario usuario =(Usuario) template.queryForObject(select, new ParameterizedRowMapper<Usuario>(){
			
			@Override
			public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
				Usuario usuario = new Usuario(
						rs.getInt("ID"), 
						rs.getString("NOMBRE"), 
						rs.getString("APELLIDOS"), 
						rs.getString("EMAIL"), 
						rs.getString("LOGIN"), 
						rs.getString("CLAVE"));
				return usuario;
			}
			
			
		},id);
		return usuario;
	}

	@Override
	public void add(Usuario usuario){
		
		String insert = "INSERT INTO USUARIOS VALUES (" + 
		":id,:firstname,:lastname, :mail, :login, :password)";
		
		
		SimpleJdbcTemplate template = getSimpleJdbcTemplate();
		
		Map<String,Object> params = new HashMap<>();
		params.put("id", usuario.getId());
		params.put("firstname", usuario.getNombre());
		params.put("lastname", usuario.getApellidos());
		params.put("mail", usuario.getEmail());
		params.put("login", usuario.getLogin());
		params.put("password", usuario.getContraseña());
		
		template.update(insert, params);
		
		
		
		template.update(insert,new Object[]{usuario.getId(),usuario.getNombre(),usuario.getApellidos(),
				usuario.getEmail(),usuario.getLogin(),usuario.getContraseña()});
	
		

	}



}
