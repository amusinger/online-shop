package beans;

import java.util.List;

import dto.CartProductDTO;

public interface ICartBean {
	
	public void addToCart(Long id, Long user_id);
	public void removeFromCart(Long id, Long user_id);
	public List<CartProductDTO> getCart(Long user_id);
	
}
