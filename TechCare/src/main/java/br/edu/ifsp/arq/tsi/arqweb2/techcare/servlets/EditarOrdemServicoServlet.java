package br.edu.ifsp.arq.tsi.arqweb2.techcare.servlets;

import java.io.IOException;

import br.edu.ifsp.arq.tsi.arqweb2.techcare.model.OrdemServico;
import br.edu.ifsp.arq.tsi.arqweb2.techcare.model.dao.OrdemServicoDao;
import br.edu.ifsp.arq.tsi.arqweb2.techcare.utils.DataSourceSearcher;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/editarOrdemServico")
public class EditarOrdemServicoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        OrdemServicoDao ordemServicoDao = new OrdemServicoDao(DataSourceSearcher.getInstance().getDataSource());
        OrdemServico ordemServico = ordemServicoDao.procurarPeloId(id);

        req.setAttribute("ordemServico", ordemServico);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/editarOrdemServico.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String descricao = req.getParameter("descricao");
        String dataEmissao = req.getParameter("dataEmissao");
        String dataFinalizacao = req.getParameter("dataFinalizacao");
        double valor = Double.parseDouble(req.getParameter("valor"));
        String observacao = req.getParameter("observacao");
        String status = req.getParameter("status");

        OrdemServicoDao ordemServicoDao = new OrdemServicoDao(DataSourceSearcher.getInstance().getDataSource());
        ordemServicoDao.atualizar(id, descricao, dataEmissao, dataFinalizacao, valor, observacao, status);

        resp.sendRedirect("listarOrdensServico");
    }
}