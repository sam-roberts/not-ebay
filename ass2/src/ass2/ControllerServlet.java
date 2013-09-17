package ass2;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.org.mozilla.javascript.internal.Context;
import beans.UserBean;
import contollers.AddAuctionController;
import contollers.LoginController;
import contollers.RegistrationController;


/**
 * Servlet implementation class ControllerServlet
 */
//@WebServlet(name="ControllerServlet",urlPatterns={"/ControllerServlet","/","/home"})
@MultipartConfig
public class ControllerServlet extends HttpServlet {

	private static final String JSP_LOGIN = "/login.jsp";
	private static final String JSP_HOME = "/index.jsp";
	private static final String JSP_REGISTRATION = "/registration.jsp";
	private static final String JSP_MESSAGE = "/message.jsp";
	private static final String JSP_ADD_AUCTION = "/auction.jsp";

	private static final long serialVersionUID = 1L;

	//private LoginController loginController;
	//private RegistrationController registrationController;


	public ControllerServlet() {

	}

	public void init(){

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.setContentType("text/html");
		String action = request.getParameter("action");
		if ("search".equals(action)) {}

		//request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ParameterManager pm = new ParameterManager(request.getParameterMap());

		//useful for debugging
		pm.printAllValues();

		//default go home?
		String forward = JSP_HOME;

		if (pm.hasParameter("action")) {
			String action = pm.getIndividualParam("action");
			if ("addAuction".equals(action)) {
				AddAuctionController ac = new AddAuctionController(pm);
				
				//get timestamp
				//TODO tell user to format like this
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				Timestamp ts;
				try {
					ts = new Timestamp(sdf.parse(request.getParameter("endOfAuction")).getTime());
				} catch (ParseException e) {
					//TODO fix so below doesn't execute if fail, not just return
					System.out.println("timestamp conversion failed");
					return ;
				}
				
				UserBean ub;
				if ((ub = (UserBean) request.getSession().getAttribute("account")) != null) {
					//TODO get username? or nickname
					ac.addAuction(request.getPart("picture"), getServletContext().getRealPath("/") + "auction_images/", ub.getUsername(), ts);
				}
			}
			else if ("halt_auction".equals(action)) {}
			else if ("remove_auction".equals(action)) {}
			else if ("ban_user".equals(action)) {}
			else if ("logout".equals(action)) {
				request.getSession().removeAttribute("account");
				forward = JSP_HOME;
			}
			else if ("login".equals(action)) {
				LoginController loginController = new LoginController(pm);
				if (loginController.isInvalidForm()) {
					//entered the wrong information, keep them on the same page
					forward = JSP_LOGIN;
					request.setAttribute("message", loginController.getFormMessage());
				} else {
					//check to see if its a valid account					
					UserBean ub = loginController.requestLogin();
					if (ub != null) {
						request.getSession().setAttribute("account", ub);
						forward = JSP_HOME;
					} else {
						request.setAttribute("message", loginController.getMessage());
						forward = JSP_LOGIN;
					}
				}
			}
			else if ("register".equals(action)) {
				RegistrationController registrationController = new RegistrationController(pm);
				if (registrationController.isInvalidForm()) {
					forward = JSP_REGISTRATION;
					request.setAttribute("message", registrationController.getFormMessage());
				} else {
					if (registrationController.isAccountAlreadyExists()) {
						forward = JSP_REGISTRATION;
						request.setAttribute("message", registrationController.getMessage());
					} else {
						forward = JSP_MESSAGE;
						request.setAttribute("message", "You have been registered, an email will be sent to you to confirm your account");

						//TODO make sure to securily parse strings

						registrationController.registerUser();

						//TODO Create the account in the database

						//TODO Email the user
					}
				}

			}
		}


		RequestDispatcher requestDispatcher = request.getServletContext().getRequestDispatcher(forward);
		System.out.println("Forwarding to: " + forward);
		requestDispatcher.forward(request, response);
	}

}
