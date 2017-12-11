package servlets;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import rest.ProductRest;

public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject 
	private ProductRest productRest;
	
	 public ProductServlet() {
	        super();
	    }

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setAttribute("products", productRest.getAllProducts());
			getServletContext().getRequestDispatcher("/WEB-INF/product.jsp").forward(request, response);
		}

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doGet(request, response);
		}

}
