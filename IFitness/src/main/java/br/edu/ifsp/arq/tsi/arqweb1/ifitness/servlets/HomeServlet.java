package br.edu.ifsp.arq.tsi.arqweb1.ifitness.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.ifsp.arq.tsi.arqweb1.ifitness.model.Activity;
import br.edu.ifsp.arq.tsi.arqweb1.ifitness.model.User;
import br.edu.ifsp.arq.tsi.arqweb1.ifitness.model.util.activities.ActivitiesReader;

@WebServlet("/homeServlet")
public class HomeServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	public HomeServlet() {
		super();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url;
		HttpSession session = req.getSession(false);
		
		User user = (User)session.getAttribute("user");
		// listar atividades do usuário logado
		List<Activity> userActivities = 
				ActivitiesReader.readByUser(user);
		req.setAttribute("userActivities", userActivities);
		url = "/home.jsp";
		
		RequestDispatcher dispatcher = req.getRequestDispatcher(url);
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

}





