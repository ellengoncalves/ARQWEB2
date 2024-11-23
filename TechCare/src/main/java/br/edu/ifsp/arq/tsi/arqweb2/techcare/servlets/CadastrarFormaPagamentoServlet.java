package br.edu.ifsp.arq.tsi.arqweb2.techcare.servlets;

import java.io.IOException;

import br.edu.ifsp.arq.tsi.arqweb2.techcare.model.FormaPagamento;
import br.edu.ifsp.arq.tsi.arqweb2.techcare.model.dao.FormaPagamentoDao;
import br.edu.ifsp.arq.tsi.arqweb2.techcare.utils.DataSourceSearcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/cadastrarFormaPagamento")
public class CadastrarFormaPagamentoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nome = req.getParameter("nome");

		FormaPagamento formaPagamento = new FormaPagamento();
		formaPagamento.setNome(nome);

		FormaPagamentoDao formaPagamentoDAO = new FormaPagamentoDao(DataSourceSearcher.getInstance().getDataSource());
		formaPagamentoDAO.salvar(formaPagamento);

		resp.sendRedirect("home.jsp");
	}
}
