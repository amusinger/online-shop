package beans;

import java.util.List;


import dto.OrderDTO;

public interface IOrderBean {
	public void order(Long id, String address, Long user_id);
	
	public List<OrderDTO> viewOrders(Long user_id);
}
