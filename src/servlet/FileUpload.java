package servlet;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.codec.binary.Base64;
import dao.DaoUsuario;

@WebServlet("/pages/fileUpload")
public class FileUpload extends HttpServlet {
  private static final long serialVersionUID = 1L;

  private DaoUsuario daoUsuario = new DaoUsuario();

  public FileUpload() {
    super();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      String acao = request.getParameter("acao");

      if (acao.equalsIgnoreCase("carregar")) {

        RequestDispatcher viDispatcher = request.getRequestDispatcher("upload.jsp");
        request.setAttribute("listaUserImagem", daoUsuario.getUsuarios());
        viDispatcher.forward(request, response);

      } else if (acao.equalsIgnoreCase("download")) {
        String iduser = request.getParameter("iduser");
        String imagem = daoUsuario.buscarImagem(iduser);
        if (imagem != null) {

          /* Pega somente imagem pura */
          String imagemPura = imagem.split(",")[1];

          /* Converte base 64 em bytes */
          byte[] imageBytes = new Base64().decodeBase64(imagemPura);

          /* Coloca os bytes em um objeto de entrada para processar */
          InputStream is = new ByteArrayInputStream(imageBytes);

          /* INICIO - Escrever dados da resposta */

          int read = 0;
          byte[] bytes = new byte[1024];
          OutputStream os = response.getOutputStream();

          while ((read = is.read(bytes)) != -1) {
            os.write(bytes, 0, read);
          }
          os.flush();
          os.close();

          /* FIM - Escrever dados da resposta */
        }
      }

    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      // usa variavel fileupload para salvar no banco
      String fileUpload = request.getParameter("fileUpload");
      // faz o insert no banco de dados
      daoUsuario.gravarImagem(fileUpload);

      response.getWriter().write("Upload realizado com sucesso");
    } catch (Exception e) {
      e.printStackTrace();
      response.getWriter().write("Erro fatal ao realizar upload");
      throw new RuntimeException(e);
    }

  }



}
