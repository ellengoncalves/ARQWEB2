package br.edu.ifsp.arq.tsi.arqweb2.ifitness.servlets;

import java.io.IOException;

import br.edu.ifsp.arq.tsi.arqweb2.ifitness.model.User;
import br.edu.ifsp.arq.tsi.arqweb2.ifitness.model.dao.UserDao;
import br.edu.ifsp.arq.tsi.arqweb2.ifitness.utils.DataSourceSearcher;
import br.edu.ifsp.arq.tsi.arqweb2.ifitness.utils.PasswordEncoder;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/userRegister")
public class UserRegisterServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	public UserRegisterServlet() {
		super();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// obtem os dados da requisicao
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String telefone = req.getParameter("telefone");
		String cpf = req.getParameter("cpf");
		
		// instancia e configura um objeto User com os dados recuperados
		User user = new User();
		user.setName(name);
		user.setEmail(email);
		user.setPassword(PasswordEncoder.encode(password));
		user.setTelefone(telefone);
		user.setCpf(cpf);
		
		RequestDispatcher dispatcher = null;
		
		// get instance = caso não existir ele cria
		UserDao userDao = new UserDao(DataSourceSearcher.getInstance().getDataSource());
		
		// salvar o novo usuário
		if(userDao.save(user)) { //.save faz o insert dos dados
			req.setAttribute("result", "registered");
			dispatcher = req.getRequestDispatcher("/login.jsp");
		}else {
			req.setAttribute("result", "notRegistered");
			dispatcher = req.getRequestDispatcher("user-register.jsp");
		}
		
		dispatcher.forward(req, resp);
	}

}







