package beans;

/*
 * Classe BeanCursoJsp Classe Que Provê o Modelo de Objeto
 */
public class Usuario {

  private String id;
  private String login;
  private String senha;

  private String imagem;
  private String tipofile;

  public String getImagem() {
    return imagem;
  }

  public void setImagem(String imagem) {
    this.imagem = imagem;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getLogin() {
    return this.login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getSenha() {
    return this.senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  public String getTipofile() {
    return tipofile;
  }

  public void setTipofile(String tipofile) {
    this.tipofile = tipofile;
  }

}
