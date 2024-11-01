package br.edu.ifsp.arq.tsi.arqweb2.techcare.servlets;

import java.io.IOException;

import br.edu.ifsp.arq.tsi.arqweb2.techcare.model.Cliente;
import br.edu.ifsp.arq.tsi.arqweb2.techcare.model.Endereco;
import br.edu.ifsp.arq.tsi.arqweb2.techcare.model.dao.ClienteDao;
import br.edu.ifsp.arq.tsi.arqweb2.techcare.utils.DataSourceSearcher;
import br.edu.ifsp.arq.tsi.arqweb2.techcare.utils.PasswordEncoder;
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
		String logradouro = req.getParameter("logradouro");
		String numero = req.getParameter("numero");
		String complemento = req.getParameter("complemento");
		String bairro = req.getParameter("bairro");
		String cep = req.getParameter("cep");
		String cidade = req.getParameter("cidade");
		String estado = req.getParameter("estado");
		
		Cliente cliente = new Cliente();
		Endereco endereco = new Endereco();
		
		cliente.setNome(name);
		cliente.setEmail(email);
		cliente.setPassword(PasswordEncoder.encode(password));
		cliente.setTelefone(telefone);
		cliente.setCpf(cpf);
		endereco.setLogradouro(logradouro);
		endereco.setNumero(numero);
		endereco.setComplemento(complemento);
		endereco.setBairro(bairro);
		endereco.setCep(cep);
		endereco.setCidade(cidade);
		endereco.setEstado(estado);
		cliente.setEndereco(endereco);
		
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