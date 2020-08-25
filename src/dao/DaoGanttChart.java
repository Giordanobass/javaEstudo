package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionDataBase;
import entidades.Projeto;
import entidades.Series;

public class DaoGanttChart {

	private Connection connection;

	public DaoGanttChart() {
		connection = ConnectionDataBase.getConnection();
	}

	public void atualizar(Series series) throws Exception {

		String sqlUpdate = "update series set datainicial = '"
				+ series.getDatainicial() + "' , datafinal = '"
				+ series.getDatafinal() + "' " + "where id = " + series.getId()
				+ " and projeto = " + series.getProjeto();
		connection.prepareStatement(sqlUpdate).executeUpdate();
	}

	public List<Projeto> getProjetos() throws Exception {
		List<Projeto> projetos = new ArrayList<Projeto>();

		String sqlProjetos = "select * from projeto";
		PreparedStatement statementProjeto = connection
				.prepareStatement(sqlProjetos);
		ResultSet resultSetProjetos = statementProjeto.executeQuery();

		while (resultSetProjetos.next()) {
			Projeto projeto = new Projeto();
			projeto.setId(resultSetProjetos.getLong("id"));
			projeto.setNome(resultSetProjetos.getString("nome"));

			String sqlSeries = "select * from series where projeto = "
					+ resultSetProjetos.getLong("id");
			PreparedStatement preparedStatementSerie = connection
					.prepareStatement(sqlSeries);
			ResultSet resultSetSeries = preparedStatementSerie.executeQuery();
			List<Series> series = new ArrayList<Series>();

			while (resultSetSeries.next()) {
				Series serie = new Series();
				serie.setId(resultSetSeries.getLong("id"));
				serie.setNome(resultSetSeries.getString("nome"));
				serie.setProjeto(resultSetSeries.getLong("projeto"));
				serie.setDatainicial(resultSetSeries.getString("datainicial"));
				serie.setDatafinal(resultSetSeries.getString("datafinal"));

				series.add(serie);
			}
			projeto.setSeries(series);

			projetos.add(projeto);
		}

		return projetos;
	}

}
