package beans;

import java.util.List;

import dto.ProductDTO;

public interface ICartBean {
	
	public void addToCart(Long id);
	public void removeFromCart(Long id);
	public List<ProductDTO> getCart();
	
}
