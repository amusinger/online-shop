package servlets;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import rest.OrderRest;

public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject 
	private OrderRest orderRest;
	
	 public OrderServlet() {
	        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("orders", orderRest.getAllOrders());
		getServletContext().getRequestDispatcher("/WEB-INF/order.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
