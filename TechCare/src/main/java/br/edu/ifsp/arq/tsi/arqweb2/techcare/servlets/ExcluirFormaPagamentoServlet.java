package br.edu.ifsp.arq.tsi.arqweb2.techcare.servlets;

import java.io.IOException;

import br.edu.ifsp.arq.tsi.arqweb2.techcare.model.dao.FormaPagamentoDao;
import br.edu.ifsp.arq.tsi.arqweb2.techcare.utils.DataSourceSearcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/excluirFormaPagamento")
public class ExcluirFormaPagamentoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        
        FormaPagamentoDao formaPagamentoDao = new FormaPagamentoDao(DataSourceSearcher.getInstance().getDataSource());
        formaPagamentoDao.deletar(id);
        
        resp.sendRedirect("listarFormasPagamento");
    }
}
