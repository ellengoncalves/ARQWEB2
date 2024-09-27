package br.edu.ifsp.arq.tsi.arqweb1.ifitness.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import br.edu.ifsp.arq.tsi.arqweb1.ifitness.model.User;
import br.edu.ifsp.arq.tsi.arqweb1.ifitness.model.dto.ActivityByType;
import br.edu.ifsp.arq.tsi.arqweb1.ifitness.model.util.activities.ActivitiesReader;

@WebServlet("/activityStatistics")
public class ActivityStatisticsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public ActivityStatisticsServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String key = req.getParameter("key");
		
		HttpSession session = req.getSession(false);
		User user = (User)session.getAttribute("user");
		
		if(key.equals("type")) {
			List<ActivityByType> activityTypeList = ActivitiesReader.getActivitiesStatisticsByType(user);
			Gson gson = new Gson();
			String json = gson.toJson(activityTypeList);
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("application/json");
			resp.getWriter().write(json.toString());
		}
	}
	
}