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
@WebServlet("/Compare")
public class ComparisonServlet extends HttpServlet {
	
	private String compared;
	private JsonFormatter formatter;
	
	@Override
	public void init() throws ServletException {
		super.init();
		compared = new GoalsTemperatureComparison().getComparedData();
		formatter = new JsonFormatter();
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("application/json;charset=UTF-8");

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
