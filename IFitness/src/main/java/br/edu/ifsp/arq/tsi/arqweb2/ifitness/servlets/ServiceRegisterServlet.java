package br.edu.ifsp.arq.tsi.arqweb2.ifitness.servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/serviceRegister")
public class ServiceRegisterServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	public ServiceRegisterServlet() {
		super();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long codigo = Long.parseLong(req.getParameter("codigo")); //req.getParameter("codigo");
		String descricao = req.getParameter("descricao");
		LocalDate dataEmissao = LocalDate.parse(req.getParameter("dataEmissao"));
		LocalDate dataFinalizacao = LocalDate.parse(req.getParameter("dataFinalizacao"));
		BigDecimal valor = testarNumero(req.getParameter("valor"));
		String observacao = req.getParameter("observacao");
	}
	
	private BigDecimal testarNumero(String num) {
		
		try {
			if(num != null && !num.isEmpty()) {
				return new BigDecimal(num);
			}
		} catch (NumberFormatException e) {
			System.err.println("Número inválido: " + e.getMessage());
		}
		
		return null;
	}
	
}
