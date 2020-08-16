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


  public List<Usuario> getUsuarios() throws Exception {
    List<Usuario> usuarios = new ArrayList<Usuario>();

    String sql = "SELECT * FROM usuario";
    PreparedStatement statement = connection.prepareStatement(sql);
    ResultSet resultSet = statement.executeQuery();

    while (resultSet.next()) {
      Usuario usuario = new Usuario();

      usuario.setId(resultSet.getString("id"));
      usuario.setLogin(resultSet.getString("login"));
      usuario.setSenha(resultSet.getString("senha"));
      usuario.setImagem(resultSet.getString("imagem"));

      usuarios.add(usuario);
    }
    return usuarios;
  }

  public void gravarImagem(String imagem) throws SQLException {

    String tipoDados = imagem.split(",")[0].split(";")[0].split("/")[1];

    String sql = "insert into usuario (imagem, tipofile) values (?,?);";
    PreparedStatement insert = connection.prepareStatement(sql);

    insert.setString(1, imagem);
    insert.setString(2, tipoDados);
    insert.execute();
  }

  public Usuario buscarImagem(String iduser) {
    try {
      String sql = "select * from usuario where id = " + iduser;

      PreparedStatement buscaImagem = connection.prepareStatement(sql);
      ResultSet resultSet = buscaImagem.executeQuery();

      while (resultSet.next()) {

        Usuario usuario = new Usuario();
        usuario.setId(resultSet.getString("id"));
        usuario.setLogin(resultSet.getString("login"));
        usuario.setSenha(resultSet.getString("senha"));
        usuario.setImagem(resultSet.getString("imagem"));
        usuario.setTipofile(resultSet.getString("tipofile"));

        return usuario;
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    return null;
  }
}
