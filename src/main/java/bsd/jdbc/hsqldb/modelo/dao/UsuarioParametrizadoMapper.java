package bsd.jdbc.hsqldb.modelo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class UsuarioParametrizadoMapper implements ParameterizedRowMapper<Usuario> {

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

}
