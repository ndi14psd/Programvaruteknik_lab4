package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import compare.DataCollection;
import compare.DataCollectionBuilder;
import compare.GoalsTemperatureComparison;
import compare.Resolution;
import domain.DataSource;
import domain.FootballGoalsSource;
import domain.TemperatureSource;

/**
 * Servlet implementation class ServletTest
 */
@WebServlet("/ServletTest")
public class ServletTest extends HttpServlet {

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("application/json;charset=UTF-8");
		
		String compared = new GoalsTemperatureComparison().getComparedData();
		JsonFormatter formatter = new JsonFormatter();
		
		try (PrintWriter out = response.getWriter()) {
			String pretty = request.getParameter("pretty");
			out.println("true".equals(pretty)? formatter.format(compared) : compared);
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	public String getServletInfo() {
		return "Short description";
	}

}
