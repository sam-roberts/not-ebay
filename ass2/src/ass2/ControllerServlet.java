package ass2;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.org.mozilla.javascript.internal.Context;
import beans.AuctionListBean;
import beans.UserBean;
import contollers.AccountController;
import contollers.AddAuctionController;
import contollers.AlertController;
import contollers.BidController;
import contollers.GetAuctionController;
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
	private static final String JSP_GET = "/getAuction.jsp";
	private static final String JSP_CONTROLLER = "/controller";
	private static final String JSP_ACCOUNT = "/account.jsp";
	private static final String JSP_WAUCTIONS = "/winningAuctions.jsp";

	private static final long serialVersionUID = 1L;

	//private LoginController loginController;
	//private RegistrationController registrationController;


	private ParameterManager pm;
	
	private final java.util.concurrent.ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

	public ControllerServlet() {

	}

	public void init(){

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ParameterManager pm = new ParameterManager(request.getParameterMap());		
		response.setContentType("text/html");
		String forward = JSP_HOME;

		String action = request.getParameter("action");
		if ("auction".equals(action)) {
			GetAuctionController gac = new GetAuctionController(pm);
			request.setAttribute("auction", gac.getAuction());

			//TODO this makes 2 calls to the database - fix to one using join
			if (request.getParameter("id") != null) {
				BidController bd = new BidController(pm);
				request.setAttribute("bid", bd.getBids());
			}
			forward = JSP_GET;
		} else if ("wauction".equals(action)) {
			GetAuctionController gac = new GetAuctionController(pm);
			UserBean ub = null;
			if ((ub = (UserBean) request.getSession().getAttribute("account")) != null) {
				request.setAttribute("wauction", gac.getWinningAuctions(ub.getUsername()));
			}
			forward = JSP_WAUCTIONS;
		} else if ("account".equals(action)) {
			AlertController ac = new AlertController(pm);
			UserBean ub;
			if ((ub = (UserBean) request.getSession().getAttribute("account")) != null) {
				request.setAttribute("alert", ac.getAlert(ub.getUsername()));
			}
			forward = JSP_ACCOUNT;
		}
		request.getRequestDispatcher(forward).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		pm = new ParameterManager(request.getParameterMap());

		//useful for debugging
		pm.printAllValues();

		//default go home?
		String forward = JSP_HOME;

		if (pm.hasParameter("action")) {
			String action = pm.getIndividualParam("action");
			if ("addAuction".equals(action)) {

				forward = doAddAuction(request);
			}
			else if ("halt_auction".equals(action)) {
				
			}
			else if ("remove_auction".equals(action)) {}
			else if ("ban_user".equals(action)) {}
			else if ("logout".equals(action)) {
				request.getSession().removeAttribute("account");
				forward = JSP_HOME;
			}
			else if ("login".equals(action)) {
				forward = doLogin(request);
			}
			else if ("register".equals(action)) {
				forward = doRegister(request);
			}
			else if ("bid".equals(action)) {
				forward = doBid(request);
			}
			else if ("win".equals(action)) {
				GetAuctionController gac = new GetAuctionController(pm);
				UserBean ub;
				if ((ub = (UserBean) request.getSession().getAttribute("account")) != null) {
					gac.winAuction(ub.getUsername());
				}
			}
			else if ("removeAlert".equals(action)) {
				AlertController ac = new AlertController(pm);
				UserBean ub;
				if ((ub = (UserBean) request.getSession().getAttribute("account")) != null) {
					ac.removeAlert(ub.getUsername());
					request.setAttribute("alert", ac.getAlert(ub.getUsername()));
				}
				forward = JSP_ACCOUNT;
			}
			else if (("update").equals(action)) {
				AccountController ac = new AccountController(pm);
				UserBean ub;
				if ((ub = (UserBean) request.getSession().getAttribute("account")) != null) {
					ac.updateAccount(ub.getUsername());
				}
			}
		}

		RequestDispatcher requestDispatcher = request.getServletContext().getRequestDispatcher(forward);
		System.out.println("Forwarding to: " + forward);
		requestDispatcher.forward(request, response);
	}

	private String doAddAuction(HttpServletRequest request) throws IOException,
	ServletException {
		String forward = JSP_ADD_AUCTION;
		AddAuctionController ac = new AddAuctionController(pm);

		if (ac.isInvalidForm()) {
			request.setAttribute("message", ac.getFormMessage());
		} else {
			UserBean ub;
			// if we are logged in
			if ((ub = (UserBean) request.getSession().getAttribute("account")) != null) {
				//TODO get username? or nickname

				forward=JSP_ACCOUNT;
				request.getSession().setAttribute("userInfo", ub);
				
				//TODO add check
				scheduler.schedule(new popAuction(ac.addAuction(request.getPart("picture"), getServletContext().getRealPath("/"), "auction_images/", ub.getUsername())), Integer.parseInt(request.getParameter("auctionEnd")), TimeUnit.MINUTES);
				request.setAttribute("message", "Added new auction");

			} else {
				request.setAttribute("message", "What are you doing here? You need to be logged in to create an auction");
			}
		}
		return forward;
	}

	private String doBid(HttpServletRequest request) {
		String forward;
		BidController bd = new BidController(pm);
		GetAuctionController gac = new GetAuctionController(pm);
		UserBean ub = null;
		AuctionListBean alb = gac.getAuction();
		if ((ub = (UserBean) request.getSession().getAttribute("account")) != null &&
			!alb.isEmpty() && !alb.getAuctions().get(0).getFinished()) {
			bd.addBid(ub.getUsername());
		}

		request.setAttribute("auction", alb);
		forward = JSP_GET;
		if (request.getParameter("id") != null) {
			request.setAttribute("bid", bd.getBids());
		}
		return forward;
	}

	private String doLogin(HttpServletRequest request) {
		String forward;
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
		return forward;
	}

	private String doRegister(HttpServletRequest request) {
		String forward;
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
		return forward;
	}

}
