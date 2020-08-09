package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import user.UserLogado;

@WebFilter(urlPatterns = {"/pages/*"})
public class FilterAutenticacao implements Filter {

  @Override
  public void destroy() {
    // Faz algo quando aplicacao é derrubada

  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    // Intercepta todas as requisições

    HttpServletRequest req = (HttpServletRequest) request;
    HttpSession session = req.getSession();

    String urlParaAutenticar = req.getServletPath();
    // Retorna null caso não esteja logado
    UserLogado userLogado = (UserLogado) session.getAttribute("usuario");

    // Usuário não logado
    if (userLogado == null && !urlParaAutenticar.equalsIgnoreCase("/pages/ServletAutenticacao")) {
      RequestDispatcher dispatcher =
          request.getRequestDispatcher("/pages/autenticar.jsp?url=" + urlParaAutenticar);
      dispatcher.forward(request, response);
      return;// para o processo para redirecinar
    }

    chain.doFilter(request, response);

  }

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    // executa algo quando iniciado

  }

}
