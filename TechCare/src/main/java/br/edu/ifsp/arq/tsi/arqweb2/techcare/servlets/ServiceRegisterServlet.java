package br.edu.ifsp.arq.tsi.arqweb2.techcare.servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.google.gson.Gson;

import br.edu.ifsp.arq.tsi.arqweb2.techcare.model.OrdemServico;
import br.edu.ifsp.arq.tsi.arqweb2.techcare.model.OrdemServicoStatus;
import br.edu.ifsp.arq.tsi.arqweb2.techcare.model.Cliente;
import br.edu.ifsp.arq.tsi.arqweb2.techcare.model.dao.OrdemServicoDao;
import br.edu.ifsp.arq.tsi.arqweb2.techcare.utils.DataSourceSearcher;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/serviceRegister")
public class ServiceRegisterServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	public ServiceRegisterServlet() {
		super();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long codigo = Long.parseLong(req.getParameter("codigo"));
		String descricao = req.getParameter("descricao");
		LocalDate data_emissao = LocalDate.parse(req.getParameter("dataEmissao"));
		LocalDate data_finalizacao = LocalDate.parse(req.getParameter("dataFinalizacao"));
		BigDecimal valor = testaNumero(req.getParameter("valor"));
		String observacao = req.getParameter("observacao");
		OrdemServicoStatus status = OrdemServicoStatus.valueOf(req.getParameter("status"));
		
		String url;
		HttpSession session = req.getSession(false);

		Cliente cliente = (Cliente) session.getAttribute("cliente");
		OrdemServico ordemServico = new OrdemServico();
		ordemServico.setDescricao(descricao);
		ordemServico.setDataEmissao(data_emissao);
		ordemServico.setDataFinalizacao(data_finalizacao);
		ordemServico.setValor(valor);
		ordemServico.setObservacao(observacao);
		ordemServico.setStatus(status);
		ordemServico.setCliente(cliente);
		
		OrdemServicoDao ordemServicoDao = 
				new OrdemServicoDao(DataSourceSearcher.getInstance().getDataSource());
		if (codigo == 0) {
			if (ordemServicoDao.save(ordemServico)) {
				req.setAttribute("result", "registered");
			}
		} else {
			ordemServico.setCodigo(codigo);
			if (ordemServicoDao.update(ordemServico)) {
				req.setAttribute("result", "registered");
			}
		}
		url = "/service-register.jsp";

		RequestDispatcher dispatcher = req.getRequestDispatcher(url);
		dispatcher.forward(req, resp);
	}
	
	private BigDecimal testaNumero(String num) {
		try {
			if (num != null && !num.isEmpty()) {
				return new BigDecimal(num);
			}
		} catch (NumberFormatException e) {
			System.err.println("Valor inválido: " + e.getMessage());
		}
		return null;
	}
}
