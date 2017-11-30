package beans;

import java.util.List;


import dto.OrderDTO;

public interface IOrderBean {
	public void order(Long id, String address);
	
	public List<OrderDTO> viewOrders();
}
