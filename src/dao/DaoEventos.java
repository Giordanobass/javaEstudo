package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionDataBase;
import entidades.Eventos;

public class DaoEventos {

	private static Connection connection;

	public DaoEventos() {
		connection = ConnectionDataBase.getConnection();
	}

	public List<Eventos> getEventos() throws Exception {
		List<Eventos> eventos = new ArrayList<Eventos>();

		String sql = "select * from eventos ";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {

			Eventos evento = new Eventos();
			evento.setId(resultSet.getString("id"));
			evento.setDescricao(resultSet.getString("descricao"));
			evento.setDataevento(resultSet.getString("dataevento"));

			eventos.add(evento);
		}

		return eventos;
	}

}
