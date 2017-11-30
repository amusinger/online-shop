package beans;

import java.util.List;

import javax.ws.rs.PathParam;

import dto.ProductDTO;
import dto.UserDTO;

public interface ICartBean {
	
	public void addToCart(Long id);
	public void removeFromCart(Long id);
	public List<ProductDTO> getCart();
	
}
