package beans;

/*
 * Classe BeanCursoJsp Classe Que Provê o Modelo de Objeto
 */
public class Usuario {

  private Long id;
  private String login;
  private String senha;

  private String imagem;


  public String getImagem() {
    return imagem;
  }

  public void setImagem(String imagem) {
    this.imagem = imagem;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
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

}
