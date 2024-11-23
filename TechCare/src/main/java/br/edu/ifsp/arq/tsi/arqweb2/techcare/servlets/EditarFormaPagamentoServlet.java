package br.edu.ifsp.arq.tsi.arqweb2.techcare.servlets;

import java.io.IOException;

import br.edu.ifsp.arq.tsi.arqweb2.techcare.model.FormaPagamento;
import br.edu.ifsp.arq.tsi.arqweb2.techcare.model.dao.FormaPagamentoDao;
import br.edu.ifsp.arq.tsi.arqweb2.techcare.utils.DataSourceSearcher;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/editarFormaPagamento")
public class EditarFormaPagamentoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        
        FormaPagamentoDao formaPagamentoDAO = new FormaPagamentoDao(DataSourceSearcher.getInstance().getDataSource());
        FormaPagamento formaPagamento = formaPagamentoDAO.procurarPeloId(id);
        
        req.setAttribute("formaPagamento", formaPagamento);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/editarFormaPagamento.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String nome = req.getParameter("nome");

        FormaPagamento formaPagamento = new FormaPagamento();
        formaPagamento.setCodigo(Long.valueOf(id));
        formaPagamento.setNome(nome);

        FormaPagamentoDao formaPagamentoDAO = new FormaPagamentoDao(DataSourceSearcher.getInstance().getDataSource());
        formaPagamentoDAO.atualizar(formaPagamento);

        resp.sendRedirect("listarFormasPagamento");
    }
}