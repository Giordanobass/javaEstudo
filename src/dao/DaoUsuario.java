package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import beans.Usuario;
import connection.SingleConnection;

/*
 * Classe DaoUsuario Classe Que Provê os Métodos e Validações Para Manipular Dados, e Acesso e
 * Manipulação do BD
 */
public class DaoUsuario {

  private Connection connection;

  /*
   * Construtor DaoUsuario() Recebe um Objeto connection da Classe SingleConnection
   */
  public DaoUsuario() {
    connection = SingleConnection.getConnection();
  }

  /*
   * Método salvar() Responsável Por Fazer a Inserção de Dados (INSERT) no BD
   * 
   * @param BeanCursoJsp usuario = Objeto Usuário da Classe BeanCursoJsp
   */
  public void salvar(Usuario usuario) {
    try {
      String sql = "INSERT INTO usuario(login, senha, imagem) VALUES( ?, ?, ?)";
      PreparedStatement insert = connection.prepareStatement(sql);
      insert.setString(1, usuario.getLogin());
      insert.setString(2, usuario.getSenha());
      insert.execute();
      connection.commit();
    } catch (Exception e) {
      e.printStackTrace();
      try {
        connection.rollback();
      } catch (SQLException e1) {
        e1.printStackTrace();
      }
    }
  }

  /*
   * Método listar() Responsável Por Listar Todos os Usuários do Sistema
   */
  public List<Usuario> getUsuarios() throws Exception {
    List<Usuario> usuarios = new ArrayList<Usuario>();

    String sql = "SELECT * FROM usuario";
    PreparedStatement statement = connection.prepareStatement(sql);
    ResultSet resultSet = statement.executeQuery();

    while (resultSet.next()) {
      Usuario usuario = new Usuario();

      usuario.setId(resultSet.getLong("id"));
      usuario.setLogin(resultSet.getString("login"));
      usuario.setSenha(resultSet.getString("senha"));
      usuario.setImagem(resultSet.getString("imagem"));

      usuarios.add(usuario);
    }
    return usuarios;
  }

  /*
   * Método delete() Responsável Por Fazer a Exclusão (Delete) no BD
   * 
   * @param String id = Atributo ID do Usuário
   */
  public void delete(String id) {
    if (id != null && !id.isEmpty()) {
      try {
        String sql = "DELETE FROM usuario WHERE id = '" + id + "' and login <> 'admin'";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.execute();
        connection.commit();
      } catch (SQLException e) {
        e.printStackTrace();
        try {
          connection.rollback();
        } catch (SQLException e1) {
          e1.printStackTrace();
        }
      }
    }
  }

  /*
   * Método consultar() Responsável Por Fazer Consultas (SELECT) no BD
   * 
   * @param String id = Atributo ID do Usuário
   */
  public Usuario consultar(String id) throws Exception {
    String sql = "SELECT * FROM usuario WHERE id = '" + id + "' and login <> 'admin'";
    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    ResultSet resultSet = preparedStatement.executeQuery();
    if (resultSet.next()) {
      Usuario usuario = new Usuario();
      usuario.setId(resultSet.getLong("id"));
      usuario.setLogin(resultSet.getString("login"));
      usuario.setSenha(resultSet.getString("senha"));
      return usuario;
    }
    return null; /* fotobase64, contenttype */
  }

  /*
   * Método validarLogin Responsável Por Validar Login(Não Pode Existir 1 Mesmo Login Para 2
   * Usuários Diferentes)
   * 
   * @param String login = Atributo Login do Usuário
   */
  public boolean validarLogin(String login) throws Exception {
    String sql = "SELECT COUNT(1) as qtde FROM usuario WHERE login = '" + login + "'";
    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    ResultSet resultSet = preparedStatement.executeQuery();
    if (resultSet.next()) {
      return resultSet.getInt("qtde") <= 0;
    }
    return false;
  }

  public boolean validarLoginUpdate(String login, String id) throws Exception {
    String sql =
        "SELECT COUNT(1) as qtde FROM usuario WHERE login = '" + login + "' and id <> " + id;
    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    ResultSet resultSet = preparedStatement.executeQuery();
    if (resultSet.next()) {
      return resultSet.getInt("qtde") <= 0;
    }
    return false;
  }

  /*
   * Método validarSenha Responsável Por Validar Senha(Não Pode Existir 1 Mesma Senha Para 2
   * Usuários Diferentes)
   * 
   * @param String senha = Atributo Senha do Usuário
   */
  public boolean validarSenha(String senha) throws Exception {
    String sql = "SELECT COUNT(1) as qtde FROM usuario WHERE senha = '" + senha + "'";
    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    ResultSet resultSet = preparedStatement.executeQuery();
    if (resultSet.next()) {
      return resultSet.getInt("qtde") <= 0;
    }
    return false;
  }

  /*
   * Método atualizar() Método Responsável Por Atualizar os Dados (UPDATE) no BD
   * 
   * @param Usuario usuario = Objeto usuario da Classe Usuario
   */
  public void atualizar(Usuario usuario) {
    try {
      StringBuilder sql = new StringBuilder();

      sql.append(" UPDATE usuario SET login = ?, senha = ?, nome = ?, telefone = ");
      sql.append(" ?, cep = ?, rua = ?, bairro = ?, cidade = ?, ");
      sql.append(" estado = ?, ibge = ?");


      sql.append(" WHERE id = " + usuario.getId());

      // fotobase64, contenttype, curriculobase64, contenttypecurriculo
      PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
      preparedStatement.setString(1, usuario.getLogin());
      preparedStatement.setString(2, usuario.getSenha());

      preparedStatement.executeUpdate();
      connection.commit();
    } catch (Exception e) {
      e.printStackTrace();
      try {
        connection.rollback();
      } catch (SQLException e1) {
        e1.printStackTrace();
      }
    }
  }

  public void gravarImagem(String imagem) throws SQLException {

    String sql = "insert into usuario (imagem) values (?);";
    PreparedStatement insert = connection.prepareStatement(sql);

    insert.setString(1, imagem);
    insert.execute();
  }

  public String buscarImagem(String iduser) {
    try {
      String sql = "select imagem from usuario where id = " + iduser;
      PreparedStatement buscaImagem = connection.prepareStatement(sql);
      ResultSet resultSet = buscaImagem.executeQuery();

      while (resultSet.next()) {
        return resultSet.getString("imagem");
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    return null;
  }
}
