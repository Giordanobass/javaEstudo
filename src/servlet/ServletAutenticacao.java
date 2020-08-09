package servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import user.UserLogado;

@WebServlet("/pages/ServletAutenticacao")
public class ServletAutenticacao extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public ServletAutenticacao() {
    super();

  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // Tratamento para deslogar da sessão
    if (Boolean.parseBoolean(request.getParameter("deslogar"))) {
      HttpServletRequest req = (HttpServletRequest) request;
      HttpSession session = req.getSession();
      session.invalidate();
      // Redireciona para login novamente
      response.sendRedirect("../index.jsp");
    }
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String login = request.getParameter("login");
    String senha = request.getParameter("senha");
    String url = request.getParameter("url");

    // Nesse momento pode validar na base de dados
    if (login.equalsIgnoreCase("admin") && senha.equalsIgnoreCase("admin")) {
      // login bem sucedido

      UserLogado userLogado = new UserLogado();
      userLogado.setLogin(login);
      userLogado.setSenha(senha);

      // Adiciona usuario logado na sessao
      HttpServletRequest req = (HttpServletRequest) request;
      HttpSession session = req.getSession();
      session.setAttribute("usuario", userLogado);


      // Redireciona para o sistema e autorizado
      RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/acessoAoSistema.jsp");
      dispatcher.forward(request, response);
    } else {
      // Falha o login, e redireciona para login novamente
      RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/autenticar.jsp");
      dispatcher.forward(request, response);
    }


  }

}
