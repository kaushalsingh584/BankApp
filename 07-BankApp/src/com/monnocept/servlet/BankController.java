package com.monnocept.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.monocept.model.BankUtil;
import com.monocept.model.Customer;
import com.monocept.model.Transaction;

/**
 * Servlet implementation class BankController
 */
@WebServlet("/BankController")
public class BankController extends HttpServlet {

	@Resource(name = "jdbc/bankdb")
	private DataSource datasource;
	private BankUtil bank;
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BankController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println(request.getParameter("command"));
		String command = request.getParameter("command");
		if (command == null)
			command="FORM";
		switch (command) {
		case "LOGIN":
			try {
				login(request, response);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "FORM":
			dispform(request, response);
			break;
			
		case "USERDATA":
			try {
				userdata(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "TRANSACTION":
			try {
				transaction(request, response);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ServletException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
			
		case "ADDCUST":
			try {
				addcustomer(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		
		case "TRANSACTION_USER":
			try {
				transactionuser(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
//		case "FILTER":
//			try {
//				filtertransactions(request, response);
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			break;

		default:
			break;
		}

	}

//	private void filtertransactions(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
//		System.out.println(request.getParameter("command"));
//		System.out.println(request.getParameter("filtertype"));
//		System.out.println(request.getParameter("username"));
//		List<Transaction> filtertransaction = bank.filtertransaction("deposit","user1");
//		System.out.println(filtertransaction);
//		request.setAttribute("transactionlist", filtertransaction);
//		RequestDispatcher dispatcher= request.getRequestDispatcher("/show-transaction.jsp");
//		dispatcher.forward(request, response);
//	}

	private void transactionuser(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		String username = request.getParameter("username");
		List<Transaction> transactions = bank.getTransactions(username);
		request.setAttribute("transactionlist", transactions);
		RequestDispatcher dispatcher= request.getRequestDispatcher("/show-transaction.jsp");
		dispatcher.forward(request, response);
		
	}

	private void transaction(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		String username = request.getParameter("username");
		System.out.println(username);
		List<Transaction> transactions = bank.getTransactions(username);
		request.setAttribute("transactionlist", transactions);
		RequestDispatcher dispatcher= request.getRequestDispatcher("/show-transaction.jsp");
		dispatcher.forward(request, response);
		
	}

	private void addcustomer(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		String accountno = request.getParameter("accountno");
		String username = request.getParameter("username");
		String balance = request.getParameter("balance");
		System.out.println(accountno+" "+username+" "+balance);
		bank.addcustomer(accountno,username,balance);
//		response.sendRedirect(request.getContextPath()+"/list-user.jsp");
		userdata(request,response);
		
	}

	private void userdata(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		System.out.println("inside userdata");
		List<Customer> userdata = bank.userdata();
		System.out.println(userdata);
		request.setAttribute("customerlist", userdata);
		RequestDispatcher dispatcher= request.getRequestDispatcher("/list-user.jsp");
		dispatcher.forward(request, response);
	}

	private void dispform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/bankapp.jsp");
		dispatcher.forward(request, response);
		
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
		String logintype = request.getParameter("logintype");
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		boolean checkCredential = bank.checkCredential(username,password);
		boolean isAdmin = false;
		if (logintype.equals("Admin"))
			isAdmin = true;
		if (isAdmin && checkCredential && username.equals("admin")) {
			request.setAttribute("admin", "admin");
			RequestDispatcher dispatcher= request.getRequestDispatcher("/admindash.jsp");
			dispatcher.forward(request, response);

		} else if(checkCredential && !isAdmin) {
			request.setAttribute("admin", username);
			RequestDispatcher dispatcher= request.getRequestDispatcher("/user.jsp");
			dispatcher.forward(request, response);
			
			
		}else {
			request.setAttribute("admin", username);
			request.setAttribute("message", "Incorrect credentials");
			RequestDispatcher dispatcher= request.getRequestDispatcher("/bankapp.jsp");
			dispatcher.forward(request, response);
		}

	}

	
	@Override
	public void init() throws ServletException {
		super.init();
		bank=new BankUtil(datasource);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}