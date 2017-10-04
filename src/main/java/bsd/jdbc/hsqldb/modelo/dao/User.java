package bsd.jdbc.hsqldb.modelo.dao;

import java.sql.SQLException;
import java.util.List;



public interface User {
	List<Usuario> getUsers() throws SQLException;
	Usuario getUsersbyCode(int id)throws SQLException;
	
	void add(Usuario usuario)throws SQLException;
	

}
