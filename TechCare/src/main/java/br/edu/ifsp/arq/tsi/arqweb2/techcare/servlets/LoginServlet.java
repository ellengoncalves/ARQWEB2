package br.edu.ifsp.arq.tsi.arqweb2.techcare.servlets;

import java.io.IOException;
import java.util.Optional;

import br.edu.ifsp.arq.tsi.arqweb2.techcare.model.Cliente;
import br.edu.ifsp.arq.tsi.arqweb2.techcare.model.dao.ClienteDao;
import br.edu.ifsp.arq.tsi.arqweb2.techcare.utils.DataSourceSearcher;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	public LoginServlet() {
		super();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		String url;
		
		ClienteDao clienteDao = new ClienteDao(DataSourceSearcher.getInstance().getDataSource());
		
		Optional <Cliente> optional = clienteDao.getClienteByEmailAndPassword(email, password);
		if(optional.isPresent()) {
			Cliente cliente = optional.get();
			HttpSession session = req.getSession();
			session.setMaxInactiveInterval(600);
			session.setAttribute("cliente", cliente);
			url = "/homeServlet";
		}else {
			req.setAttribute("result", "loginError");
			url = "/login.jsp";
		}
		RequestDispatcher dispatcher = req.getRequestDispatcher(url);
		dispatcher.forward(req, resp);
	}

}
