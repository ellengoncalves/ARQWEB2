package br.edu.ifsp.arq.tsi.arqweb2.techcare.servlets;

import java.io.IOException;
import java.util.List;

import br.edu.ifsp.arq.tsi.arqweb2.techcare.model.FormaPagamento;
import br.edu.ifsp.arq.tsi.arqweb2.techcare.model.dao.FormaPagamentoDao;
import br.edu.ifsp.arq.tsi.arqweb2.techcare.utils.DataSourceSearcher;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/listarFormasPagamento")
public class ListarFormasPagamentoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FormaPagamentoDao formaPagamentoDao = new FormaPagamentoDao(DataSourceSearcher.getInstance().getDataSource());
        List<FormaPagamento> formasPagamento = formaPagamentoDao.listarFormasPagamento();
        
        req.setAttribute("formasPagamento", formasPagamento);
        
        RequestDispatcher dispatcher = req.getRequestDispatcher("/listarFormasPagamento.jsp");
        dispatcher.forward(req, resp);
    }
}
