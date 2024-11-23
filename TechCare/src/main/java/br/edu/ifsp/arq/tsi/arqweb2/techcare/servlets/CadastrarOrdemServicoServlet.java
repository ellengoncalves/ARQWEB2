package br.edu.ifsp.arq.tsi.arqweb2.techcare.servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

import br.edu.ifsp.arq.tsi.arqweb2.techcare.model.Cliente;
import br.edu.ifsp.arq.tsi.arqweb2.techcare.model.FormaPagamento;
import br.edu.ifsp.arq.tsi.arqweb2.techcare.model.OrdemServico;
import br.edu.ifsp.arq.tsi.arqweb2.techcare.model.Status;
import br.edu.ifsp.arq.tsi.arqweb2.techcare.model.dao.ClienteDao;
import br.edu.ifsp.arq.tsi.arqweb2.techcare.model.dao.FormaPagamentoDao;
import br.edu.ifsp.arq.tsi.arqweb2.techcare.model.dao.OrdemServicoDao;
import br.edu.ifsp.arq.tsi.arqweb2.techcare.utils.DataSourceSearcher;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/CadastrarOrdemServico")
public class CadastrarOrdemServicoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String descricao = req.getParameter("descricao");
		LocalDate dataEmissao = LocalDate.parse(req.getParameter("dataEmissao"));
		LocalDate dataFinalizacao = LocalDate.parse(req.getParameter("dataFinalizacao"));
		BigDecimal valor = new BigDecimal(req.getParameter("valor"));
		String observacao = req.getParameter("observacao");
		Status status = Status.valueOf(req.getParameter("status"));
		String formaPagamentoNome = req.getParameter("formaPagamentoNome");
		int clienteId = Integer.parseInt(req.getParameter("clienteId"));

		OrdemServico ordemServico = new OrdemServico();
		ordemServico.setDescricao(descricao);
		ordemServico.setDataEmissao(dataEmissao);
		ordemServico.setDataFinalizacao(dataFinalizacao);
		ordemServico.setValor(valor);
		ordemServico.setObservacao(observacao);
		ordemServico.setStatus(status);

		FormaPagamentoDao formaPagamentoDAO = new FormaPagamentoDao(DataSourceSearcher.getInstance().getDataSource());
		FormaPagamento formaPagamento = formaPagamentoDAO.buscar(formaPagamentoNome);

		if (formaPagamento == null) {
			formaPagamento = new FormaPagamento();
			formaPagamento.setNome(formaPagamentoNome);
			formaPagamentoDAO.salvar(formaPagamento);
		}
		ordemServico.setFormaPagamento(formaPagamento);

		ClienteDao clienteDAO = new ClienteDao(DataSourceSearcher.getInstance().getDataSource());
		Cliente cliente = clienteDAO.procurarPeloId(clienteId);
		ordemServico.setCliente(cliente);

		OrdemServicoDao ordemServicoDao = new OrdemServicoDao(DataSourceSearcher.getInstance().getDataSource());
		ordemServicoDao.salvar(ordemServico);

		req.setAttribute("descricao", ordemServico.getDescricao());
		req.setAttribute("dataEmissao", ordemServico.getDataEmissao());
		req.setAttribute("dataFinalizacao", ordemServico.getDataFinalizacao());
		req.setAttribute("valor", ordemServico.getValor());
		req.setAttribute("observacao", ordemServico.getObservacao());
		req.setAttribute("status", ordemServico.getStatus());
		req.setAttribute("nomeFormaPagamento", ordemServico.getFormaPagamento().getNome());
		req.setAttribute("nomeCliente", ordemServico.getCliente().getNome());

		RequestDispatcher dispatcher = req.getRequestDispatcher("/listarOrdensServico.jsp");
		dispatcher.forward(req, resp);
	}
}