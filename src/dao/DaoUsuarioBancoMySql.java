package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionDataBaseMySQL;
import entidades.Usuario;

public class DaoUsuarioBancoMySql {

	private static Connection connection;

	public DaoUsuarioBancoMySql() {
		connection = ConnectionDataBaseMySQL.getConnection();
	}

	public List<Usuario> getUsuarios() throws Exception {
		List<Usuario> usuarios = new ArrayList<Usuario>();

		String sql = "select * from usuario ";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			
			Usuario usuario = new Usuario();
			usuario.setId(resultSet.getString("id"));
			usuario.setLogin(resultSet.getString("login"));
			usuario.setSenha(resultSet.getString("senha"));

			usuarios.add(usuario);
		}

		return usuarios;
	}

}
