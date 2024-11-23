package br.edu.ifsp.arq.tsi.arqweb2.techcare.servlets;

import java.io.IOException;
import java.util.List;

import br.edu.ifsp.arq.tsi.arqweb2.techcare.model.Cliente;
import br.edu.ifsp.arq.tsi.arqweb2.techcare.model.dao.ClienteDao;
import br.edu.ifsp.arq.tsi.arqweb2.techcare.utils.DataSourceSearcher;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/listarClientes")
public class ListarClientesServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ClienteDao clienteDao = new ClienteDao(DataSourceSearcher.getInstance().getDataSource());
		List<Cliente> listaDeClientes = clienteDao.listarClientes();

		req.setAttribute("clientes", listaDeClientes);
		req.getRequestDispatcher("listarClientes.jsp").forward(req, resp);

		ClienteDao odd = new ClienteDao(DataSourceSearcher.getInstance().getDataSource());

		System.out.println("Ordens de Serviço: " + listaDeClientes.size());

		req.setAttribute("listaDeClientes", listaDeClientes);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/listarClientes.jsp");
		dispatcher.forward(req, resp);

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}