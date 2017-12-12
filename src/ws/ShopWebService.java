package ws;

import java.util.List;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;

import beans.CartBean;
import beans.OrderBean;
import beans.ProductBean;
import beans.UserBean;
import dto.OrderDTO;
import dto.ProductDTO;
import dto.UserDTO;

@WebService(serviceName="ShopWebService")
public class ShopWebService {

	@EJB 
	ProductBean productBean;
	
	@EJB
	CartBean cartBean;
	
	@EJB
	UserBean userBean;
	
	@EJB
	OrderBean orderBean;
	
	@WebMethod
    public List<ProductDTO> getAllProducts() {
		return productBean.getAllProducts();
    }
	
	@WebMethod
    public ProductDTO ProductById(Long id) {
		return productBean.getProductByID(id);
    }
	
	@WebMethod
	public List<ProductDTO> searchProductByName(String name) {
		return productBean.searchProductByName(name);
	}
	
	@WebMethod
	public List<ProductDTO> filterByPrice(Long minPrice, Long maxPrice) {
		return productBean.filterByPrice(minPrice, maxPrice);
	}
	
	@WebMethod
	public UserDTO login(String email, String password) {
		return userBean.login(email, password);
	}
	
	@WebMethod
	public List<UserDTO> isOnline() {
		return userBean.isOnline();
	}
	
	@WebMethod
	public void register(String email,  String password) {
		userBean.register(email, password);
	}
	
	@WebMethod
	public void logout(String email) {
		userBean.logout(email);
	}
	
	@WebMethod
	public void order(Long id, String address, Long user_id) {
		orderBean.order(id, address, user_id);
	}
	
	@WebMethod
	public List<OrderDTO> viewOrders() {
		return orderBean.viewOrders();
				
	}
	
	@WebMethod
	public void addToCart(Long id, Long user_id) {
		cartBean.addToCart(id, user_id);
	}
	
	
	@WebMethod
	public List<ProductDTO> getCart(Long user_id) {
		return cartBean.getCart(user_id);
	}
	
	@WebMethod
	public void removeFromCart(Long id, Long user_id) {
		cartBean.removeFromCart(id, user_id);
	}
	
	
}
