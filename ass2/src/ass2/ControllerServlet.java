package ass2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet(name="ControllerServlet",urlPatterns={"/ControllerServlet","/","/home"})
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ControllerServlet() {

	}

	public void init(){

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String action = request.getParameter("action");
		if ("search".equals(action)) {}

		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ParameterManager pm = new ParameterManager(request.getParameterMap());

		//useful for debugging
		pm.printAllValues();

		if (pm.hasParameter("action")) {
			String action = pm.getIndividualParam("action");
			if ("add_auction".equals(action)) {}
			else if ("halt_auction".equals(action)) {}
			else if ("remove_auction".equals(action)) {}
			else if ("ban_user".equals(action)) {}
			else if ("logout".equals(action)) {}
			else if ("login".equals(action)) {}
			else if ("register".equals(action)) {}
		}

		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

}
