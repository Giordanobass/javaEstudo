package entidades;

import java.util.ArrayList;
import java.util.List;

public class Projeto {

	private Long id;
	private String nome;

	private List<Series> series = new ArrayList<Series>();

	public void setSeries(List<Series> series) {
		this.series = series;
	}

	public List<Series> getSeries() {
		return series;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
