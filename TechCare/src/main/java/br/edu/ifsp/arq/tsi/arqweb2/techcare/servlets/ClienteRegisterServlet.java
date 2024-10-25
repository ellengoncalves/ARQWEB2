package br.edu.ifsp.arq.tsi.arqweb2.techcare.servlets;

import java.io.IOException;

import br.edu.ifsp.arq.tsi.arqweb2.techcare.model.Cliente;
import br.edu.ifsp.arq.tsi.arqweb2.techcare.model.dao.ClienteDao;
import br.edu.ifsp.arq.tsi.arqweb2.techcare.utils.PasswordEncoder;
import br.edu.ifsp.arq.tsi.arqweb2.techcare.utils.DataSourceSearcher;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/clientRegister")
public class ClienteRegisterServlet extends HttpServlet{

private static final long serialVersionUID = 1L;
	
	public ClienteRegisterServlet() {
		super();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String telefone = req.getParameter("telefone");
		String cpf = req.getParameter("cpf");
		
		Cliente cliente = new Cliente();
		cliente.setNome(name);
		cliente.setEmail(email);
		cliente.setPassword(PasswordEncoder.encode(password));
		cliente.setTelefone(telefone);
		cliente.setCpf(cpf);
		
		RequestDispatcher dispatcher = null;

		ClienteDao clienteDao = new ClienteDao(DataSourceSearcher.getInstance().getDataSource());
		
		if(clienteDao.save(cliente)) {
			req.setAttribute("result", "registered");
			dispatcher = req.getRequestDispatcher("/login.jsp");
		}else {
			req.setAttribute("result", "notRegistered");
			dispatcher = req.getRequestDispatcher("client-register.jsp");
		}
		
		dispatcher.forward(req, resp);
	}
}