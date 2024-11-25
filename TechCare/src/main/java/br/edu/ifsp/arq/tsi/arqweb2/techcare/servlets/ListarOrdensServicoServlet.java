package br.edu.ifsp.arq.tsi.arqweb2.techcare.servlets;

import java.io.IOException;
import java.util.List;

import br.edu.ifsp.arq.tsi.arqweb2.techcare.model.OrdemServico;
import br.edu.ifsp.arq.tsi.arqweb2.techcare.model.dao.OrdemServicoDao;
import br.edu.ifsp.arq.tsi.arqweb2.techcare.utils.DataSourceSearcher;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/listarOrdensServico")
public class ListarOrdensServicoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OrdemServicoDao ordemServicoDao = new OrdemServicoDao(DataSourceSearcher.getInstance().getDataSource());
        List<OrdemServico> ordensServico = ordemServicoDao.listarOrdensServico();

        req.setAttribute("ordensServico", ordensServico);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/listarOrdensServico.jsp");
        dispatcher.forward(req, resp);
    }
}
