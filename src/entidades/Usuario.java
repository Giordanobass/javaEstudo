package entidades;

public class Usuario {

	private String login;
	private String senha;
	private String id;
	private String imagem;
	
	private String tipofile;
	
	public void setTipofile(String tipofile) {
		this.tipofile = tipofile;
	}
	
	public String getTipofile() {
		return tipofile;
	}
	
	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	
	public String getImagem() {
		return imagem;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
